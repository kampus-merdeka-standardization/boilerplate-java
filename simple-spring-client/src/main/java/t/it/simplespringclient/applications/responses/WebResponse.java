package t.it.simplespringclient.applications.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record WebResponse<T>(T data, MetaResponse meta) {
}
