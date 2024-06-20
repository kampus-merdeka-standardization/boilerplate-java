package t.it.simplespringclient.infrastructures.repositories.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record WebResponse<T>(T data, MetaResponse meta) {
}
