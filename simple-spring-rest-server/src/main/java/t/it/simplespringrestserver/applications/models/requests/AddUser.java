package t.it.simplespringrestserver.applications.models.requests;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AddUser(String name, String age) {
}
