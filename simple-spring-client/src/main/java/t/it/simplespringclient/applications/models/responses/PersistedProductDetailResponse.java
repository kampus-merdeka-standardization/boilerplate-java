package t.it.simplespringclient.applications.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record PersistedProductDetailResponse(
        String id,
        String name,
        Double price,
        String color,
        String capacity,
        String createdAt
) {
}
