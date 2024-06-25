package t.it.simplespringdatabase.domains.repositories.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    @Column(value = "created_at")
    private OffsetDateTime createdAt;
    @Column(value = "updated_at")
    private OffsetDateTime updatedAt;
    @Version
    private Long version;
}