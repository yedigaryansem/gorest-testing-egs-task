package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.communication.RequestFactory;
import gorest.test.core.model.PostPartialUpdate;
import gorest.test.core.model.PostResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class PostService extends ResourceCrudServiceBase<PostResource, PostPartialUpdate> {

    @Autowired
    public PostService(RequestFactory baseRequestFactory,
                       ObjectMapper objectMapper) throws URISyntaxException {
        super(baseRequestFactory, objectMapper, PostResource.class);
    }

    @Override
    protected Class<PostResource> resourceType() {
        return PostResource.class;
    }

    @Override
    protected Class<PostResource[]> resourceArrayType() {
        return PostResource[].class;
    }

    @Override
    protected PostPartialUpdate createPartialUpdateObject(PostResource updates) {
        return PostPartialUpdate.builder()
                .copyFromResource(updates)
                .build();
    }
}
