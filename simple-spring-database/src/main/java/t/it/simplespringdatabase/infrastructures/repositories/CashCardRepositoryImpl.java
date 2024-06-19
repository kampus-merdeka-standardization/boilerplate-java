package t.it.simplespringdatabase.infrastructures.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import t.it.simplespringdatabase.domains.repositories.CashCardRepository;
import t.it.simplespringdatabase.domains.repositories.entities.CashCard;
import t.it.simplespringdatabase.infrastructures.repositories.mysql.MySqlCashCardDao;

import java.time.Duration;

@Repository
@RequiredArgsConstructor
public class CashCardRepositoryImpl implements CashCardRepository {
    private final MySqlCashCardDao mySqlCashCardDao;

    @Override
    public Mono<CashCard> addCashCard(CashCard cashCard) {
        return mySqlCashCardDao.save(cashCard);
    }

    @Override
    public Mono<CashCard> updateCashCard(CashCard cashCard) {
        return mySqlCashCardDao.save(cashCard).delaySubscription(Duration.ofSeconds(5));
    }

    @Override
    public Mono<Void> deleteCashCard(CashCard cashCard) {
        return mySqlCashCardDao.delete(cashCard);
    }

    @Override
    public Mono<CashCard> findCashCardById(String id) {
        return mySqlCashCardDao.findById(id);
    }
}