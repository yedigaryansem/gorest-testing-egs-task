package gorest.test.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import gorest.test.core.metadata.HttpResourcePath;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@HttpResourcePath("posts")
public class PostResource implements ApiResource {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private Long authorId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("body")
    private String body;

    /**
     * An extension class for Lombok, to add custom methods to the builder.
     */
    public abstract static class PostResourceBuilder<C extends PostResource, B extends PostResourceBuilder<C, B>> {
    }

}
