package t.it.simplespringlogging.application.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record WebResponse<T>(T data, MetaResponse meta) {
}
