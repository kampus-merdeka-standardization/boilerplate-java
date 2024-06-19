package t.it.simplespringdatabase.domains.repositories.entities;

import lombok.Builder;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder(toBuilder = true)
@With
@Table(name = "cash_cards")
public record CashCard(
        @Id
        String id,
        @Column(value = "holder_name")
        String holderName,
        BigDecimal balance,
        @Column(value = "created_at")
        OffsetDateTime createdAt,
        @Column(value = "updated_at")
        OffsetDateTime updatedAt,
        @Version
        Long version
) {
}