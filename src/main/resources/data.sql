INSERT INTO DEPARTMENT (id, name)
VALUES (1, 'IT'),
       (2, 'HR'),
       (3, 'SALES'),
       (4, 'TestToDelete');

INSERT INTO EMPLOYEE (id, full_name, date_of_birth, salary, department_id)
VALUES (10, 'Ivan Ivanov', '2018-10-26', 10000, 1),
       (11, 'Ivan Petrov', '2018-10-26', 20000, 1),
       (12, 'Ivan Sidorov', '2018-10-26', 30000, 1),
       (13, 'Ivan Smirnov', '2018-10-26', 40000, 1),
       (14, 'Peter Petrov', '2019-10-26', 20000, 2),
       (15, 'Semen Semenov', '2016-10-26', 30000, 3);