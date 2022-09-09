package gorest.test.automation.component.todo;

import gorest.test.api.communication.Response;
import gorest.test.api.service.ResourceCrudServiceBase;
import gorest.test.api.service.TodoService;
import gorest.test.api.service.UserService;
import gorest.test.automation.component.CrudTestBase;
import gorest.test.automation.db.entity.TodoTestDataEntity;
import gorest.test.automation.db.entity.TodoTestDataEntity;
import gorest.test.automation.db.repo.TestDataRepositoryBase;
import gorest.test.automation.db.repo.TodoTestDataRepository;
import gorest.test.automation.utils.Utils;
import gorest.test.core.model.TodoResource;
import gorest.test.core.model.TodoResource;
import gorest.test.core.model.UserResource;
import gorest.test.core.utils.ResourceUtils;
import gorest.test.validator.Assertion;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TodoCrudTest extends CrudTestBase<TodoResource, TodoTestDataEntity> {

    private UserResource existingUser;

    @Autowired
    protected UserService userService;

    @Autowired
    protected TodoService todoService;

    @Autowired
    private TodoTestDataRepository todoTestDataRepository;

    @BeforeClass(dependsOnMethods = SPRING_CONTEXT_INIT_BEFORE_CLASS)
    public void createRandomUser() throws Exception {
        initExistingUser();
    }

    @Override
    protected TestDataRepositoryBase<TodoTestDataEntity> repository() {
        return todoTestDataRepository;
    }

    @Override
    protected ResourceCrudServiceBase<TodoResource> service() {
        return todoService;
    }

    @Override
    protected TodoResource convertToResource(TodoTestDataEntity todoTestDataEntity) {
        return TodoResource.builder()
                .authorId(existingUser.getId())
                .title(todoTestDataEntity.getTitle())
                .dueDate(todoTestDataEntity.getDueDate())
                .status(todoTestDataEntity.getStatus())
                .build();
    }

    @Override
    protected void prepareResourceUpdate(TodoResource existingResource, TodoResource resourceUpdates) {
        if(USE_ORIGINAL_VALUE.equals(resourceUpdates.getId())) {
            resourceUpdates.setId(existingResource.getId());
        }
        if(USE_ORIGINAL_VALUE.equals(resourceUpdates.getAuthorId())) {
            resourceUpdates.setAuthorId(existingResource.getAuthorId());
        }
        if(USE_ORIGINAL_VALUE.equals(resourceUpdates.getTitle())) {
            resourceUpdates.setTitle(existingResource.getTitle());
        }
        if(USE_ORIGINAL_VALUE.equals(resourceUpdates.getDueDate())) {
            resourceUpdates.setDueDate(existingResource.getDueDate());
        }
        if(USE_ORIGINAL_VALUE.equals(resourceUpdates.getStatus())) {
            resourceUpdates.setStatus(existingResource.getStatus());
        }
    }

    @Override
    protected void applyPartialUpdateOnExistingResource(TodoResource existingResource, TodoResource resourcePartialUpdates) {
        if(resourcePartialUpdates.getTitle() != null) {
            existingResource.setTitle(resourcePartialUpdates.getTitle());
        }
        if(resourcePartialUpdates.getDueDate() != null) {
            existingResource.setDueDate(resourcePartialUpdates.getDueDate());
        }
        if(resourcePartialUpdates.getStatus() != null) {
            existingResource.setStatus(resourcePartialUpdates.getStatus());
        }
    }

    @Override
    protected TodoResource randomValidResource() {
        initExistingUser();
        return Utils.initRandomTodo(existingUser.getId());
    }

    @Override
    protected void assertEquality(TodoResource actual, TodoResource expected) {
        Assertion.of(actual).assertEqualsTo(expected);
    }

    private void initExistingUser() {
        try {
            if (existingUser == null) {
                Response<UserResource> createUserResponse = userService.createNewResource(Utils.initRandomUser());
                assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
                existingUser = createUserResponse.getResource();
            }
        } catch (Exception checkedException) {
            throw new RuntimeException(checkedException);
        }
    }
}
