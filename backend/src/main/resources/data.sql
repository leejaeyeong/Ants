-- 화상회의 타입 초기데이터 sql
insert into conference_category values(1, '업무') ON DUPLICATE KEY UPDATE id=1;
insert into conference_category values(2, '교육') ON DUPLICATE KEY UPDATE id=2;
insert into conference_category values(3, '기타') ON DUPLICATE KEY UPDATE id=3;

-- 그룹 초기데이터 sql
insert into grp (id, name) values (1, "empty") ON DUPLICATE KEY UPDATE id=1;
insert into grp (id, name) values (2, "Ants") ON DUPLICATE KEY UPDATE id=2;

-- 부서 초기데이터 sql
insert into department (id, department_name) values (1, "프론트엔드 개발팀") ON DUPLICATE KEY UPDATE id = 1;
insert into department (id, department_name) values (2, "백엔드 개발팀") ON DUPLICATE KEY UPDATE id = 2;
insert into department (id, department_name) values (3, "플랫폼 디자인팀") ON DUPLICATE KEY UPDATE id = 3;
insert into department (id, department_name) values (4, "마켓팅 전략팀") ON DUPLICATE KEY UPDATE id = 4;
-- 사용자 초기데이터 sql
insert into user (id, holiday, name, password, position, user_id, department, group_id)
values (1, 14, '홍길동', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '교육생', 'test-1', 1, 1)
ON DUPLICATE KEY
UPDATE id = 1;
insert into user (id, holiday, name, password, position, user_id, department, group_id)
values (2, 14, '홍길동', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '임시 관리자', 'admin', 1, 2)
ON DUPLICATE KEY
UPDATE id = 2;

-- 게시물 타입 초기데이터 sql
insert into board_type values(1, "공지사항 게시판") ON DUPLICATE KEY UPDATE id=1;
insert into board_type values(2, "익명 게시판") ON DUPLICATE KEY UPDATE id=2;
insert into board_type values(3, "프론트엔드 개발팀") ON DUPLICATE KEY UPDATE id=3;
insert into board_type values(4, "백엔드 마케팅팀") ON DUPLICATE KEY UPDATE id=4;
insert into board_type values(5, "플랫폼 디자인팀") ON DUPLICATE KEY UPDATE id=5;
insert into board_type values(6, "마켓팅 전략팀") ON DUPLICATE KEY UPDATE id=6;

-- 게시물 초기 데이터 sql
insert into board values (1, '공지사항 내용입니다.',now(),'공지제목', 13,1,'test-1')  ON DUPLICATE KEY UPDATE id=1;
insert into board values (2, '익명 글 2',now(),'2021 하반기 공채입니다 2', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=2;
insert into board values (3, '익명 글 3',now(),'2021 하반기 공채입니다 3', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=3;
insert into board values (4, '익명 글 4',now(),'2021 하반기 공채입니다 4', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=4;
insert into board values (5, '익명 글 5',now(),'2021 하반기 공채입니다 5', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=5;
insert into board values (6, '익명 글 6',now(),'2021 하반기 공채입니다 6', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=6;
insert into board values (7, '익명 글 7',now(),'2021 하반기 공채입니다 7', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=7;
insert into board values (8, '익명 글 8',now(),'2021 하반기 공채입니다 8', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=8;
insert into board values (9, '2021 하반기 공채 내용 9',now(),'2021 하반기 공채입니다 9', 20,4,'test-1')  ON DUPLICATE KEY UPDATE id=9;
insert into board values (10, '2021 하반기 공채 내용 10',now(),'2021 하반기 공채입니다 10', 20,4,'test-1')  ON DUPLICATE KEY UPDATE id=10;
insert into board values (11, '2021 하반기 공채 내용 11',now(),'2021 하반기 공채입니다 11', 20,4,'test-1')  ON DUPLICATE KEY UPDATE id=11;
insert into board values (12, '2021 하반기 공채 내용 12',now(),'2021 하반기 공채입니다 12', 20,4,'test-1')  ON DUPLICATE KEY UPDATE id=12;
insert into board values (13, '2021 하반기 공채 내용 13',now(),'2021 하반기 공채입니다 13', 20,5,'test-1')  ON DUPLICATE KEY UPDATE id=13;
insert into board values (14, '2021 하반기 공채 내용 14',now(),'2021 하반기 공채입니다 14', 20,5,'test-1')  ON DUPLICATE KEY UPDATE id=14;
insert into board values (15, '2021 하반기 공채 내용 15',now(),'2021 하반기 공채입니다 15', 20,5,'test-1')  ON DUPLICATE KEY UPDATE id=15;


-- 사용자가 게시물 마크 초기 데이터 sql
insert into user_marker_board values(1, 1, 'test-1') ON DUPLICATE KEY UPDATE id=1;
insert into user_marker_board values(2, 5, 'test-1') ON DUPLICATE KEY UPDATE id=2;
insert into user_marker_board values(3, 10, 'test-1') ON DUPLICATE KEY UPDATE id=3;
insert into user_marker_board values(4, 11, 'admin') ON DUPLICATE KEY UPDATE id=4;
insert into user_marker_board values(5, 12, 'admin') ON DUPLICATE KEY UPDATE id=5;
insert into user_marker_board values(6, 13, 'admin') ON DUPLICATE KEY UPDATE id=6;