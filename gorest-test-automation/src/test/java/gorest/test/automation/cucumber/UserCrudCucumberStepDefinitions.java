package gorest.test.automation.cucumber;

import gorest.test.api.communication.ApiError;
import gorest.test.api.communication.Response;
import gorest.test.api.exception.response.ApiErrorResponseException;
import gorest.test.api.service.UserService;
import gorest.test.core.model.UserResource;
import gorest.test.validator.Assertion;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserCrudCucumberStepDefinitions {
    @Autowired
    private UserService userService;
    private UserResource user;
    private Response<UserResource> createUserResponse;

    @Given("I have a new user with name {string} and email {string} and gender {string} and status {string}")
    public void iHaveANewUserWithNameAndEmailAndGenderAndStatus(String name, String email, String gender, String status) {
        user = UserResource.builder()
                .name(name)
                .email(email)
                .gender(gender)
                .status(status)
                .build();
    }

    @When("I send create request with that user")
    public void iSendCreateRequestWithThatUser() throws Exception {
        createUserResponse = userService.createNewResource(user);
    }

    @Then("I should get response with the same user and id")
    public void iShouldGetResponseWithTheSameUserAndId() {
        user.setId(createUserResponse.getResource().getId());
        Assertion.of(createUserResponse.getResource()).assertEqualsTo(user);
    }

    @Then("Delete that user")
    public void deleteThatUser() throws Exception {
        userService.deleteResource(user.getId());
    }

    @Then("Ensure api returns error with message {string} when I request that user by id again")
    public void ensureApiReturnsErrorWithMessageWhenIRequestThatUserByIdAgain(String errorMessage) throws Exception {
        try {
            Response<UserResource> deleteResponse = userService.getResourceById(user.getId());
        } catch (ApiErrorResponseException e) {
            Response<ApiError> apiErrorResponse = e.getApiError();
            ApiError apiError = apiErrorResponse.getResource();
            assertThat(apiError.getMessage()).isEqualTo(errorMessage);
        }
    }
}
