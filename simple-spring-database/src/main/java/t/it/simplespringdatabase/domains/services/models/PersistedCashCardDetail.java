package t.it.simplespringdatabase.domains.services.models;

import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@With
public record PersistedCashCardDetail(
        String id,
        String holderName,
        BigDecimal balance,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
