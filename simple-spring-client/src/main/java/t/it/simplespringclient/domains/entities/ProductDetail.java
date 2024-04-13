package t.it.simplespringclient.domains.entities;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class ProductDetail {
    private String color;
    private String capacity;
    private Integer year;
    private Double price;
}
