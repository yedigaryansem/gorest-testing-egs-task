package gorest.test.automation.db.repo;

import gorest.test.automation.db.entity.UserTestDataEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTestDataRepository extends TestDataRepositoryBase<UserTestDataEntity> {
}
