package t.it.simplespringapp.interfaces.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record UpdateSomeProductFieldsRequest(
        @NotBlank
        String id,
        String name,
        Long price,
        String category,
        Long updatedAt,
        String description
) {
}
