package t.it.simplespringrestserver.applications.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record CurrentUserState(String id, String message, Long timestamp) {
}
