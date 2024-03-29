package t.it.boilerplates.interfaces.models.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record AddProductRequest(
        @NotBlank(message = "name must be filled")
        String name,
        @NotNull(message = "price must be filled")
        @Min(value = 1L, message = "the price ${validatedValue} must be greater than 0")
        Long price,
        @NotBlank(message = "category must be filled")
        String category,
        @NotNull(message = "updated at must be filled")
        @Min(value = 1L, message = "the updated at ${validatedValue} must be greater than 0")
        Long updatedAt,
        @NotNull(message = "created at must be filled")
        @Min(value = 1L, message = "the created at ${validatedValue} must be greater than 0")
        Long createdAt,
        @NotBlank(message = "description must be filled")
        String description
) {
}
