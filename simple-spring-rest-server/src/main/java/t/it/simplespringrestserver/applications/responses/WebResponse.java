package t.it.simplespringrestserver.applications.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record WebResponse<T>(MetaResponse meta, T data) {
}
