package t.it.simplespringdatabase.domains.services.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public record UpdateCashCard(
        @NotBlank
        @Size(max = 36, message = "the length of id must not greater than {max}")
        @JsonIgnore
        String id,
        @NotBlank(message = "the holderName field must be filled")
        @Size(max = 150, message = "the length of holderName must not greater than {max}")
        String holderName,
        @NotNull(message = "the balance field must be filled")
        @Range(min = 1L, max = 9_999_999_999L, message = "the balance field must be between {min} and {max}")
        BigDecimal balance,
        @NotNull(message = "the updatedAt field must be filled")
        OffsetDateTime updatedAt
) {
}
