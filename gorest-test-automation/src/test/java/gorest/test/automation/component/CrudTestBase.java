package gorest.test.automation.component;

import gorest.test.api.communication.ApiError;
import gorest.test.api.communication.BasicResponse;
import gorest.test.api.communication.FieldValueApiError;
import gorest.test.api.communication.Response;
import gorest.test.api.exception.response.ApiErrorResponseException;
import gorest.test.api.exception.response.ApiFieldValueErrorResponseException;
import gorest.test.api.service.ResourceCrudServiceBase;
import gorest.test.automation.TestBase;
import gorest.test.automation.db.entity.OperationType;
import gorest.test.automation.db.entity.TestDataEntityBase;
import gorest.test.automation.db.entity.TestType;
import gorest.test.automation.db.repo.TestDataRepositoryBase;
import gorest.test.core.model.ApiResource;
import org.apache.hc.core5.http.HttpStatus;
import org.assertj.core.api.Fail;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public abstract class CrudTestBase<R extends ApiResource, TestDataEntity extends TestDataEntityBase> extends TestBase {

    public static final String RESOURCE_NOT_FOUND = "Resource not found";

    private final List<R> existingResources = new ArrayList<>();

    protected static final String USE_ORIGINAL_VALUE = "0";

    /**
     * A method that erases all the resources from the server and also checks the response of each query.
     * This is done to have a clean environment when the actual tests start
     */
    @BeforeClass(dependsOnMethods = SPRING_CONTEXT_INIT_BEFORE_CLASS)
    public void eraseRemoteEnvironmentResources() throws Exception {
        // Create a random resource, to ensure basic valid resource creation API is OK
        testResourceCreation_positive(randomValidResource());

        // Fetch all the existing resources and ensure that newly created resource is in the list
        Response<R[]> allResourcesResponse = service().getAllResources();
        assertThat(allResourcesResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        R[] allResources = allResourcesResponse.getResource();
        assertThat(allResources.length).isGreaterThan(0);
        R existingResource = existingResources.get(0);
        R existingResourceFromResponse = Arrays.stream(allResources)
                .filter(r -> r.getId().equals(existingResource.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Cannot find newly created resource " + existingResource.getId() + " in all resources list."));
        assertEquality(existingResource, existingResourceFromResponse);

        // Delete all the existing resources and check the response
        // This prepares the remote environment for the future tests by cleaning up all the resources
        for (R resource : allResources) {
            testDeleteResource_positive(resource);
        }
    }

    @Test(dataProvider = "positiveResourcesToCreate")
    public void testResourceCreation_positive(R testResource) throws Exception {
        Response<R> creationResponse = service().createNewResource(testResource);
        assertThat(creationResponse.getStatusCode()).isEqualTo(HttpStatus.SC_CREATED);
        R createdResource = creationResponse.getResource();
        testResource.setId(createdResource.getId());
        assertEquality(createdResource, testResource);
        existingResources.add(createdResource);
    }

    @Test(dataProvider = "negativeResourcesToCreate", dependsOnMethods = "testResourceCreation_positive")
    public void testResourceCreation_negative(R testResource) throws Exception {
        try {
            Response<R> creationResponse = service().createNewResource(testResource);
            Fail.fail("Resource creation should not succeed, but returned a response with code %s",
                    creationResponse.getStatusCode());
        } catch (ApiErrorResponseException apiErrorException) {
            Response<ApiError> apiError = apiErrorException.getApiError();
            assertThat(apiError.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
            //TODO: we should save the expected error in the DB, so we can check the actual response from the server
        } catch (ApiFieldValueErrorResponseException apiErrorException) {
            Response<FieldValueApiError[]> apiErrors = apiErrorException.getFieldApiErrors();
            assertThat(apiErrors.getStatusCode()).isEqualTo(HttpStatus.SC_UNPROCESSABLE_ENTITY);
            //TODO: we should save the expected error in the DB, so we can check the actual response from the server
        }
    }

    @Test(dataProvider = "existingResources", dependsOnMethods = "testResourceCreation_positive")
    public void testResourceFetchById_positive(R expectedResource) throws Exception {
        Response<R> fetchResponse = service().getResourceById(expectedResource.getId());
        assertThat(fetchResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        assertEquality(fetchResponse.getResource(), expectedResource);
    }

    @Test(dataProvider = "positiveResourcesToUpdate", dependsOnMethods = "testResourceFetchById_positive")
    public void testResourceUpdate_positive(R resourceUpdates) throws Exception {
        R existingResource = existingResources.get(0);
        prepareResourceUpdate(existingResource, resourceUpdates);
        Response<R> updateResponse = service().updateResource(existingResource.getId(), resourceUpdates);
        existingResources.remove(0);
        existingResources.add(updateResponse.getResource());
        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        resourceUpdates.setId(existingResource.getId());
        assertEquality(updateResponse.getResource(), resourceUpdates);
        Response<R> fetchResponse = service().getResourceById(resourceUpdates.getId());
        assertEquality(fetchResponse.getResource(), updateResponse.getResource());
    }

    @Test(dataProvider = "negativeResourcesToUpdate", dependsOnMethods = "testResourceUpdate_positive")
    public void testResourceUpdate_negative(R resourceUpdates) throws Exception {
        R existingResource = existingResources.get(0);
        try {
            prepareResourceUpdate(existingResource, resourceUpdates);
            Response<R> updateResponse = service().updateResource(existingResource.getId(), resourceUpdates);
            Fail.fail("Resource update should not succeed, but returned a response with code %s",
                    updateResponse.getStatusCode());
        } catch (ApiErrorResponseException apiErrorException) {
            Response<ApiError> apiError = apiErrorException.getApiError();
            assertThat(apiError.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
            //TODO: we should save the expected error in the DB, so we can check the actual response from the server
        } catch (ApiFieldValueErrorResponseException apiErrorException) {
            Response<FieldValueApiError[]> apiErrors = apiErrorException.getFieldApiErrors();
            assertThat(apiErrors.getStatusCode()).isEqualTo(HttpStatus.SC_UNPROCESSABLE_ENTITY);
            //TODO: we should save the expected error in the DB, so we can check the actual response from the server
        }
    }

    @Test(dataProvider = "positiveResourcesToUpdatePartially", dependsOnMethods = "testResourceFetchById_positive")
    public void testResourceUpdatePartially_positive(R resourcePartialUpdates) throws Exception {
        R existingResource = existingResources.get(0);
        Response<R> updateResponse = service().updateResourcePartially(existingResource.getId(), resourcePartialUpdates);
        existingResources.remove(0);
        existingResources.add(updateResponse.getResource());
        assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        applyPartialUpdateOnExistingResource(existingResource, resourcePartialUpdates);
        assertEquality(updateResponse.getResource(), existingResource);
        Response<R> fetchResponse = service().getResourceById(existingResource.getId());
        assertEquality(fetchResponse.getResource(), updateResponse.getResource());
    }

    @Test(dataProvider = "negativeResourcesToUpdatePartially", dependsOnMethods = "testResourceUpdatePartially_positive")
    public void testResourceUpdatePartially_negative(R resourcePartialUpdates) throws Exception {
        R existingResource = existingResources.get(0);
        try {
            Response<R> updateResponse = service().updateResourcePartially(existingResource.getId(), resourcePartialUpdates);
            Fail.fail("Resource update should not succeed, but returned a response with code %s",
                    updateResponse.getStatusCode());
        } catch (ApiErrorResponseException apiErrorException) {
            Response<ApiError> apiError = apiErrorException.getApiError();
            assertThat(apiError.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
            //TODO: we should save the expected error in the DB, so we can check the actual response from the server
        } catch (ApiFieldValueErrorResponseException apiErrorException) {
            Response<FieldValueApiError[]> apiErrors = apiErrorException.getFieldApiErrors();
            assertThat(apiErrors.getStatusCode()).isEqualTo(HttpStatus.SC_UNPROCESSABLE_ENTITY);
            //TODO: we should save the expected error in the DB, so we can check the actual response from the server
        }
    }

    @Test(dataProvider = "existingResources", dependsOnMethods = "testResourceUpdatePartially_negative", priority = Integer.MAX_VALUE)
    public void testDeleteResource_positive(R existingResource) throws Exception {
        BasicResponse deleteResponse = service().deleteResource(existingResource.getId());
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.SC_NO_CONTENT);

        try {
            service().getResourceById(existingResource.getId());
        } catch (ApiErrorResponseException e) {
            Response<ApiError> apiErrorResponse = e.getApiError();
            assertThat(apiErrorResponse.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
            assertThat(apiErrorResponse.getResource().getMessage()).isEqualTo(RESOURCE_NOT_FOUND);
        }

        existingResources.removeIf(r -> r.getId().equals(existingResource.getId()));
    }

    @DataProvider
    protected Iterator<Object[]> existingResources() {
        return existingResources.stream()
                .limit(5) // testing GET/DELETE query maximum on 5 elements
                .map(resource -> new Object[]{resource})
                .toList()
                .iterator();
    }

    @DataProvider
    protected Iterator<Object[]> positiveResourcesToCreate() {
        return getResources(TestType.POSITIVE, OperationType.CREATE, OperationType.CREATE_OR_UPDATE);
    }

    @DataProvider
    protected Iterator<Object[]> negativeResourcesToCreate() {
        return getResources(TestType.NEGATIVE, OperationType.CREATE, OperationType.CREATE_OR_UPDATE);
    }

    @DataProvider
    protected Iterator<Object[]> positiveResourcesToUpdate() {
        return getResources(TestType.POSITIVE, OperationType.UPDATE, OperationType.CREATE_OR_UPDATE);
    }

    @DataProvider
    protected Iterator<Object[]> negativeResourcesToUpdate() {
        return getResources(TestType.NEGATIVE, OperationType.UPDATE, OperationType.CREATE_OR_UPDATE);
    }

    @DataProvider
    protected Iterator<Object[]> positiveResourcesToUpdatePartially() {
        return getResources(TestType.POSITIVE, OperationType.PARTIAL_UPDATE);
    }

    @DataProvider
    protected Iterator<Object[]> negativeResourcesToUpdatePartially() {
        return getResources(TestType.NEGATIVE, OperationType.PARTIAL_UPDATE);
    }

    protected abstract TestDataRepositoryBase<TestDataEntity> repository();

    protected abstract ResourceCrudServiceBase<R> service();

    protected abstract R convertToResource(TestDataEntity testData);

    protected abstract void prepareResourceUpdate(R existingResource, R resourceUpdates);

    protected abstract void applyPartialUpdateOnExistingResource(R existingResource, R resourcePartialUpdates);

    protected abstract R randomValidResource();

    protected abstract void assertEquality(R actual, R expected);

    private Iterator<Object[]> getResources(TestType testType, OperationType... operations) {
        return repository().findAllByTypeAndOperationIn(testType, List.of(operations))
                .stream()
                .map(this::convertToResource)
                .map(resource -> new Object[]{resource})
                .iterator();
    }
}
