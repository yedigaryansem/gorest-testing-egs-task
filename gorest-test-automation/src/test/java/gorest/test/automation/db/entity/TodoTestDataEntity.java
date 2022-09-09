package gorest.test.automation.db.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "todo_test_data")
@Getter
public class TodoTestDataEntity extends TestDataEntityBase {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_db_id")
    private UserTestDataEntity authorId;

    @Column(name = "title")
    private String title;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "status")
    private String status;
}
