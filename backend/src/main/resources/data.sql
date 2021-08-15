-- 화상회의 타입 초기데이터 sql
insert into conference_category values(1, '업무') ON DUPLICATE KEY UPDATE id=1;
insert into conference_category values(2, '교육') ON DUPLICATE KEY UPDATE id=2;
insert into conference_category values(3, '기타') ON DUPLICATE KEY UPDATE id=3;

-- 유저 상태 정보 초기 데이터 sql
insert into user_state values(1, '관리자') ON DUPLICATE KEY UPDATE id=1;
insert into user_state values(2, '사용자') ON DUPLICATE KEY UPDATE id=2;
insert into user_state values(3, '가입 대기중') ON DUPLICATE KEY UPDATE id=3;

-- 그룹 초기데이터 sql
--insert into grp (id, name) values (1, "empty") ON DUPLICATE KEY UPDATE id=1;
--insert into grp (id, name) values (2, "Ants") ON DUPLICATE KEY UPDATE id=2;

-- 부서 초기데이터 sql
insert into department (id, department_name) values (1, "프론트엔드 개발팀") ON DUPLICATE KEY UPDATE id = 1;
insert into department (id, department_name) values (2, "백엔드 개발팀") ON DUPLICATE KEY UPDATE id = 2;
insert into department (id, department_name) values (3, "플랫폼 디자인팀") ON DUPLICATE KEY UPDATE id = 3;
insert into department (id, department_name) values (4, "마켓팅 전략팀") ON DUPLICATE KEY UPDATE id = 4;
-- 사용자 초기데이터 sql
insert into user (id, email, holiday, name, password, position, profile_location, state, user_id, department)
values (1, 'last9500@naver.com', 14, '정범진', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '대리', '/media/profile/test-1/test.gif', 1, 'test-1', 1)
ON DUPLICATE KEY
UPDATE id = 1;
insert into user (id, email, holiday, name, password, position, profile_location, state, user_id, department)
values (2, 'giver12@naver.com', 14, '이재영', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '대리', '/media/profile/admin/test.gif', 1, 'admin', 2)
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
insert into board values (1, '공지사항 내용입니다.', now(),null, now(),now(),'공지제목', 13,1,'test-1')  ON DUPLICATE KEY UPDATE id=1;
insert into board values (2, '익명 글 2', now(),null, now(),now(),'2021 하반기 공채입니다 2', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=2;
insert into board values (3, '익명 글 3',now(), null, now(),now(),'2021 하반기 공채입니다 3', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=3;
insert into board values (4, '익명 글 4', now(),null, now(),now(),'2021 하반기 공채입니다 4', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=4;
insert into board values (5, '익명 글 5', now(),null, now(),now(),'2021 하반기 공채입니다 5', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=5;
insert into board values (6, '익명 글 6', now(),null, now(),now(),'2021 하반기 공채입니다 6', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=6;
insert into board values (7, '익명 글 7', now(),null, now(),now(),'2021 하반기 공채입니다 7', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=7;
insert into board values (8, '익명 글 8', now(),null, now(),now(),'2021 하반기 공채입니다 8', 20,2,'test-1')  ON DUPLICATE KEY UPDATE id=8;
insert into board values (9, '2021 하반기 공채 내용 9', now(),null, now(),now(),'2021 하반기 공채입니다 9', 20,4,'test-1')  ON DUPLICATE KEY UPDATE id=9;
insert into board values (10, '2021 하반기 공채 내용 10', now(),null, now(),now(),'2021 하반기 공채입니다 10', 20,4,'test-1')  ON DUPLICATE KEY UPDATE id=10;
insert into board values (11, '2021 하반기 공채 내용 11', now(),null, now(),now(),'2021 하반기 공채입니다 11', 20,4,'test-1')  ON DUPLICATE KEY UPDATE id=11;
insert into board values (12, '2021 하반기 공채 내용 12', now(),null, now(),now(),'2021 하반기 공채입니다 12', 20,4,'test-1')  ON DUPLICATE KEY UPDATE id=12;
insert into board values (13, '2021 하반기 공채 내용 13', now(),null, now(),now(),'2021 하반기 공채입니다 13', 20,5,'test-1')  ON DUPLICATE KEY UPDATE id=13;
insert into board values (14, '2021 하반기 공채 내용 14', now(),null, now(),now(),'2021 하반기 공채입니다 14', 20,5,'test-1')  ON DUPLICATE KEY UPDATE id=14;
insert into board values (15, '2021 하반기 공채 내용 15', now(),null, now(),now(),'2021 하반기 공채입니다 15', 20,5,'test-1')  ON DUPLICATE KEY UPDATE id=15;


-- 사용자가 게시물 마크 초기 데이터 sql
insert into user_marker_board values(1, 1, 'test-1') ON DUPLICATE KEY UPDATE id=1;
insert into user_marker_board values(2, 5, 'test-1') ON DUPLICATE KEY UPDATE id=2;
insert into user_marker_board values(3, 10, 'test-1') ON DUPLICATE KEY UPDATE id=3;
insert into user_marker_board values(4, 11, 'admin') ON DUPLICATE KEY UPDATE id=4;
insert into user_marker_board values(5, 12, 'admin') ON DUPLICATE KEY UPDATE id=5;
insert into user_marker_board values(6, 13, 'admin') ON DUPLICATE KEY UPDATE id=6;

-- 게시판 댓글 초기 데이터 sql
insert into board_comment values (1, "좋은 공지 감사합니다. ~~~ 오늘도 화이팅", now(), 1, "admin") ON DUPLICATE KEY UPDATE id=1;
insert into board_comment values (2, "행복한 하루 되시길", now(), 1, "test-1") ON DUPLICATE KEY UPDATE id=2;