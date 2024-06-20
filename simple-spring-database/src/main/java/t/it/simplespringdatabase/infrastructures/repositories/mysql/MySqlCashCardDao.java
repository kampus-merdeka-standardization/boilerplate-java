package t.it.simplespringdatabase.infrastructures.repositories.mysql;

import jakarta.annotation.Nonnull;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.relational.core.sql.LockMode;
import org.springframework.data.relational.repository.Lock;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import t.it.simplespringdatabase.domains.repositories.entities.CashCard;

@Component
public interface MySqlCashCardDao extends R2dbcRepository<CashCard, String> {
    // SELECT FOR UPDATE
    @Lock(LockMode.PESSIMISTIC_WRITE)
    @Nonnull
    @Override
    Mono<CashCard> findById(@Nonnull String id);
}
