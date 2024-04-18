package t.it.simplespringapp.applications.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record MetaResponse(
        String code,
        String message
) {
}
