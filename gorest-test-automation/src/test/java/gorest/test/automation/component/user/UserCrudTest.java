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
}
