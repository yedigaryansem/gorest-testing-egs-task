package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.communication.RequestFactory;
import gorest.test.core.model.CommentResource;
import gorest.test.core.model.PartialCommentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class CommentService extends ResourceCrudServiceBase<CommentResource> {

    @Autowired
    public CommentService(RequestFactory baseRequestFactory,
                          ObjectMapper objectMapper) throws URISyntaxException {
        super(baseRequestFactory, objectMapper, CommentResource.class);
    }

    @Override
    protected Class<CommentResource> resourceType() {
        return CommentResource.class;
    }

    @Override
    protected Class<CommentResource[]> resourceArrayType() {
        return CommentResource[].class;
    }

    @Override
    protected PartialCommentResource createPartialUpdateObject(CommentResource updates) {
        return PartialCommentResource.builder()
                .copyFromResource(updates)
                .build();
    }

}
