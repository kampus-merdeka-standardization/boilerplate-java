package t.it.simplespringrestserver.interfaces.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record WebResponse<T>(T data) {
}
