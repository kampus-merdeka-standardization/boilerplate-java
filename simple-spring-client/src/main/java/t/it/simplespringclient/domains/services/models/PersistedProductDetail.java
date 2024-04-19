package t.it.simplespringclient.domains.services.models;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record PersistedProductDetail(
        String id,
        String name,
        Double price,
        String color,
        String capacity,
        String createdAt
) {
}
