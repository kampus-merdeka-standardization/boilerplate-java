package t.it.simplespringclient.applications.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record PongResponse(String message) {
}
