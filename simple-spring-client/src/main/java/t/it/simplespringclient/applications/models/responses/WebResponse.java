package t.it.simplespringclient.applications.models.responses;

import lombok.Builder;
import lombok.With;
import t.it.simplespringclient.infrastructures.repositories.payloads.responses.MetaResponse;

@Builder
@With
public record WebResponse<T>(T data, MetaResponse meta) {
}
