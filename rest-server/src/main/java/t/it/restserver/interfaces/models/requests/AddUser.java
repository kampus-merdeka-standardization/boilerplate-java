package t.it.restserver.interfaces.models.requests;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AddUser(String name, String age) {
}
