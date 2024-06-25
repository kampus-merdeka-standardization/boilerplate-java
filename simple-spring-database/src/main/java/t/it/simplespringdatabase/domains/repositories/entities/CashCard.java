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
@Table(name = "cash_cards")
public class CashCard {
    @Id
    private String id;
    @Column(value = "holder_name")
    private String holderName;
    private BigDecimal balance;
    @Column(value = "created_at")
    private OffsetDateTime createdAt;
    @Column(value = "updated_at")
    private OffsetDateTime updatedAt;
    @Version
    private Long version;
}