package t.it.simplespringrestserver.domains.services.models;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record AddUser(String name, Integer age) {
}
