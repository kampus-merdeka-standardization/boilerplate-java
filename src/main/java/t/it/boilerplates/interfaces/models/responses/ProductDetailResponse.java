package t.it.boilerplates.interfaces.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record ProductDetailResponse(
        String id,
        String name,
        Long price,
        String category,
        Long updatedAt,
        Long createdAt,
        String description
) {
}
