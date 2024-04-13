package t.it.simplespringclient.domains.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class Product {
    private String id;
    private String name;
    @JsonProperty("data")
    private ProductDetail productDetail;
    private String createdAt;
}
