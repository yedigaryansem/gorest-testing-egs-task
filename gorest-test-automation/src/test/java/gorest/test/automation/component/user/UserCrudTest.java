package gorest.test.automation.component.user;

import gorest.test.api.service.ResourceCrudServiceBase;
import gorest.test.api.service.UserService;
import gorest.test.automation.component.CrudTestBase;
import gorest.test.automation.db.entity.UserTestDataEntity;
import gorest.test.automation.db.repo.TestDataRepositoryBase;
import gorest.test.automation.db.repo.UserTestDataRepository;
import gorest.test.automation.utils.Utils;
import gorest.test.core.model.UserResource;
import gorest.test.validator.Assertion;
import org.springframework.beans.factory.annotation.Autowired;

public class UserCrudTest extends CrudTestBase<UserResource, UserTestDataEntity> {

    @Autowired
    protected UserService userService;

    @Autowired
    private UserTestDataRepository userTestDataRepository;

    @Override
    protected TestDataRepositoryBase<UserTestDataEntity> repository() {
        return userTestDataRepository;
    }

    @Override
    protected ResourceCrudServiceBase<UserResource> service() {
        return userService;
    }

    @Override
    protected UserResource convertToResource(UserTestDataEntity userTestDataEntity) {
        return UserResource.builder()
                .name(userTestDataEntity.getName())
                .email(userTestDataEntity.getEmail())
                .gender(userTestDataEntity.getGender())
                .status(userTestDataEntity.getStatus())
                .build();
    }

    @Override
    protected void prepareResourceUpdate(UserResource existingResource, UserResource resourceUpdates) {
        if(USE_ORIGINAL_VALUE.equals(resourceUpdates.getId())) {
            resourceUpdates.setId(existingResource.getId());
        }
        if(USE_ORIGINAL_VALUE.equals(resourceUpdates.getName())) {
            resourceUpdates.setName(existingResource.getName());
        }
        if(USE_ORIGINAL_VALUE.equals(resourceUpdates.getEmail())) {
            resourceUpdates.setEmail(existingResource.getEmail());
        }
        if(USE_ORIGINAL_VALUE.equals(resourceUpdates.getGender())) {
            resourceUpdates.setGender(existingResource.getGender());
        }
        if(USE_ORIGINAL_VALUE.equals(resourceUpdates.getStatus())) {
            resourceUpdates.setStatus(existingResource.getStatus());
        }
    }

    @Override
    protected void applyPartialUpdateOnExistingResource(UserResource existingResource, UserResource resourcePartialUpdates) {
        if(resourcePartialUpdates.getName() != null) {
            existingResource.setName(resourcePartialUpdates.getName());
        }
        if(resourcePartialUpdates.getEmail() != null) {
            existingResource.setEmail(resourcePartialUpdates.getEmail());
        }
        if(resourcePartialUpdates.getGender() != null) {
            existingResource.setGender(resourcePartialUpdates.getGender());
        }
        if(resourcePartialUpdates.getStatus() != null) {
            existingResource.setStatus(resourcePartialUpdates.getStatus());
        }
    }

    @Override
    protected UserResource randomValidResource() {
        return Utils.initRandomUser();
    }

    @Override
    protected void assertEquality(UserResource actual, UserResource expected) {
        Assertion.of(actual).assertEqualsTo(expected);
    }

















//    @Test
//    public void testUserCreate() throws Exception {
//        List<UserTestDataEntity> all = userTestDataRepository.findAll();
//        System.out.println(all.size());
//        UserResource testUser = initRandomUser();
//        Response<UserResource> response = userService.createNewResource(testUser);
//        testUser.setId(response.getResource().getId());
//        assertThat(response.getStatusCode()).isEqualTo(201);
//        Assertion.of(response.getResource()).assertEqualsTo(testUser);
//    }
//
//    @Test
//    public void testUserDelete() throws Exception {
//
//        Response<UserResource> response = userService.createNewResource(initRandomUser());
//        UserResource actualUser = response.getResource();
//
//        assertThat(userService.getResourceById(actualUser.getId()).getStatusCode()).isEqualTo(200);
//        assertThat(userService.deleteResource(actualUser.getId()).getStatusCode()).isEqualTo(204);
//
//        try {
//            userService.getResourceById(actualUser.getId());
//        } catch (ApiErrorResponseException e) {
//            Response<ApiError> apiError = e.getApiError();
//            assertThat(apiError.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
//            assertThat(apiError.getResource().getMessage()).isEqualTo(RESOURCE_NOT_FOUND);
//        }
//    }
//
//    @Test
//    public void testUserUpdate() throws Exception {
//        Response<UserResource> r = userService.createNewResource(initRandomUser());
//        UserResource user = r.getResource();
//
//        user.setName("updated-name");
//        Response<UserResource> response = userService.updateResource(user.getId(), user);
//        assertThat(response.getStatusCode()).isEqualTo(200);
//        Assertion.of(response.getResource()).assertEqualsTo(user);
//
//        response = userService.getResourceById(user.getId());
//        assertThat(response.getStatusCode()).isEqualTo(200);
//        Assertion.of(response.getResource()).assertEqualsTo(user);
//    }
//
//    @Test
//    public void testUserPartialUpdate() throws Exception {
//        Response<UserResource> r = userService.createNewResource(initRandomUser());
//        UserResource user = r.getResource();
//
//        user.setName("partial Update");
//        UserResource partialUpdate = user.toBuilder()
//                .status((String) null)
//                .gender((String) null)
//                .email(null)
//                .build();
//        Response<UserResource> response = userService.updateResourcePartially(user.getId(), partialUpdate);
//        assertThat(response.getStatusCode()).isEqualTo(200);
//        Assertion.of(response.getResource()).assertEqualsTo(user);
//
//        response = userService.getResourceById(user.getId());
//        assertThat(response.getStatusCode()).isEqualTo(200);
//        Assertion.of(response.getResource()).assertEqualsTo(user);
//    }
}
