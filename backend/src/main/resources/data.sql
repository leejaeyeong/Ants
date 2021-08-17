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
insert into department (id, department_name) values (4, "마케팅 전략팀") ON DUPLICATE KEY UPDATE id = 4;
-- 사용자 초기데이터 sql
insert into user (id, email, holiday, name, password, position, profile_location, state, user_id, department)
values (1, 'last9500@naver.com', 14, '정범진', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '부장', '/media/profile/wjdqjawls1/wjdqjawls1.png', 1, 'wjdqjawls1', 1)
ON DUPLICATE KEY
UPDATE id = 1;
insert into user (id, email, holiday, name, password, position, profile_location, state, user_id, department)
values (2, 'giver12@naver.com', 14, '이재영', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '대리', '/media/profile/dlwodud1/이재영.jpg', 1, 'dlwodud1', 1)
ON DUPLICATE KEY
UPDATE id = 2;
insert into user (id, email, holiday, name, password, position, profile_location, state, user_id, department)
values (3, 'jiahn.dev@gmail.com', 14, '김지안', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '대리', '/media/profile/rlawldks1/test.gif', 1, 'rlawldks1', 1)
ON DUPLICATE KEY
UPDATE id = 3;
insert into user (id, email, holiday, name, password, position, profile_location, state, user_id, department)
values (4, '', 14, '황지훈', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '대리', '/media/profile/ghkdwlgns1/황지훈.jpg', 1, 'ghkdwlgns1', 1)
ON DUPLICATE KEY
UPDATE id = 4;

-- 출근 기록 sql
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-02', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-03', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-04', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-05', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-06', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-09', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-10', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-11', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-12', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-13', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-16', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-17', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-18', 'dlwodud1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-19', 'dlwodud1');

insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-02', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-03', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-04', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-05', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-06', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-09', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-10', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-11', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-12', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-13', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-16', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-17', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-18', 'rlawldks1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-19', 'rlawldks1');

insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-02', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-03', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-04', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-05', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-06', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-09', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-10', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-11', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-12', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-13', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-16', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-17', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-18', 'ghkdwlgns1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-19', 'ghkdwlgns1');

insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-02', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-03', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-04', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-05', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-06', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-09', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-10', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-11', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-12', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-13', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-16', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-17', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-18', 'wjdqjawls1');
insert into attendance values (null, '09:00:30', '18:00:30', '2021-08-19', 'wjdqjawls1');

-- 투두 리스트 초기 sql


-- 게시물 타입 초기데이터 sql
insert into board_type values(1, "공지사항 게시판") ON DUPLICATE KEY UPDATE id=1;
insert into board_type values(2, "익명 게시판") ON DUPLICATE KEY UPDATE id=2;
insert into board_type values(3, "프론트엔드 개발팀") ON DUPLICATE KEY UPDATE id=3;
insert into board_type values(4, "백엔드 개발팀") ON DUPLICATE KEY UPDATE id=4;
insert into board_type values(5, "플랫폼 디자인팀") ON DUPLICATE KEY UPDATE id=5;
insert into board_type values(6, "마케팅 전략팀") ON DUPLICATE KEY UPDATE id=6;

-- 게시물 초기 데이터 sql
-- id, 내용, 날짜, 이미지, 등록 날짜 시간, 등록 시간, 제목, 조회수, 게시판 타입, 작성자
insert into board values (1, '공지사항 내용입니다.', '2021-08-02',null, '2021-08-02 00:55:38.000000',now(),'[필독]주간 업무 공유 관련 공지사항입니다! 모두 확인바랍니다!', 13,1,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=1;
insert into board values (2, '익명 글 2', '2021-08-02', null, '2021-08-02 00:55:38.000000',now(),'오늘 있었던 따뜻한 일 공유합니다ㅎㅎ', 20,2,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=2;
insert into board values (3, '익명 글 3', '2021-08-02', null, '2021-08-02 00:55:38.000000',now(),'익명의 힘을 빌려 써봅니다 ^^', 20,2,'dlwodud1')  ON DUPLICATE KEY UPDATE id=3;
insert into board values (4, '익명 글 4', '2021-08-03', null, '2021-08-03 00:55:38.000000',now(),'프론트엔드 부서 금일 회의 진행사항 공유', 20,3,'rlawldks1')  ON DUPLICATE KEY UPDATE id=4;
insert into board values (5, '익명 글 5', '2021-08-03',null, '2021-08-03 00:55:38.000000',now(),'프론트엔드 유용한 웹 사이트 공유', 20,3,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=5;
insert into board values (6, '익명 글 6', '2021-08-03',null, '2021-08-03 00:55:38.000000',now(),'백엔드 업무 프로세스 공유 공지입니다.', 20,4,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=6;
insert into board values (7, '익명 글 7', '2021-08-04',null, '2021-08-04 00:55:38.000000',now(),'2021 OOO 컨퍼런스 사내 지원자 모집!', 20,4,'rlawldks1')  ON DUPLICATE KEY UPDATE id=7;
insert into board values (8, '익명 글 8', '2021-08-04',null, '2021-08-04 00:55:38.000000',now(),'2021 하반기 공채입니다 8', 20,4,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=8;
insert into board values (9, '2021 하반기 공채 내용 9', '2021-08-05',null, '2021-08-05 00:55:38.000000',now(),'Ants 플랫폼 디자인 초안 공유', 20,5,'dlwodud1')  ON DUPLICATE KEY UPDATE id=9;
insert into board values (10, '2021 하반기 공채 내용 10', '2021-08-05',null, '2021-08-05 00:55:38.000000',now(),'사이드 디자인 수정 제안 공지', 20,5,'dlwodud1')  ON DUPLICATE KEY UPDATE id=10;
insert into board values (11, '2021 하반기 공채 내용 11', '2021-08-05',null, '2021-08-05 00:55:38.000000',now(),'차주 부터 반영되는 업무 프로세스 공지', 20,5,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=11;
insert into board values (12, '2021 하반기 공채 내용 12', '2021-08-05',null, '2021-08-05 00:55:38.000000',now(),'Ants UCC 시나리오 구상 제안', 20,6,'rlawldks1')  ON DUPLICATE KEY UPDATE id=12;
insert into board values (13, '2021 하반기 공채 내용 13', '2021-08-06',null, '2021-08-06 00:55:38.000000',now(),'발표 ppt 제작 양식 모음', 20,6,'rlawldks1')  ON DUPLICATE KEY UPDATE id=13;
insert into board values (14, '2021 하반기 공채 내용 14', '2021-08-06',null, '2021-08-06 00:55:38.000000',now(),'금일 마케팅 전략 회의 안내입니다.', 20,6,'rlawldks1')  ON DUPLICATE KEY UPDATE id=14;
insert into board values (15, '2021 하반기 공채 내용 15', '2021-08-06',null, '2021-08-06 00:55:38.000000',now(),'이번 한 주도 모두 수고하셨습니다^^', 20,6,'dlwodud1')  ON DUPLICATE KEY UPDATE id=15;


-- 사용자가 게시물 마크 초기 데이터 sql
insert into user_marker_board values(1, 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=1;
insert into user_marker_board values(2, 5, 'dlwodud1') ON DUPLICATE KEY UPDATE id=2;
insert into user_marker_board values(3, 10, 'rlawldks1') ON DUPLICATE KEY UPDATE id=3;
insert into user_marker_board values(4, 11, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=4;
insert into user_marker_board values(5, 12, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=5;
insert into user_marker_board values(6, 13, 'rlawldks1') ON DUPLICATE KEY UPDATE id=6;

-- 게시판 댓글 초기 데이터 sql
insert into board_comment values (1, "좋은 공지 감사합니다. ~~~ 오늘도 화이팅", now(), 1, "dlwodud1") ON DUPLICATE KEY UPDATE id=1;
insert into board_comment values (2, "행복한 하루 되시길", now(), 1, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=2;