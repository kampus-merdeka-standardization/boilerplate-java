package t.it.simplespringdatabase.domains.repositories;

import reactor.core.publisher.Mono;
import t.it.simplespringdatabase.domains.repositories.entities.CashCard;

public interface CashCardRepository {
    Mono<CashCard> addCashCard(CashCard cashCard);
    Mono<CashCard> updateCashCard(CashCard cashCard);
    Mono<Void> deleteCashCard(CashCard cashCard);
    Mono<CashCard> findCashCardById(String id);
}
