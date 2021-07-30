-- 화상회의 타입 초기데이터 sql
insert into conference_category values(1, '업무') ON DUPLICATE KEY UPDATE id=1;
insert into conference_category values(2, '교육') ON DUPLICATE KEY UPDATE id=2;
insert into conference_category values(3, '기타') ON DUPLICATE KEY UPDATE id=3;

-- 그룹 초기데이터 sql
insert into grp (id, name) values (1, "empty") ON DUPLICATE KEY UPDATE id=1;
insert into grp (id, name) values (2, "Ants") ON DUPLICATE KEY UPDATE id=2;

-- 부서 초기데이터 sql
insert into department (id, department_name) values (1, "개발부서") ON DUPLICATE KEY UPDATE id = 1;

-- 사용자 초기데이터 sql
insert into user (id, holiday, name, password, position, user_id, department, group_id) values (1, 14, '홍길동', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '교육생', 'test-1', 1, 1)
ON DUPLICATE KEY
UPDATE id = 1;

-- 게시물 타입 초기데이터 sql
insert into board_type values(1, "공지사항 게시판") ON DUPLICATE KEY UPDATE id=1;
insert into board_type values(2, "익명 게시판") ON DUPLICATE KEY UPDATE id=2;
insert into board_type values(3, "개발팀") ON DUPLICATE KEY UPDATE id=3;
insert into board_type values(4, "마케팅팀") ON DUPLICATE KEY UPDATE id=4;
insert into board_type values(5, "디자인팀") ON DUPLICATE KEY UPDATE id=5;

-- 게시물 초기 데이터 sql
insert into board values (1, '공지사항 내용입니다.',now(),'공지제목',1,'test-1', 13)  ON DUPLICATE KEY UPDATE id=1;
insert into board values (2, '2021 하반기 공채 내용',now(),'2021 하반기 공채입니다',2,'test-1', 20)  ON DUPLICATE KEY UPDATE id=2;

-- 사용자가 게시물 마크 초기 데이터 sql
insert into user_marker_board values(1, 1, 'test-1') ON DUPLICATE KEY UPDATE id=1;