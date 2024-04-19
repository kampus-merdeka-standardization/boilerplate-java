package t.it.simplespringclient.domains.repositories.entities;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDetail {
    private String color;
    private String capacity;
    private Integer year;
    private Double price;
}
