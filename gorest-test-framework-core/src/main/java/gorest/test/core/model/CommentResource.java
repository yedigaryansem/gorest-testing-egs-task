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
@HttpResourcePath("comments")
public class CommentResource implements ApiResource {
    @JsonProperty("id")
    private String id;

    @JsonProperty("post_id")
    private String postId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("body")
    private String body;

    /**
     * An extension class for Lombok, to add custom methods to the builder.
     */
    public abstract static class CommentResourceBuilder<C extends CommentResource, B extends CommentResourceBuilder<C, B>> {
    }
}
