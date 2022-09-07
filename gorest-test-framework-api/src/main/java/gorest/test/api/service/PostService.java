package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.RequestFactory;
import gorest.test.core.model.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostService extends ServiceBase {
    long lastId = 0;

    @Autowired
    public PostService(RequestFactory requestFactory, ObjectMapper objectMapper) {
        super(requestFactory, objectMapper);
    }

    public PostEntity createNewPost(PostEntity newPost) {
        //TODO: remove mock and implement
        return PostEntity.builder()
                .id(++lastId)
                .authorId(newPost.getAuthorId())
                .title(newPost.getTitle())
                .body(newPost.getBody())
                .build();
    }

    public PostEntity getPostById(Long id) {
        //TODO: remove mock and implement
        return PostEntity.builder()
                .id(id)
                .build();
    }

    /*
     Ideally, here we should have a proper argument type for searching posts
     But, as we are short in time, this will be a temporary implementation, and we'll add it later on, if there
     will be time enough
     */
    public List<PostEntity> getPostsByDetails(PostEntity postDetails) {
        return List.of(postDetails);
    }

    public PostEntity updatePost(PostEntity updatedPost) {
        //TODO: remove mock and implement
        return updatedPost;
    }

    public PostEntity updatePostPartially(PostEntity updatedPost) {
        //TODO: remove mock and implement
        return updatedPost;
    }

    public PostEntity deletePost(Long id) {
        //TODO: remove mock and implement
        return PostEntity.builder()
                .id(id)
                .build();
    }
}
