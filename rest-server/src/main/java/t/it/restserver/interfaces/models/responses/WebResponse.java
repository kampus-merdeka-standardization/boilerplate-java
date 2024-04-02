package t.it.restserver.interfaces.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record WebResponse<T>(T data) {
}
