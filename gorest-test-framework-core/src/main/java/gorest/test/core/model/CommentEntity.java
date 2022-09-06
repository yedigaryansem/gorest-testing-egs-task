package gorest.test.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentEntity {
    @Builder.Default
    private final Long id = null;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
