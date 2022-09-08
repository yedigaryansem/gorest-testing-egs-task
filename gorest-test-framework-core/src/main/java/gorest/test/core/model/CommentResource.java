package gorest.test.core.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import gorest.test.core.metadata.HttpResourcePath;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@HttpResourcePath("comments")
public class CommentResource {
    @Builder.Default
    @JsonProperty("id")
    private Long id = null;

    @JsonProperty("post_id")
    private Long postId;

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
