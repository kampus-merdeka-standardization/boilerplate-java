package t.it.simplespringclient.domains.services.models;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record PersistedProduct(
        String id,
        String name,
        Double price,
        String createdAt
) {
}
