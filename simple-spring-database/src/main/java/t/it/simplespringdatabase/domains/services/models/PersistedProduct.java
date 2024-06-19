package t.it.simplespringdatabase.domains.services.models;

import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;

@Builder
@With
public record PersistedProduct(
        String id,
        String name,
        BigDecimal price
) {
}
