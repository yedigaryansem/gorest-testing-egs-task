package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.communication.BasicResponse;
import gorest.test.api.communication.HttpRequestBuilder;
import gorest.test.api.communication.RequestFactory;
import gorest.test.api.communication.Response;
import gorest.test.api.exception.response.ApiErrorResponseException;
import gorest.test.api.exception.response.ApiFieldValueErrorResponseException;
import gorest.test.core.utils.ResourceUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 *
 * @param <T> the java-type of the resource that is being sent and received by this service
 */
@Component
public abstract class ResourceCrudServiceBase<T> extends ServiceBase {
    @Autowired
    public ResourceCrudServiceBase(RequestFactory baseRequestFactory,
                                   ObjectMapper objectMapper,
                                   Class<T> resourceType) throws URISyntaxException {
        super(baseRequestFactory.withAppendedBaseUrl(ResourceUtils.extractPathSegment(resourceType)), objectMapper);
    }

    public Response<T[]> getAllResources() throws URISyntaxException, ApiErrorResponseException, IOException, ParseException, ApiFieldValueErrorResponseException {
        var getAllResourcesRequest = requestFactory().get().build();
        return execute(getAllResourcesRequest, resourceArrayType());
    }

    public Response<T> createNewResource(T newResource) throws URISyntaxException, IOException, ParseException, ApiFieldValueErrorResponseException, ApiErrorResponseException {
        var newResourceCreationRequest = requestFactory().post()
                .body(newResource)
                .build();
        return execute(newResourceCreationRequest, resourceType());
    }

    public Response<T> getResourceById(String id) throws URISyntaxException, IOException, ParseException, ApiFieldValueErrorResponseException, ApiErrorResponseException {
        var getResourceRequest = requestFactory().get()
                .pathSegment(id)
                .build();
        return execute(getResourceRequest, resourceType());
    }

    public Response<T[]> getResourcesByDetails(T resourceSearchDetails) throws URISyntaxException, IOException, ParseException, ApiFieldValueErrorResponseException, ApiErrorResponseException {
        HttpRequestBuilder<HttpGet> requestBuilder = requestFactory().get();
        Map<String, String> searchParams = ResourceUtils.extractSearchParams(resourceSearchDetails);
        searchParams.forEach(requestBuilder::param);
        var getResourcesRequest = requestBuilder.build();
        return execute(getResourcesRequest, resourceArrayType());
    }

    public Response<T> updateResource(String targetResourceId, T updates) throws URISyntaxException, IOException, ParseException, ApiFieldValueErrorResponseException, ApiErrorResponseException {
        var updateResourceRequest = requestFactory().put()
                .pathSegment(targetResourceId)
                .body(updates)
                .build();
        return execute(updateResourceRequest, resourceType());
    }

    public Response<T> updateResourcePartially(String targetResourceId, T updates) throws URISyntaxException, IOException, ParseException, ApiFieldValueErrorResponseException, ApiErrorResponseException {
        Object partialUpdate = createPartialUpdateObject(updates);
        var updateResourceRequest = requestFactory().put()
                .pathSegment(targetResourceId)
                .body(partialUpdate)
                .build();
        return execute(updateResourceRequest, resourceType());
    }

    public BasicResponse deleteResource(String id) throws URISyntaxException, IOException {
        var deleteResourceRequest = requestFactory().delete()
                .pathSegment(id)
                .build();
        return execute(deleteResourceRequest);
    }

    protected abstract Class<T> resourceType();

    protected abstract Class<T[]> resourceArrayType();

    protected abstract Object createPartialUpdateObject(T updates);
}
