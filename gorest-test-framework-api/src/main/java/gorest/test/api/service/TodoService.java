package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.communication.RequestFactory;
import gorest.test.core.model.PartialTodoResource;
import gorest.test.core.model.TodoResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class TodoService extends ResourceCrudServiceBase<TodoResource, PartialTodoResource> {
    @Autowired
    public TodoService(RequestFactory baseRequestFactory,
                       ObjectMapper objectMapper) throws URISyntaxException {
        super(baseRequestFactory, objectMapper, TodoResource.class);
    }

    @Override
    protected Class<TodoResource> resourceType() {
        return TodoResource.class;
    }

    @Override
    protected Class<TodoResource[]> resourceArrayType() {
        return TodoResource[].class;
    }

    @Override
    protected PartialTodoResource createPartialUpdateObject(TodoResource updates) {
        return PartialTodoResource.builder()
                .copyFromResource(updates)
                .build();
    }
}
