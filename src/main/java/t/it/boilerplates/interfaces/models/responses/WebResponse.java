package t.it.boilerplates.interfaces.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record WebResponse<T>(
        T data,
        String errors
) {
}
