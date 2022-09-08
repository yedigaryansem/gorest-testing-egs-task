package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.communication.BasicResponse;
import gorest.test.api.communication.HttpRequestBuilder;
import gorest.test.api.communication.RequestFactory;
import gorest.test.api.communication.Response;
import gorest.test.core.utils.ResourceUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

@Component
public abstract class ResourceCrudServiceBase<T, P> extends ServiceBase {
    @Autowired
    public ResourceCrudServiceBase(RequestFactory baseRequestFactory,
                                   ObjectMapper objectMapper,
                                   Class<T> resourceType) throws URISyntaxException {
        super(baseRequestFactory.withAppendedBaseUrl(ResourceUtils.extractPathSegment(resourceType)), objectMapper);
    }

    public Response<T> createNewResource(T newResource) throws URISyntaxException, IOException {
        var newResourceCreationRequest = requestFactory().post()
                .build(newResource);
        return execute(newResourceCreationRequest, resourceType());
    }

    public Response<T> getResourceById(Long id) throws URISyntaxException, IOException {
        var getResourceRequest = requestFactory().get()
                .pathSegment(id)
                .build();
        return execute(getResourceRequest, resourceType());
    }

    public Response<T[]> getResourcesByDetails(T resourceSearchDetails) throws URISyntaxException, IOException {
        HttpRequestBuilder<HttpGet> requestBuilder = requestFactory().get();
        Map<String, String> searchParams = ResourceUtils.extractSearchParams(resourceSearchDetails);
        searchParams.forEach(requestBuilder::param);
        var getResourcesRequest = requestBuilder.build();
        return execute(getResourcesRequest, resourceArrayType());
    }

    public Response<T> updateResource(Long targetResourceId, T updates) throws URISyntaxException, IOException {
        var updateResourceRequest = requestFactory().put()
                .pathSegment(targetResourceId)
                .build(updates);
        return execute(updateResourceRequest, resourceType());

    }

    public Response<T> updateResourcePartially(Long targetResourceId, T updates) throws URISyntaxException, IOException {
        P partialUpdate = createPartialUpdateObject(updates);
        var updateResourceRequest = requestFactory().put()
                .pathSegment(targetResourceId)
                .build(partialUpdate);
        return execute(updateResourceRequest, resourceType());
    }

    public BasicResponse deleteResource(Long id) throws URISyntaxException, IOException {
        var deleteResourceRequest = requestFactory().delete()
                .pathSegment(id)
                .build();
        return execute(deleteResourceRequest);
    }

    protected abstract Class<T> resourceType();

    protected abstract Class<T[]> resourceArrayType();

    protected abstract P createPartialUpdateObject(T updates);
}