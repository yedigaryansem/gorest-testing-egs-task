package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.communication.RequestFactory;
import gorest.test.core.model.PartialPostResource;
import gorest.test.core.model.PostResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class PostService extends ResourceCrudServiceBase<PostResource, PartialPostResource> {

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
    protected PartialPostResource createPartialUpdateObject(PostResource updates) {
        return PartialPostResource.builder()
                .copyFromResource(updates)
                .build();
    }
}
