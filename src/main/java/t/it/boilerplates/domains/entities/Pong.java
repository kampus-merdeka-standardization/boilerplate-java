package t.it.boilerplates.domains.entities;

import lombok.Builder;
import lombok.Data;
import lombok.With;

@Builder
@With
@Data
public class Pong {
    private String message;
}
