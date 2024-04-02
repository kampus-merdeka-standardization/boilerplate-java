package t.it.restserver.interfaces.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record CurrentUserState(String id, String message, Long timestamp) {
}
