package t.it.simplespringdatabase.domains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import t.it.simplespringdatabase.commons.exceptions.ConflictResourceException;
import t.it.simplespringdatabase.commons.exceptions.NotFoundResourceException;
import t.it.simplespringdatabase.domains.repositories.CashCardRepository;
import t.it.simplespringdatabase.domains.repositories.entities.CashCard;
import t.it.simplespringdatabase.domains.services.models.*;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CashCardServiceImpl implements CashCardService {
    private final CashCardRepository cashCardRepository;
    private final ValidationService validationService;

    @Override
    @Transactional
    public Mono<PersistedCashCard> addCashCard(Mono<AddCashCard> addCashCard) {
        return addCashCard
                .flatMap(
                        cashCardItem ->
                                validationService.validateObject(cashCardItem)
                                        .map(Optional::of)
                                        .defaultIfEmpty(Optional.empty())
                                        .flatMap(validationException -> validationException.<Mono<? extends CashCard>>map(Mono::error).orElseGet(() -> Mono.just(
                                                CashCard.builder()
                                                        .id(UUID.randomUUID().toString())
                                                        .holderName(cashCardItem.holderName())
                                                        .balance(cashCardItem.balance())
                                                        .createdAt(cashCardItem.createdAt())
                                                        .updatedAt(cashCardItem.createdAt())
                                                        .build()
                                        )))
                )
                .flatMap(
                        cashCardRepository::addCashCard
                ).map(cashCard -> PersistedCashCard.builder()
                        .id(cashCard.getId())
                        .holderName(cashCard.getHolderName())
                        .createdAt(cashCard.getCreatedAt())
                        .updatedAt(cashCard.getUpdatedAt())
                        .build())
                .subscribeOn(Schedulers.parallel());
    }

    @Transactional
    @Override
    public Mono<PersistedCashCard> updateCashCard(Mono<UpdateCashCard> updateCashCard) {
        return updateCashCard
                .flatMap(
                        cashCardItem ->
                                validationService.validateObject(cashCardItem)
                                        .map(Optional::of)
                                        .defaultIfEmpty(Optional.empty())
                                        .flatMap(validationException -> validationException.<Mono<? extends CashCard>>map(Mono::error).orElseGet(() ->
                                                cashCardRepository
                                                        .findCashCardById(cashCardItem.id())
                                                        .switchIfEmpty(Mono.error(new NotFoundResourceException("the cash card is not found")))
                                                        .onErrorResume(throwable -> throwable instanceof OptimisticLockingFailureException, throwable -> Mono.error(new ConflictResourceException("the cash card is already changed by others")))
                                        ))
                                        .flatMap(
                                                cashCardEntity -> cashCardRepository.updateCashCard(cashCardEntity
                                                                .withHolderName(cashCardItem.holderName())
                                                                .withBalance(cashCardItem.balance())
                                                                .withUpdatedAt(cashCardItem.updatedAt()))
                                        )
                )
                .map(
                        cashCard ->
                                PersistedCashCard.builder()
                                        .id(cashCard.getId())
                                        .holderName(cashCard.getHolderName())
                                        .createdAt(cashCard.getCreatedAt())
                                        .updatedAt(cashCard.getUpdatedAt())
                                        .build()
                )
                .subscribeOn(Schedulers.parallel()
                );
    }

    @Transactional
    @Override
    public Mono<String> deleteCashCardById(String id) {
        return cashCardRepository.findCashCardById(id)
                .switchIfEmpty(Mono.error(new NotFoundResourceException("the cash card is not found")))
                .onErrorResume(throwable -> throwable instanceof OptimisticLockingFailureException, throwable -> Mono.error(new ConflictResourceException("the cash card is already changed by others")))
                .flatMap(cashCard ->
                        cashCardRepository.deleteCashCard(cashCard)
                                .subscribeOn(Schedulers.parallel()).then(Mono.fromCallable(cashCard::getId)))
                .subscribeOn(Schedulers.parallel());
    }

    @Override
    public Mono<PersistedCashCardDetail> getCashCardById(String id) {
        return cashCardRepository.findCashCardById(id)
                .map(cashCard -> PersistedCashCardDetail.builder()
                        .id(cashCard.getId())
                        .holderName(cashCard.getHolderName())
                        .balance(cashCard.getBalance())
                        .createdAt(cashCard.getCreatedAt())
                        .updatedAt(cashCard.getUpdatedAt())
                        .build())
                .subscribeOn(Schedulers.parallel());
    }
}
