package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.communication.RequestFactory;
import gorest.test.core.model.PartialUserResource;
import gorest.test.core.model.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class UserService extends ResourceCrudServiceBase<UserResource, PartialUserResource> {
    @Autowired
    public UserService(RequestFactory baseRequestFactory,
                       ObjectMapper objectMapper) throws URISyntaxException {
        super(baseRequestFactory, objectMapper, UserResource.class);
    }

    @Override
    protected Class<UserResource> resourceType() {
        return UserResource.class;
    }

    @Override
    protected Class<UserResource[]> resourceArrayType() {
        return UserResource[].class;
    }

    @Override
    protected PartialUserResource createPartialUpdateObject(UserResource updates) {
        return PartialUserResource.builder()
                .copyFromResource(updates)
                .build();
    }
}
