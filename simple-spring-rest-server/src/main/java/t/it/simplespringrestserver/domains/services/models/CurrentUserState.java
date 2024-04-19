package t.it.simplespringrestserver.domains.services.models;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record CurrentUserState(String id, String message, Long timestamp) {
}
