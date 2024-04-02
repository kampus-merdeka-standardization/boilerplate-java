package t.it.restclient.domains.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {
    private String id;
    private String name;
    @JsonProperty("data")
    private ProductDetail productDetail;
    private String createdAt;
}
