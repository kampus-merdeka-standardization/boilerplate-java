package t.it.simplespringclient.applications.payloads.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record MetaResponse(
        String code,
        String message
) {
}
