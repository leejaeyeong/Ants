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
values (1, 'last9500@naver.com', 14, '정범진', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '부장', 'https://user-images.githubusercontent.com/41180841/129883265-5cc16260-8429-4573-934c-140eff52dbe6.png', 1, 'wjdqjawls1', 1)
ON DUPLICATE KEY
UPDATE id = 1;
insert into user (id, email, holiday, name, password, position, profile_location, state, user_id, department)
values (2, 'giver12@naver.com', 14, '이재영', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '대리', 'https://user-images.githubusercontent.com/41180841/129883237-75d76154-c111-4fa3-ad57-729ee667fb05.jpg', 1, 'dlwodud1', 1)
ON DUPLICATE KEY
UPDATE id = 2;
insert into user (id, email, holiday, name, password, position, profile_location, state, user_id, department)
values (3, 'jiahn.dev@gmail.com', 14, '김지안', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '대리', 'https://user-images.githubusercontent.com/41180841/129883271-2b921488-afb6-45eb-ba5c-019409f19685.jpg', 1, 'rlawldks1', 1)
ON DUPLICATE KEY
UPDATE id = 3;
insert into user (id, email, holiday, name, password, position, profile_location, state, user_id, department)
values (4, '', 14, '황지훈', '$2a$10$yzciyMkcjrqFfGQvb38bsOZzK9G9uoCvYbUEMJWgoC..F9HNHBQWy', '대리', 'https://user-images.githubusercontent.com/41180841/129883244-1cf99998-9850-4bd5-800e-f822f5209eec.jpg', 1, 'ghkdwlgns1', 1)
ON DUPLICATE KEY
UPDATE id = 4;

-- 출근 기록 sql
insert into attendance values (1, '09:05:30', '18:11:00', '2021-08-02', 'dlwodud1') ON DUPLICATE KEY UPDATE id=1;
insert into attendance values (2, '08:47:30', '18:05:02', '2021-08-03', 'dlwodud1') ON DUPLICATE KEY UPDATE id=2;
insert into attendance values (3, '08:55:30', '18:20:31', '2021-08-04', 'dlwodud1') ON DUPLICATE KEY UPDATE id=3;
insert into attendance values (4, '09:01:32', '18:30:30', '2021-08-05', 'dlwodud1') ON DUPLICATE KEY UPDATE id=4;
insert into attendance values (5, '09:03:31', '18:06:21', '2021-08-06', 'dlwodud1') ON DUPLICATE KEY UPDATE id=5;
insert into attendance values (6, '09:02:32', '18:11:33', '2021-08-09', 'dlwodud1') ON DUPLICATE KEY UPDATE id=6;
insert into attendance values (7, '09:21:21', '18:12:32', '2021-08-10', 'dlwodud1') ON DUPLICATE KEY UPDATE id=7;
insert into attendance values (8, '10:05:31', '18:15:31', '2021-08-11', 'dlwodud1') ON DUPLICATE KEY UPDATE id=8;
insert into attendance values (9, '08:50:31', '18:07:21', '2021-08-12', 'dlwodud1') ON DUPLICATE KEY UPDATE id=9;
insert into attendance values (10, '08:54:31', '18:22:11', '2021-08-13', 'dlwodud1') ON DUPLICATE KEY UPDATE id=10;
insert into attendance values (11, '08:44:22', '18:50:50', '2021-08-16', 'dlwodud1') ON DUPLICATE KEY UPDATE id=11;
insert into attendance values (12, '08:59:30', '18:33:11', '2021-08-17', 'dlwodud1') ON DUPLICATE KEY UPDATE id=12;
insert into attendance values (13, '08:55:50', '18:21:20', '2021-08-18', 'dlwodud1') ON DUPLICATE KEY UPDATE id=13;
insert into attendance values (14, '09:02:41', '18:11:32', '2021-08-19', 'dlwodud1') ON DUPLICATE KEY UPDATE id=14;

insert into attendance values (15, '09:00:30', '18:00:30', '2021-08-02', 'rlawldks1') ON DUPLICATE KEY UPDATE id=15;
insert into attendance values (16, '09:00:30', '18:00:30', '2021-08-03', 'rlawldks1') ON DUPLICATE KEY UPDATE id=16;
insert into attendance values (17, '09:00:30', '18:00:30', '2021-08-04', 'rlawldks1') ON DUPLICATE KEY UPDATE id=17;
insert into attendance values (18, '09:00:30', '18:00:30', '2021-08-05', 'rlawldks1') ON DUPLICATE KEY UPDATE id=18;
insert into attendance values (19, '09:00:30', '18:00:30', '2021-08-06', 'rlawldks1') ON DUPLICATE KEY UPDATE id=19;
insert into attendance values (20, '09:00:30', '18:00:30', '2021-08-09', 'rlawldks1') ON DUPLICATE KEY UPDATE id=20;
insert into attendance values (21, '09:00:30', '18:00:30', '2021-08-10', 'rlawldks1') ON DUPLICATE KEY UPDATE id=21;
insert into attendance values (22, '09:00:30', '18:00:30', '2021-08-11', 'rlawldks1') ON DUPLICATE KEY UPDATE id=22;
insert into attendance values (23, '09:00:30', '18:00:30', '2021-08-12', 'rlawldks1') ON DUPLICATE KEY UPDATE id=23;
insert into attendance values (24, '09:00:30', '18:00:30', '2021-08-13', 'rlawldks1') ON DUPLICATE KEY UPDATE id=24;
insert into attendance values (25, '09:00:30', '18:00:30', '2021-08-16', 'rlawldks1') ON DUPLICATE KEY UPDATE id=25;
insert into attendance values (26, '09:00:30', '18:00:30', '2021-08-17', 'rlawldks1') ON DUPLICATE KEY UPDATE id=26;
insert into attendance values (27, '09:00:30', '18:00:30', '2021-08-18', 'rlawldks1') ON DUPLICATE KEY UPDATE id=27;
insert into attendance values (28, '09:00:30', '18:00:30', '2021-08-19', 'rlawldks1') ON DUPLICATE KEY UPDATE id=28;

insert into attendance values (29, '09:00:30', '18:00:30', '2021-08-02', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=29;
insert into attendance values (30, '09:00:30', '18:00:30', '2021-08-03', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=30;
insert into attendance values (31, '09:00:30', '18:00:30', '2021-08-04', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=31;
insert into attendance values (32, '09:00:30', '18:00:30', '2021-08-05', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=32;
insert into attendance values (33, '09:00:30', '18:00:30', '2021-08-06', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=33;
insert into attendance values (34, '09:00:30', '18:00:30', '2021-08-09', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=34;
insert into attendance values (35, '09:00:30', '18:00:30', '2021-08-10', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=35;
insert into attendance values (36, '09:00:30', '18:00:30', '2021-08-11', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=36;
insert into attendance values (37, '09:00:30', '18:00:30', '2021-08-12', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=37;
insert into attendance values (38, '09:00:30', '18:00:30', '2021-08-13', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=38;
insert into attendance values (39, '09:00:30', '18:00:30', '2021-08-16', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=39;
insert into attendance values (40, '09:00:30', '18:00:30', '2021-08-17', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=40;
insert into attendance values (41, '09:00:30', '18:00:30', '2021-08-18', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=41;
insert into attendance values (42, '09:00:30', '18:00:30', '2021-08-19', 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=42;

insert into attendance values (43, '09:00:30', '18:00:30', '2021-08-02', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=43;
insert into attendance values (44, '09:00:30', '18:00:30', '2021-08-03', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=44;
insert into attendance values (45, '09:00:30', '18:00:30', '2021-08-04', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=45;
insert into attendance values (46, '09:00:30', '18:00:30', '2021-08-05', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=46;
insert into attendance values (47, '09:00:30', '18:00:30', '2021-08-06', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=47;
insert into attendance values (48, '09:00:30', '18:00:30', '2021-08-09', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=48;
insert into attendance values (49, '09:00:30', '18:00:30', '2021-08-10', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=49;
insert into attendance values (50, '09:00:30', '18:00:30', '2021-08-11', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=50;
insert into attendance values (51, '09:00:30', '18:00:30', '2021-08-12', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=51;
insert into attendance values (52, '09:00:30', '18:00:30', '2021-08-13', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=52;
insert into attendance values (53, '09:00:30', '18:00:30', '2021-08-16', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=53;
insert into attendance values (54, '09:00:30', '18:00:30', '2021-08-17', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=54;
insert into attendance values (55, '09:00:30', '18:00:30', '2021-08-18', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=55;
insert into attendance values (56, '09:00:30', '18:00:30', '2021-08-19', 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=56;

-- 투두 리스트 초기 sql
insert into todo values (1, '2021-08-02',0, '09:30:00', '업무 보고 회의', 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=1;
insert into todo values (2, '2021-08-02',1, '15:30:00', '페어 프로그래밍 with 지안', 1, 'rlawldks1') ON DUPLICATE KEY UPDATE id=2;
insert into todo values (3, '2021-08-03',1, '10:30:00', '신입 가이드 자료 준비하기', 1, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=3;
insert into todo values (4, '2021-08-03',1, '17:30:00', '라이브러리 조사하기', 1, 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=4;
insert into todo values (5, '2021-08-04',0, '11:30:00', '팀미팅 준비하기', 1, 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=5;
insert into todo values (6, '2021-08-04',1, '12:30:00', '발표 자료 세부 수정 및 조율', 1, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=6;
insert into todo values (7, '2021-08-05',1, '18:30:00', '보고 메일 보내기 -> 정범진', 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=7;
insert into todo values (8, '2021-08-05',1, '16:30:00', '미완료 작업 체크', 1, 'rlawldks1') ON DUPLICATE KEY UPDATE id=8;
insert into todo values (9, '2021-08-06',0, '17:30:00', '자료실 파일 업로드', 1, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=9;
insert into todo values (10, '2021-08-06',1, '11:30:32', '선행 기술 조사하기', 1, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=10;
insert into todo values (11, '2021-08-09',0, '15:30:00', 'WebRTC 관련 조사', 1, 'rlawldks1') ON DUPLICATE KEY UPDATE id=11;
insert into todo values (12, '2021-08-09',1, '15:46:32', '팀 회의 준비', 1, 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=12;
insert into todo values (13, '2021-08-10',0, '12:30:00', '발표 ppt 제작', 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=13;
insert into todo values (14, '2021-08-10',1, '15:30:00', '팀 로고 정하기', 1, 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=14;
insert into todo values (15, '2021-08-11',0, '10:30:00', '역할 분담 다시하기', 1, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=15;
insert into todo values (16, '2021-08-11',1, '16:30:00', '주간 회의 준비, 내일까지', 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=16;
insert into todo values (17, '2021-08-12',0, '13:30:00', '소스코드 버그 수정', 1, 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=17;
insert into todo values (18, '2021-08-12',1, '15:30:00', 'readme 파일 작성, 오타수정', 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=18;
insert into todo values (19, '2021-08-13',0, '13:30:00', '코드 리뷰 준비', 1, 'wjdqjawls1') ON DUPLICATE KEY UPDATE id=19;
insert into todo values (20, '2021-08-13',1, '15:30:00', '메인화면 UI/UX 개선', 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=20;
insert into todo values (21, '2021-08-16',0, '14:00:00', '백엔드 서비스 코드 리팩토링', 1, 'rlawldks1') ON DUPLICATE KEY UPDATE id=21;
insert into todo values (22, '2021-08-16',1, '14:32:00', '메인 페이지 버그 수정', 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=22;
insert into todo values (23, '2021-08-17',0, '13:22:31', '발표 준비하기(10분 분량)', 1, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=23;
insert into todo values (24, '2021-08-17',1, '17:11:00', '서버 통신 버그 수정', 1, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=24;
insert into todo values (25, '2021-08-17',0, '12:10:00', '메인 사이드바 구현(디테일)', 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=25;
insert into todo values (26, '2021-08-18',1, '11:05:00', '전반적으로 테스트케이스 작성', 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=26;
insert into todo values (27, '2021-08-18',0, '11:07:00', '화면 정의서 수정', 1, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=27;
insert into todo values (28, '2021-08-18',1, '18:30:00', '서버 로컬 시간 확인하기', 1, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=28;
insert into todo values (29, '2021-08-19',1, '11:30:00', '소켓 통신 확인', 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=29;
insert into todo values (30, '2021-08-19',1, '11:30:00', '주간 회의 준비', 1, 'rlawldks1') ON DUPLICATE KEY UPDATE id=30;


-- 게시물 타입 초기데이터 sql
insert into board_type values(1, "공지사항 게시판") ON DUPLICATE KEY UPDATE id=1;
insert into board_type values(2, "익명 게시판") ON DUPLICATE KEY UPDATE id=2;
insert into board_type values(3, "프론트엔드 개발팀") ON DUPLICATE KEY UPDATE id=3;
insert into board_type values(4, "백엔드 개발팀") ON DUPLICATE KEY UPDATE id=4;
insert into board_type values(5, "플랫폼 디자인팀") ON DUPLICATE KEY UPDATE id=5;
insert into board_type values(6, "마케팅 전략팀") ON DUPLICATE KEY UPDATE id=6;

-- 게시물 초기 데이터 sql
-- id, 내용, 날짜, 이미지, 등록 날짜 시간, 등록 시간, 제목, 조회수, 게시판 타입, 작성자
insert into board values (1, '한 주간 다들 고생 많으셨습니다.\n\n 다음주도 화이팅 치킨 쏩니다.\n\n 다들 문자 확인 ㄱㄱ', '2021-08-02',null, '2021-08-02 11:55:38.000000','11:55:38','[필독]주간 업무 공유 관련 공지사항입니다! 모두 확인바랍니다!', 13,1,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=1;
insert into board values (2, '오늘 회사 출근하다가 지갑 잃어버렸는데, 옆 부서 XX 부장님이 지갑 찾아주심', '2021-08-02', null, '2021-08-02 09:55:38.000000','09:55:38','오늘 있었던 따뜻한 일 공유합니다ㅎㅎ', 20,2,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=2;
insert into board values (3, '나는 슈퍼맨이다', '2021-08-02', null, '2021-08-02 08:55:38.000000','08:55:38','익명의 힘을 빌려 써봅니다 ^^', 20,2,'dlwodud1')  ON DUPLICATE KEY UPDATE id=3;
insert into board values (4, '다음주부터 본격적으로 프론트 개발 돌입하기로 했습니다.\n\n 앞으로 주기적으로 업무 공유하겠습니다.', '2021-08-03', null, '2021-08-03 11:55:38.000000','11:55:38','프론트엔드 부서 금일 회의 진행사항 공유', 20,3,'rlawldks1')  ON DUPLICATE KEY UPDATE id=4;
insert into board values (5, '유용한 사이트 자료실에 웹사이트 공유.pdf로 공유했습니다.', '2021-08-03',null, '2021-08-03 12:55:38.000000','12:55:38','프론트엔드 유용한 웹 사이트 공유', 20,3,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=5;
insert into board values (6, '업무 프로세스 관련한 파일 자료 공유실에 업로드했습니다.', '2021-08-03',null, '2021-08-03 13:55:38.000000','13:55:38','백엔드 업무 프로세스 공유 공지입니다.', 20,4,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=6;
insert into board values (7, '2021 OOO 컨퍼런스 관련해서 지원자를 모집하고 있습니다. \n\n 4성급 호텔 숙박지원 예정이고 회사 대표로 가는 것인 만큼 모범이 되어주시길 바랍니다.', '2021-08-04',null, '2021-08-04 14:55:38.000000','14:55:38','2021 OOO 컨퍼런스 사내 지원자 모집!', 20,4,'rlawldks1')  ON DUPLICATE KEY UPDATE id=7;
insert into board values (8, '아 2021년 상반기 알차게 보냈다 다들 그렇죠? 앞으로도 화이팅합니다 \n\n - Ants 개발자 일동 -', '2021-08-04',null, '2021-08-04 14:55:38.000000','14:55:38','[필독]프론트엔드 개발팀 회고', 20,4,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=8;
insert into board values (9, '디자인 관련한 파일 자료 공유실에 업로드했습니다.', '2021-08-05',null, '2021-08-05 12:55:38.000000','12:55:38','Ants 플랫폼 디자인 초안 공유', 20,5,'dlwodud1')  ON DUPLICATE KEY UPDATE id=9;
insert into board values (10, '사이드바 색깔 누가 바꿨어 이뻤는데 ㅡ.ㅡ', '2021-08-05',null, '2021-08-05 11:55:38.000000','11:55:38','사이드 디자인 수정 제안 공지', 20,5,'dlwodud1')  ON DUPLICATE KEY UPDATE id=10;
insert into board values (11, '다음주부터는 열심히하는 프로세스입니다. \n\n 다들 숙지.', '2021-08-05',null, '2021-08-05 13:55:38.000000','13:55:38','차주 부터 반영되는 업무 프로세스 공지', 20,5,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=11;
insert into board values (12, 'UCC에 주인공으로 출연한 Ant 모집합니다. <-- [매우 중요]', '2021-08-05',null, '2021-08-05 15:55:38.000000','15:55:38','Ants UCC 시나리오 구상 제안', 20,6,'rlawldks1')  ON DUPLICATE KEY UPDATE id=12;
insert into board values (13, '자료실에 올렸슴다', '2021-08-06',null, '2021-08-06 09:55:38.000000','09:55:38','발표 ppt 제작 양식 모음', 20,6,'rlawldks1')  ON DUPLICATE KEY UPDATE id=13;
insert into board values (14, '마케팅팀 실적이 저조합니다. 전략적으로 이목을 집중시킬 무엇인가 필요합니다.\n\n 다음주까지 분발해주시고 화이팅합시다. \n\n ps. 오늘 회식 있음.', '2021-08-06',null, '2021-08-06 10:55:38.000000','10:55:38','금일 마케팅 전략 회의 안내입니다.', 20,6,'rlawldks1')  ON DUPLICATE KEY UPDATE id=14;
insert into board values (15, '수고하셨습니다.', '2021-08-06',null, '2021-08-06 17:55:38.000000','17:55:38','이번 한 주도 모두 수고하셨습니다^^', 20,6,'dlwodud1')  ON DUPLICATE KEY UPDATE id=15;
insert into board values (16, '부서 이전입니다. \n 장소 : 본사 건물 6층 305호 \n 9월부터 공사 들어갈 예정이며, 완공은 21년 11월 예상됩니다.', '2021-08-09',null, '2021-08-09 10:32:38.000000','10:32:38','프론트엔드 부서 이전 공고', 20,3,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=16;
insert into board values (17, '이번에 고양이를 새로 위탁받았는데 \n 처음 키워봐서...ㅠㅠㅠ \n 처음에 사료랑 세팅을 어떻게 해야되죠??', '2021-08-09',null, '2021-08-09 15:22:13.000000','15:22:13','고민이 있어요', 19,2,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=17;
insert into board values (18, '새로 하게 되는 S1 프로젝트 Deployment 자료 배포합니다. \n 프론트엔드 부서는 필독해주세요', '2021-08-09',null, '2021-08-09 17:22:35.000000','17:22:35','S1 프로젝트 Deployment 자료', 23,3,'rlawldks1')  ON DUPLICATE KEY UPDATE id=18;
insert into board values (19, '새로 하게 되는 S1 프로젝트 화면설계서 배포합니다. \n 프론트엔드 부서는 필독해주세요', '2021-08-10',null, '2021-08-10 12:12:44.000000','12:12:44','S1 프로젝트 화면설계서', 24,3,'dlwodud1')  ON DUPLICATE KEY UPDATE id=19;
insert into board values (20, '새로 하게 되는 S1 프로젝트 명세서 자료 배포합니다. \n 프론트엔드 부서는 필독해주세요', '2021-08-10',null, '2021-08-10 13:25:54.000000','13:25:54','[프론트엔드 필독] S1 프로젝트 명세서', 28,3,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=20;
insert into board values (21, '하계 워크샵 계획입니다. \n 각 부서별로 지정된 장소로 이동 예정이며 \n 불참인원은 부서 장들께 사유 알림 부탁드립니다.', '2021-08-10',null, '2021-08-10 16:43:22.000000','16:43:22','하계 워크샵 계획서', 19,1,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=21;
insert into board values (22, '하계 워크샵 참가자 명단입니다. \n 일정은 추후 공지합니다.', '2021-08-11',null, '2021-08-11 09:32:43.000000','09:32:43','하계 워크샵 참가자 명단', 20,1,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=22;
insert into board values (23, 'S1 프로젝트 스토리보드 자료 배포합니다. \n 프론트엔드 부서는 필독해주세요', '2021-08-11',null, '2021-08-11 11:55:38.000000','11:55:38','S1 프로젝트 스토리보드', 20,3,'rlawldks1')  ON DUPLICATE KEY UPDATE id=23;
insert into board values (24, 'S1 프로젝트 추가 명세사항입니다. \n 프론트엔드 부서는 필독해주세요', '2021-08-12',null, '2021-08-12 09:35:54.000000','09:35:54','S1 프로젝트 추가 명세사항', 24,3,'dlwodud1')  ON DUPLICATE KEY UPDATE id=24;
insert into board values (25, 'S1 프로젝트 백엔드 부서 요청사항입니다. \n 프론트엔드 부서는 필독해주세요', '2021-08-12',null, '2021-08-12 14:22:43.000000','14:22:43','백엔드 부서 요청사항', 24,3,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=25;
insert into board values (26, '고양이 사진 올리려는데 사진을 안찍어와서 못올림ㅠ \n 다음에 다시 도전합니다 하하', '2021-08-12',null, '2021-08-12 15:36:54.000000','15:36:54','모두 힐링하고 가세요~', 18,2,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=26;
insert into board values (27, '평소 듣고 계시는 플레이 리스트 공유받고 싶어요~! \n 좋은 음악 공유합시다 여러분 ^^', '2021-08-13',null, '2021-08-13 11:56:32.000000','11:56:32','음악 공유 부탁드립니다', 21,2,'rlawldks1')  ON DUPLICATE KEY UPDATE id=27;
insert into board values (28, '아무것도 없었다', '2021-08-13',null, '2021-08-13 17:20:51.000000','17:20:51','이거 좀 봐주세요', 17,2,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=28;
insert into board values (29, '하계 워크샵 장소 및 일정입니다. \n 참가 인원은 참고 부탁드립니다. ', '2021-08-13',null, '2021-08-13 17:55:38.000000','17:55:38','하계 워크샵 장소 및 일정', 23,1,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=29;
insert into board values (30, 'S1 프로젝트 디자인 구성안입니다. \n 프론트엔드 부서는 필독해주세요', '2021-08-16',null, '2021-08-16 09:33:38.000000','09:33:38','S1 프로젝트 디자인 구성안', 15,3,'rlawldks1')  ON DUPLICATE KEY UPDATE id=30;
insert into board values (31, 'S1 프로젝트 디자인 스택 명세서입니다. \n 프론트엔드 부서는 필독해주세요', '2021-08-16',null, '2021-08-16 11:35:22.000000','11:35:22','S1 프로젝트 디자인 스택 명세서', 16,3,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=31;
insert into board values (32, '이번에 이사하면서 안쓰게 된 물건들 나눔합니다~ \n 아래 댓글 달아주세요~!', '2021-08-17',null, '2021-08-17 14:25:42.000000','14:25:42','나눔해요 우리~', 13,2,'dlwodud1')  ON DUPLICATE KEY UPDATE id=32;
insert into board values (33, '사내 E-SPORTS 대회를 개최합니다. \n 종목 : LOL \n  일시 : 21년 9월 10일 (금) \n 총 모집 인원 : 5인 1팀으로 총 10팀 \n 방식 : 풀리그 \n 팀장만 아래 댓글로 팀원 명단 작성해주세요', '2021-08-17',null, '2021-08-17 15:10:38.000000','15:10:38','사내 E-SPORTS 대회 알림', 24,1,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=33;
insert into board values (34, '사내 E-SPORTS 대회 참가자 팀 구합니다. \n 필자 실력은 골드입니다.', '2021-08-17',null, '2021-08-17 09:15:32.000000','09:15:32','참가자 구합니다', 22,2,'dlwodud1')  ON DUPLICATE KEY UPDATE id=34;
insert into board values (35, 'S1 프로젝트 진행상황입니다. \n 프론트엔드 부서는 필독해주세요', '2021-08-18',null, '2021-08-18 11:15:38.000000','11:15:38','S1 프로젝트 진행상황 v1', 23,3,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=35;
insert into board values (36, 'S1 프로젝트 일일회의 정리자료입니다. \n 프론트엔드 부서는 매일 회의결과 인지해주세요', '2021-08-18',null, '2021-08-18 16:20:21.000000','16:20:21','S1 프로젝트 일일 수정사항', 24,3,'rlawldks1')  ON DUPLICATE KEY UPDATE id=36;
insert into board values (37, '사내 E-SPORTS 대회 참가자 명단입니다. \n 추가 참가자 모집도 받고 있으니 추가 참가 요청은 이후 공지 참고바랍니다.', '2021-08-19',null, '2021-08-19 09:54:38.000000','09:54:38','사내 E-SPORTS 대회 참가자 명단', 14,1,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=37;
insert into board values (38, 'S1 프로젝트 일일회의 정리자료입니다. \n 프론트엔드 부서는 매일 회의결과 인지해주세요', '2021-08-19',null, '2021-08-19 15:52:29.000000','15:52:29','S1 프로젝트 일일 수정사항', 22,3,'rlawldks1')  ON DUPLICATE KEY UPDATE id=38;
insert into board values (39, 'S1 프로젝트 일일회의 정리자료입니다. \n 프론트엔드 부서는 매일 회의결과 인지해주세요', '2021-08-20',null, '2021-08-20 10:02:05.000000','10:02:05','S1 프로젝트 일일 수정사항', 22,3,'rlawldks1')  ON DUPLICATE KEY UPDATE id=39;
insert into board values (40, 'E-SPORTS 대회 알람을 이제알아서ㅠㅠㅠ \n 뒤늦게 참가자 구하시는 분들! 구제해주세요 ㅠㅠ', '2021-08-20',null, '2021-08-20 11:53:48.000000','11:53:48','뒤늦게 참가자 구해봅니다ㅠㅠ', 22,2,'ghkdwlgns1')  ON DUPLICATE KEY UPDATE id=40;
insert into board values (41, 'S1 프로젝트 클라이언트 측 추가 요청사항입니다. \n 프론트엔드 부서는 필독해주세요', '2021-08-20',null, '2021-08-20 12:52:43.000000','12:52:43','S1 프로젝트 클라이언트 추가 요구사항', 22,3,'wjdqjawls1')  ON DUPLICATE KEY UPDATE id=41;


-- 사용자가 게시물 마크 초기 데이터 sql
insert into user_marker_board values(1, 1, 'dlwodud1') ON DUPLICATE KEY UPDATE id=1;
insert into user_marker_board values(2, 5, 'dlwodud1') ON DUPLICATE KEY UPDATE id=2;
insert into user_marker_board values(3, 10, 'rlawldks1') ON DUPLICATE KEY UPDATE id=3;
insert into user_marker_board values(4, 11, 'dlwodud1') ON DUPLICATE KEY UPDATE id=4;
insert into user_marker_board values(5, 12, 'ghkdwlgns1') ON DUPLICATE KEY UPDATE id=5;
insert into user_marker_board values(6, 13, 'rlawldks1') ON DUPLICATE KEY UPDATE id=6;

-- 게시판 댓글 초기 데이터 sql
insert into board_comment values (1, "좋은 공지 감사합니다. ~~~ 오늘도 화이팅", '2021-08-02 22:52:43.000000', 1, "dlwodud1") ON DUPLICATE KEY UPDATE id=1;
insert into board_comment values (2, "확인했습니다", '2021-08-22 22:53:45.000000', 1, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=2;
insert into board_comment values (3, "좋네요~", '2021-08-02 22:54:43.000000', 2, "rlawldks1") ON DUPLICATE KEY UPDATE id=3;
insert into board_comment values (4, "고생하셨습니다 좋네요ㅎㅎ", '2021-08-03 22:55:22.000000', 3, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=4;
insert into board_comment values (5, "그렇군요..", '2021-08-03 22:51:32.000000', 3, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=5;
insert into board_comment values (6, "확인했습니다", '2021-08-03 22:54:31.000000', 4, "rlawldks1") ON DUPLICATE KEY UPDATE id=6;
insert into board_comment values (7, "확인이요!", '2021-08-03 22:56:31.000000', 4, "dlwodud1") ON DUPLICATE KEY UPDATE id=7;
insert into board_comment values (8, "잘 쓰겠습니다~!", '2021-08-03 22:57:31.000000', 5, "dlwodud1") ON DUPLICATE KEY UPDATE id=8;
insert into board_comment values (9, "고마워요^^", '2021-08-03 22:58:31.000000', 5, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=9;
insert into board_comment values (10, "확인했습니다.", '2021-08-03 22:56:33.000000', 6, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=10;
insert into board_comment values (11, "지원합니다!", '2021-08-04 22:56:33.000000', 7, "dlwodud1") ON DUPLICATE KEY UPDATE id=11;
insert into board_comment values (12, "저도 참가해봅니다!", '2021-08-04 22:57:52.000000', 7, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=12;
insert into board_comment values (13, "확인했습니다.", '2021-08-04 22:56:52.000000', 8, "rlawldks1") ON DUPLICATE KEY UPDATE id=13;
insert into board_comment values (14, "확인했습니다!", '2021-08-04 22:58:52.000000', 8, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=14;
insert into board_comment values (15, "넵!", '2021-08-05 10:31:52.000000', 9, "rlawldks1") ON DUPLICATE KEY UPDATE id=15;
insert into board_comment values (16, "넵", '2021-08-05 10:33:13.000000', 9, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=16;
insert into board_comment values (17, "확인", '2021-08-05 10:13:54.000000', 10, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=17;
insert into board_comment values (18, "확인했습니다!", '2021-08-05 10:32:15.000000', 10, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=18;
insert into board_comment values (19, "확인했습니다!", '2021-08-05 12:23:15.000000', 11, "rlawldks1") ON DUPLICATE KEY UPDATE id=19;
insert into board_comment values (20, "확인", '2021-08-05 13:10:15.000000', 11, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=20;
insert into board_comment values (21, "회의 한 번 열어서 진행하는 건 어떨까요?", '2021-08-05 15:10:15.000000', 12, "dlwodud1") ON DUPLICATE KEY UPDATE id=21;
insert into board_comment values (22, "좋아요~!", '2021-08-06 12:20:15.000000', 13, "dlwodud1") ON DUPLICATE KEY UPDATE id=22;
insert into board_comment values (23, "잘쓸게요~!", '2021-08-06 14:52:15.000000', 13, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=23;
insert into board_comment values (24, "확인", '2021-08-06 12:12:14.000000', 14, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=24;
insert into board_comment values (25, "확인했습니다", '2021-08-06 13:45:14.000000', 14, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=25;
insert into board_comment values (26, "모두 수고했어요~", '2021-08-06 17:45:14.000000', 15, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=26;
insert into board_comment values (27, "수고하셨습니다!", '2021-08-06 17:46:44.000000', 15, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=27;
insert into board_comment values (28, "확인했습니다.", '2021-08-09 13:02:44.000000', 16, "rlawldks1") ON DUPLICATE KEY UPDATE id=28;
insert into board_comment values (29, "우선 병원에 데려가서 검사부터 해보세요.", '2021-08-09 14:32:44.000000', 17, "dlwodud1") ON DUPLICATE KEY UPDATE id=29;
insert into board_comment values (30, "귀엽겠다ㅠ", '2021-08-09 14:59:44.000000', 17, "rlawldks1") ON DUPLICATE KEY UPDATE id=30;
insert into board_comment values (31, "확인", '2021-08-09 11:02:44.000000', 18, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=31;
insert into board_comment values (32, "확인했습니다", '2021-08-09 11:44:44.000000', 18, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=32;
insert into board_comment values (33, "확인했습니다", '2021-08-10 11:22:44.000000', 19, "rlawldks1") ON DUPLICATE KEY UPDATE id=33;
insert into board_comment values (34, "확인했습니다", '2021-08-10 11:55:44.000000', 19, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=34;
insert into board_comment values (35, "확인했습니다", '2021-08-10 10:03:44.000000', 20, "dlwodud1") ON DUPLICATE KEY UPDATE id=35;
insert into board_comment values (36, "확인했습니다", '2021-08-10 10:32:44.000000', 20, "rlawldks1") ON DUPLICATE KEY UPDATE id=36;
insert into board_comment values (37, "확인했습니다", '2021-08-10 10:44:44.000000', 20, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=37;
insert into board_comment values (38, "벌써 워크샵 가는 때가 왔군요.", '2021-08-10 13:49:44.000000', 21, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=38;
insert into board_comment values (39, "제이름이 없네요", '2021-08-11 14:50:44.000000', 22, "rlawldks1") ON DUPLICATE KEY UPDATE id=39;
insert into board_comment values (40, "ㅋㅋㅋㅋㅋ", '2021-08-11 14:51:44.000000', 22, "dlwodud1") ON DUPLICATE KEY UPDATE id=40;
insert into board_comment values (41, "확인", '2021-08-11 12:22:44.000000', 23, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=41;
insert into board_comment values (42, "확인", '2021-08-12 12:40:44.000000', 24, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=42;
insert into board_comment values (43, "날짜 알아보고 공지 드리겠습니다.", '2021-08-12 14:01:44.000000', 25, "rlawldks1") ON DUPLICATE KEY UPDATE id=43;
insert into board_comment values (44, "확인", '2021-08-12 15:09:44.000000', 25, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=44;
insert into board_comment values (45, "귀여워라ㅠㅠㅠㅠ", '2021-08-12 15:47:44.000000', 26, "rlawldks1") ON DUPLICATE KEY UPDATE id=45;
insert into board_comment values (46, "쌈자 노래 들으세용", '2021-08-13 16:32:44.000000', 27, "dlwodud1") ON DUPLICATE KEY UPDATE id=46;
insert into board_comment values (47, "쇼미더머늬", '2021-08-13 16:35:44.000000', 27, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=47;
insert into board_comment values (48, "굉장하군", '2021-08-13 17:35:44.000000', 28, "dlwodud1") ON DUPLICATE KEY UPDATE id=48;
insert into board_comment values (49, "철원 실화인가요?", '2021-08-13 15:34:44.000000', 29, "dlwodud1") ON DUPLICATE KEY UPDATE id=49;
insert into board_comment values (50, "확인", '2021-08-16 15:38:44.000000', 30, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=50;
insert into board_comment values (51, "확인", '2021-08-16 15:38:44.000000', 31, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=51;
insert into board_comment values (52, "냉장고 나눔 진짜인가요 이거? DM 주세요", '2021-08-17 17:00:44.000000', 32, "rlawldks1") ON DUPLICATE KEY UPDATE id=52;
insert into board_comment values (53, "대체 속옷은 왜 나눔하나요", '2021-08-17 17:59:44.000000', 32, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=53;
insert into board_comment values (54, "다들 들어오시죠", '2021-08-17 14:48:44.000000', 33, "dlwodud1") ON DUPLICATE KEY UPDATE id=54;
insert into board_comment values (55, "딱대", '2021-08-17 15:01:44.000000', 33, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=55;
insert into board_comment values (56, "미드 갑니다. DM 주세요", '2021-08-17 16:01:44.000000', 34, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=56;
insert into board_comment values (57, "확인", '2021-08-18 11:08:44.000000', 35, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=57;
insert into board_comment values (58, "재영님 이거 좀 빨리끝내주세요", '2021-08-18 13:54:44.000000', 36, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=58;
insert into board_comment values (59, "넵", '2021-08-18 13:55:44.000000', 36, "dlwodud1") ON DUPLICATE KEY UPDATE id=59;
insert into board_comment values (60, "다들 들어오시죠", '2021-08-19 15:06:44.000000', 37, "dlwodud1") ON DUPLICATE KEY UPDATE id=60;
insert into board_comment values (61, "오늘부터 지안님이 올리시는 일일수정사항 해결된 이슈대로 저한테 DM 부탁드립니다.", '2021-08-19 12:30:44.000000', 38, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=61;
insert into board_comment values (62, "넵", '2021-08-19 13:16:44.000000', 38, "dlwodud1") ON DUPLICATE KEY UPDATE id=62;
insert into board_comment values (63, "확인", '2021-08-20 15:15:44.000000', 39, "wjdqjawls1") ON DUPLICATE KEY UPDATE id=63;
insert into board_comment values (64, "이미 기간이 지나서ㅠㅠㅠ 죄송여", '2021-08-20 13:40:44.000000', 40, "dlwodud1") ON DUPLICATE KEY UPDATE id=64;
insert into board_comment values (65, "ㅠㅠㅠ", '2021-08-20 13:52:44.000000', 40, "ghkdwlgns1") ON DUPLICATE KEY UPDATE id=65;
insert into board_comment values (66, "확인했습니다.", '2021-08-20 12:44:44.000000', 41, "rlawldks1") ON DUPLICATE KEY UPDATE id=66;