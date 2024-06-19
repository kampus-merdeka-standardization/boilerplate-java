package t.it.simplespringdatabase.domains.services;

import reactor.core.publisher.Mono;
import t.it.simplespringdatabase.domains.services.models.*;

public interface CashCardService {
    Mono<PersistedCashCard> addCashCard(Mono<AddCashCard> addCashCard);
    Mono<PersistedCashCard> updateCashCard(Mono<UpdateCashCard> updateCashCard);
    Mono<String> deleteCashCardById(String id);
    Mono<PersistedCashCardDetail> getCashCardById(String id);
}
