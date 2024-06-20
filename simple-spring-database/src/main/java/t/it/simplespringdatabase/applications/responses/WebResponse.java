package t.it.simplespringdatabase.applications.responses;

import lombok.Builder;
import lombok.With;

@Builder
@With
public record WebResponse<T>(T data, MetaResponse meta) {
}
