package gorest.test.automation.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuperBuilder
@Getter
@NoArgsConstructor
@MappedSuperclass
public class TestDataEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "db_id")
    private Long dbId;

    @Column(name = "test_type")
    @Enumerated(EnumType.STRING)
    private TestType type;

    @Column(name = "test_operation")
    @Enumerated(EnumType.STRING)
    private OperationType operation;
}
