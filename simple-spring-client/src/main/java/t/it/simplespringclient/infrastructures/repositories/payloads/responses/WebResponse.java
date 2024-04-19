package t.it.simplespringclient.infrastructures.repositories.payloads.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record WebResponse<T>(T data, MetaResponse meta) {
}
