package t.it.simplespringapp.domains.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Product {
    private String id;
    private String name;
    private Long price;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
}
