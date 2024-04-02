package t.it.restclient.interfaces.models.responses;

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
