package gorest.test.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gorest.test.api.RequestFactory;
import gorest.test.core.model.TodoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoService extends ServiceBase {
    long lastId = 0;

    @Autowired
    public TodoService(RequestFactory requestFactory, ObjectMapper objectMapper) {
        super(requestFactory, objectMapper);
    }

    public TodoEntity createNewTodo(TodoEntity newTodo) {
        //TODO: remove mock and implement
        return TodoEntity.builder()
                .id(++lastId)
                .authorId(newTodo.getAuthorId())
                .title(newTodo.getTitle())
                .dueDate(newTodo.getDueDate())
                .status(newTodo.getStatus())
                .build();
    }

    public TodoEntity getTodoById(Long id) {
        //TODO: remove mock and implement
        return TodoEntity.builder()
                .id(id)
                .build();
    }

    /*
     Ideally, here we should have a proper argument type for searching todos
     But, as we are short in time, this will be a temporary implementation, and we'll add it later on, if there
     will be time enough
     */
    public List<TodoEntity> getTodosByDetails(TodoEntity todoDetails) {
        return List.of(todoDetails);
    }

    public TodoEntity updateTodo(TodoEntity updatedTodo) {
        //TODO: remove mock and implement
        return updatedTodo;
    }

    public TodoEntity updateTodoPartially(TodoEntity updatedTodo) {
        //TODO: remove mock and implement
        return updatedTodo;
    }

    public TodoEntity deleteTodo(Long id) {
        //TODO: remove mock and implement
        return TodoEntity.builder()
                .id(id)
                .build();
    }
}
