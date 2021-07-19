insert into conference_category values(1, '업무') ON DUPLICATE KEY UPDATE id=1, name='업무';
insert into conference_category values(2, '교육') ON DUPLICATE KEY UPDATE id=2, name='교육';
insert into conference_category values(3, '기타') ON DUPLICATE KEY UPDATE id=3, name='기타';

insert into user values (1, 'SSAFY', '홍길동', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '교육생', 'test-1')
ON DUPLICATE KEY
UPDATE id = 1, department = 'SSAFY', name='홍길동', password='$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy',
        position = '교육생', user_id = 'test-1';