INSERT INTO user_test_data(test_type, test_operation, name, email, gender, status)
VALUES ('POSITIVE', 'CREATE', 'positive_name', 'positive@email.com', 'male', 'active'),
       ('POSITIVE', 'CREATE', 'Sam Yedigaryan', 'sy@mail.com', 'male', 'active'),
       ('POSITIVE', 'CREATE', 'Aaaaaaaaaaaaa', 'a@email.com', 'female', 'inactive'),
       ('POSITIVE', 'CREATE', 'a a a a', 'aa@email.com', 'male', 'inactive'),
       ('POSITIVE', 'CREATE', '1', '1@email.com', 'female', 'active'),
       ('POSITIVE', 'CREATE', 'A 1', 'a1@email.com', 'male', 'active'),
       ('POSITIVE', 'CREATE', '1 1', '11@email.com', 'female', 'active'),

       ('NEGATIVE', 'CREATE', '', 'blank@email.com', 'male', 'active'),
       ('NEGATIVE', 'CREATE', ' ', 'space@email.com', 'none', 'active'),
       ('NEGATIVE', 'CREATE', 'Alex', '', 'male', 'active'),
       ('NEGATIVE', 'CREATE', 'Alex', 'invalidMail', 'male', 'active'),
       ('NEGATIVE', 'CREATE', 'Alex', 'invalidMail.com', 'male', 'active'),
       ('NEGATIVE', 'CREATE', 'Alex', ' ', 'male', 'active'),
       ('NEGATIVE', 'CREATE', 'Alex', 'Alex@email.com', 'male', 'unknown'),
       ('NEGATIVE', 'CREATE', '', '', 'male', 'active'),
       ('NEGATIVE', 'CREATE', '', '', '', 'active'),
       ('NEGATIVE', 'CREATE', '', '', '', ''),
       ('NEGATIVE', 'CREATE', ' ', ' ', ' ', ' '),

       ('POSITIVE', 'UPDATE', 'UpdatedName', '0', '0', '0'),
       ('POSITIVE', 'UPDATE', '0', 'updatedMail@mail.com', '0', '0'),
       ('POSITIVE', 'UPDATE', '0', '0', 'male', '0'),
       ('POSITIVE', 'UPDATE', '0', '0', '0', 'inactive'),
       ('POSITIVE', 'UPDATE', 'xUpdatedName', 'xupdatedMail@mail.com', 'female', 'inactive'),

       ('NEGATIVE', 'UPDATE', '', '0', '0', '0'),
       ('NEGATIVE', 'UPDATE', '0', 'invalidMail', '0', '0'),
       ('NEGATIVE', 'UPDATE', '0', '0', 'none', '0'),
       ('NEGATIVE', 'UPDATE', '0', '0', '0', 'unknown'),
       ('NEGATIVE', 'UPDATE', '', 'invalidMail', 'none', 'unknown'),

       ('POSITIVE', 'PARTIAL_UPDATE', 'UpdatedName', null, null, null),
       ('POSITIVE', 'PARTIAL_UPDATE', null, 'pupdatedMail@mail.com', null, null),
       ('POSITIVE', 'PARTIAL_UPDATE', null, null, 'male', null),
       ('POSITIVE', 'PARTIAL_UPDATE', null, null, null, 'inactive'),
       ('POSITIVE', 'PARTIAL_UPDATE', 'xUpdatedName', 'xpupdatedMail@mail.com', 'female', 'inactive'),

       ('NEGATIVE', 'PARTIAL_UPDATE', '', null, null, null),
       ('NEGATIVE', 'PARTIAL_UPDATE', null, 'invalidMail', null, null),
       ('NEGATIVE', 'PARTIAL_UPDATE', null, null, 'none', null),
       ('NEGATIVE', 'PARTIAL_UPDATE', null, null, null, 'unknown'),
       ('NEGATIVE', 'PARTIAL_UPDATE', '', 'invalidMail', 'none', 'unknown')
;



INSERT INTO todo_test_data(test_type, test_operation, title, due_date, status)
VALUES ('POSITIVE', 'CREATE', 'Title', '2022-09-17T00:00:00.000+05:30', 'pending'),
       ('POSITIVE', 'CREATE', 'title', '2022-09-17T00:00:00.000+05:30', 'completed'),
       ('POSITIVE', 'CREATE', 'TITLE', '2022-09-17T00:00:00.000+05:30', 'pending'),
       ('POSITIVE', 'CREATE', 'SOME LOOOOOONG TITLE AND TITLE', '2022-09-17T00:00:00.000+05:30', 'completed'),
       ('POSITIVE', 'CREATE', 'T', '2022-09-17T00:00:00.000+04:00', 'completed'),
       ('POSITIVE', 'CREATE', '123456789', '2022-09-17T00:00:00.000+05:30', 'completed'),
       ('POSITIVE', 'CREATE', '(-_-)', '2022-09-17T00:00:00.000+05:30', 'completed'),

       ('NEGATIVE', 'CREATE', '', '2022-09-17T00:00:00.000+05:30', 'completed'),
       ('NEGATIVE', 'CREATE', ' ', '2022-09-17T00:00:00.000+05:30', 'pending'),
       ('NEGATIVE', 'CREATE', ' ', 'invalidDate', 'pending'),
       ('NEGATIVE', 'CREATE', ' ', '2022-09-17T00:00:00.000+05:30', 'unknown'),
       ('NEGATIVE', 'CREATE', ' ', ' ', ' '),

       ('POSITIVE', 'UPDATE', 'UpdatedTitle', '0', '0'),
       ('POSITIVE', 'UPDATE', '0', '2022-09-18T00:00:00.000+05:30', '0'),
       ('POSITIVE', 'UPDATE', '0', '0', 'completed'),
       ('POSITIVE', 'UPDATE', 'xUpdatedTitle', '2022-10-19T00:00:00.000+05:30', 'completed'),

       ('NEGATIVE', 'UPDATE', ' ', '0', '0'),
       ('NEGATIVE', 'UPDATE', '0', 'invalidDate', '0'),
       ('NEGATIVE', 'UPDATE', '0', '0', 'unknown'),
       ('NEGATIVE', 'UPDATE', ' ', 'invalidDate', 'unknown'),

       ('POSITIVE', 'PARTIAL_UPDATE', 'UpdatedTitle', '0', '0'),
       ('POSITIVE', 'PARTIAL_UPDATE', '0', '2022-09-18T00:00:00.000+05:30', '0'),
       ('POSITIVE', 'PARTIAL_UPDATE', '0', '0', 'completed'),
       ('POSITIVE', 'PARTIAL_UPDATE', 'xUpdatedTitle', '2022-10-19T00:00:00.000+05:30', 'completed'),

       ('NEGATIVE', 'PARTIAL_UPDATE', ' ', '0', '0'),
       ('NEGATIVE', 'PARTIAL_UPDATE', '0', 'invalidDate', '0'),
       ('NEGATIVE', 'PARTIAL_UPDATE', '0', '0', 'unknown'),
       ('NEGATIVE', 'PARTIAL_UPDATE', ' ', 'invalidDate', 'unknown')
;