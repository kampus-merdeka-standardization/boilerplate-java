package t.it.simplespringclient.domains.services.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record UpdateProduct(
        @JsonIgnore
        String id,
        String name,
        String color,
        String capacity,
        Integer year,
        Double price
) {
}
