package t.it.simplespringdatabase.domains.services.models;

import lombok.Builder;
import lombok.With;

import java.time.OffsetDateTime;

@Builder
@With
public record PersistedCashCard(
        String id,
        String holderName,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
