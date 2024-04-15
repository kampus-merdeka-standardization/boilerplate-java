package t.it.simplespringclient.applications.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record PersistedProductResponse(
        String id,
        String name,
        Double price,
        String createdAt
) {
}
