package t.it.simplespringdatabase.domains.services.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@With
public record UpdateSomeProduct(
        @NotBlank
        @Size(max = 36, message = "the length of id must not greater than {max}")
        @JsonIgnore
        String id,
        @Size(max = 255, message = "the length of name must not greater than {max}")
        String name,
        @Range(min = 1L, max = 9_999_999_999L, message = "the price field must be between {min} and {max}")
        BigDecimal price,
        @Min(value = 1L, message = "the minimum value of quantity must be {value}")
        Integer quantity,
        String description,
        @NotNull(message = "the updatedAt field must be filled")
        OffsetDateTime updatedAt
) {
}
