package t.it.simplespringrestserver.domains.services.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.With;

@Builder
@With
public record UpdateUser(@JsonIgnore String id, String currentName, String newName) {
}
