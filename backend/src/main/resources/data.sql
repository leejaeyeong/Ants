insert into conference_category values(1, '업무') ON DUPLICATE KEY UPDATE id=1, name='업무';
insert into conference_category values(2, '교육') ON DUPLICATE KEY UPDATE id=2, name='교육';
insert into conference_category values(3, '기타') ON DUPLICATE KEY UPDATE id=3, name='기타';

insert into grp (id, name) values (1, "empty") ON DUPLICATE KEY UPDATE id=1, name='empty';
insert into grp (id, name) values (2, "Ants") ON DUPLICATE KEY UPDATE id=2, name='Ants';
insert into department (id, department_name) values (1, "개발부서") ON DUPLICATE KEY UPDATE id = 1, department_name = "개발부서";

insert into user (id, holiday, name, password, position, user_id, department, group_id) values (1, 14, '홍길동', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '교육생', 'test-1', 1, 1)
ON DUPLICATE KEY
UPDATE id = 1, name='홍길동', password='$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy',
        position = '교육생', user_id = 'test-1', department = 1, group_id = 1;
