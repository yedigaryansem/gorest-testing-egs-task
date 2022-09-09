package gorest.test.automation.db.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment_test_data")
@Getter
public class CommentTestDataEntity extends TestDataEntityBase {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_post_db_id")
    private PostTestDataEntity targetPost;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "body")
    private String body;
}
