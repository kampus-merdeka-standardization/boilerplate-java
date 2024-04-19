package t.it.simplespringclient.domains.services.models;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record AddProduct(
        String name,
        String color,
        String capacity,
        Integer year,
        Double price
        ) {
}
