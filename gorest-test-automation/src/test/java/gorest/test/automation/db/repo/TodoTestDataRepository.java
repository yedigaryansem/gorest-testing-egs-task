package gorest.test.automation.db.repo;

import gorest.test.automation.db.entity.TodoTestDataEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoTestDataRepository extends TestDataRepositoryBase<TodoTestDataEntity> {
}
