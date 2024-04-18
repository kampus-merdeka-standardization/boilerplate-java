package t.it.simplespringapp.applications.models.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record WebResponse<T>(T data, MetaResponse meta) {
}
