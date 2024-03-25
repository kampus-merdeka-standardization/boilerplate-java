package t.it.boilerplates.domains.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {
    private String id;
    private String name;
    private Long price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String description;
}
