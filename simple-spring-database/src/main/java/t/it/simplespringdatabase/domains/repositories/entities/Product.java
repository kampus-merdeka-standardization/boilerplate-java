package t.it.simplespringdatabase.domains.repositories.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder(toBuilder = true)
@With
@Table(name = "products")
public record Product(
        @Id
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        @Column(value = "created_at")
        OffsetDateTime createdAt,
        @Column(value = "updated_at")
        OffsetDateTime updatedAt,
        @Version
        Long version
) {

}
