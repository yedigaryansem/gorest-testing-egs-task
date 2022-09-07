package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.RequestFactory;
import gorest.test.core.model.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentService extends ServiceBase {
    long lastId = 0;

    @Autowired
    public CommentService(RequestFactory requestFactory, ObjectMapper objectMapper) {
        super(requestFactory, objectMapper);
    }

    public CommentEntity createNewComment(CommentEntity newComment) {
        //TODO: remove mock and implement
        return CommentEntity.builder()
                .id(++lastId)
                .postId(newComment.getPostId())
                .name(newComment.getName())
                .email(newComment.getEmail())
                .body(newComment.getBody())
                .build();
    }

    public CommentEntity getCommentById(Long id) {
        //TODO: remove mock and implement
        return CommentEntity.builder()
                .id(id)
                .build();
    }

    /*
     Ideally, here we should have a proper argument type for searching comments
     But, as we are short in time, this will be a temporary implementation, and we'll add it later on, if there
     will be time enough
     */
    public List<CommentEntity> getCommentsByDetails(CommentEntity commentDetails) {
        return List.of(commentDetails);
    }

    public CommentEntity updateComment(CommentEntity updatedComment) {
        //TODO: remove mock and implement
        return updatedComment;
    }

    public CommentEntity updateCommentPartially(CommentEntity updatedComment) {
        //TODO: remove mock and implement
        return updatedComment;
    }

    public CommentEntity deleteComment(Long id) {
        //TODO: remove mock and implement
        return CommentEntity.builder()
                .id(id)
                .build();
    }
}
