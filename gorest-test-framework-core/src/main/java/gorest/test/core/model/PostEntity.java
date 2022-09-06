package gorest.test.core.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostEntity {
    @Builder.Default
    private final Long id = null;
    private Long authorId;
    private String title;
    private String body;
}
