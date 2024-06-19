package t.it.simplespringdatabase.domains.services.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.With;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@With
public record AddProduct(
        @NotBlank(message = "the name field must be filled")
        @Size(max = 255, message = "the length of name must not greater than {max}")
        String name,
        @Range(min = 1L, max = 9_999_999_999L, message = "the price field must be between {min} and {max}")
        BigDecimal price,
        @Min(value = 1L, message = "the minimum value of quantity must be {value}")
        Integer quantity,
        @NotBlank(message = "the description field must be filled")
        String description,
        @NotNull(message = "the createdAt field must be filled")
        OffsetDateTime createdAt
) {
}
