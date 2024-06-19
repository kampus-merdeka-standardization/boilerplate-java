package t.it.simplespringdatabase.domains.services.models;

import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@With
public record PersistedProductDetail(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
