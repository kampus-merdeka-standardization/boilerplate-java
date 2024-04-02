package t.it.restclient.domains.entities;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDetail {
    private String color;
    private String capacity;
    private Integer year;
    private Double price;
}
