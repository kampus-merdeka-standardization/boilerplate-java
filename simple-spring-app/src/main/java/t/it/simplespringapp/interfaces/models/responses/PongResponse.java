package t.it.simplespringapp.interfaces.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record PongResponse(String message) {
}
