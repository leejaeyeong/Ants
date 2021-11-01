# 🐜비대면 협업을 위한 그룹웨어 서비스 Ants
## **Ants**
 - **Ants**는 비대면 협업과 효율적인 업무 수행을 지원하는 웹 서비스입니다.
 - Ants는 상용화된 여러 그룹웨어 서비스를 모티브로 하고 있습니다.


## **브랜치 관리 전략**
![](https://images.velog.io/images/nunddu/post/0c7eb99c-771c-469b-bee2-4cc8d1d84c91/image.png)

Git Flow Branch 전략을 변형하여 브랜치를 관리하고 있습니다.   
`master` - 프로젝트 배포시 사용하는 브랜치입니다.  
`release` - 배포를 준비하는 단계에서 사용합니다.  
`development` - 기능이 추가될 때 사용하는 브랜치입니다.   
`개인 작업 브랜치` - 개인별 기능 개발을 진행하는 브랜치입니다.   

## **프로젝트 구성도**
![](https://images.velog.io/images/nunddu/post/5d026103-961f-4b2a-8988-676ffaf42a5f/image.png)

## ERD
![](https://images.velog.io/images/nunddu/post/185263d5-54f1-432d-bddc-5df4bcce4865/image.png)
## **주요 기능**
 - 로그인 / 회원가입
 - 사용자 근태관리(출/퇴근)
 - 실시간 화상회의 기능
 - 실시간 채팅 기능
 - 개인 메신저
 - 게시판 관리
 - 가입 승인 메일 전송
 - 그룹 관리 
 - 캘린더 관리

## **UI/UX**
<div align="center">
<figure>
    <img src="https://images.velog.io/images/nunddu/post/a6f3351e-7f6b-4f0a-ae8e-ff24d1747e70/image.png" title="메인화면">    
    <h3><i>메인화면</i></h2>
</figure>
<figure>
    <img src="https://images.velog.io/images/nunddu/post/74cb5318-6820-4d31-b4db-6d7c49587c1f/image.png" title="캘린더">    
    <h3><i>캘린더 관리</i></h2>
</figure>
<figure>
    <img src="https://images.velog.io/images/nunddu/post/99d652f3-f5bb-409b-b9fe-744526dee942/image.png" title="화상회의">    
    <h3><i>화상회의</i></h2>
</figure>
<figure>
    <img src="https://images.velog.io/images/nunddu/post/24d620b7-4816-49a8-94bf-a98a7cbd3d9d/image.png" title="게시판 화면">    
    <h3><i>게시판 화면</i></h2>
</figure>
<figure>
    <img src="https://images.velog.io/images/nunddu/post/26c50e9d-1332-497e-a3b7-00e5425682c9/image.png" title="자료실 화면">    
    <h3><i>자료실 화면</i></h2>
</figure>
</div>

## **개발 이슈**
 - Open Graph Protocol
   - 웹 사이트의 메타데이터를 추출해 웹 사이트 정보와 미리 보기 이미지를 가져온 후 북마크 기능을 구현함.
 - Kurento WebRTC Media Server
     - 처음 사용하는 WebRTC 오픈소스와 https를 적용했습니다.
     - kurento media server, application, db를 각 docker 컨테이너로 분리해서 구성했는데, container간 통신이 안되는 문제 발생
     - docker container 포트 포워딩과 docker-compose.yml 파일 network 설정 문제를 수정하여 해결
 - AWS 20만원 과금 사건
   - 사건의 발단
     - AWS 프리티어 요금제로 ec2 instance를 사용하던 중 메모리 부족 문제로 서버가 다운되는 현상발생
     - 테스트용으로 사용하던 ec2였기 때문에 임시적으로 요금제를 높임
   - 진행
     - 메인으로 사용하던 ec2에 설정이 잘 마무리되면서 테스트용 ec2는 잊혀짐
   - 결말
     - 얼마 후, AWS로부터 20만원 상당의 청구서가 날라옴
     - AWS 측에 요금제 변경 후 ec2를 거의 사용하지 않음을 잘 어필하여 면제 받을 수 있었다.

## **사용 기술 및 환경**
### **FrontEnd**
 - Vue.js v3.0
 - Quasar Framework v3.0.2
   

### **BackEnd**

- SpringBoot
  - Gradle v6.7
  - SpringBoot v2.4.5
- MySQL v5.7
- JPA
- QueryDSL v4.4.0
- Open Source
  - [Kurento WebRTC Media Server](https://github.com/Kurento/kurento-tutorial-java) (release 6.16.0)


### **Infra**
 - AWS
 - Docker



