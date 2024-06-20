package t.it.simplespringdatabase.applications.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record MetaResponse(
        String code,
        String message
) {
}
