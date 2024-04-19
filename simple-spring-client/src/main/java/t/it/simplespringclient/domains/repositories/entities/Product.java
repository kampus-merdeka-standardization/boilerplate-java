package t.it.simplespringclient.domains.repositories.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {
    private String id;
    private String name;
    @JsonProperty("data")
    private ProductDetail productDetail;
    private String createdAt;
}
