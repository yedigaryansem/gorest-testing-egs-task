package gorest.test.automation.db.repo;

import gorest.test.automation.db.entity.OperationType;
import gorest.test.automation.db.entity.TestDataEntityBase;
import gorest.test.automation.db.entity.TestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TestDataRepositoryBase<T extends TestDataEntityBase> extends JpaRepository<T, Long> {

    List<T> findAllByType(TestType type);

    List<T> findAllByTypeAndOperationIn(TestType type, List<OperationType> operations);
}
