# CODEBACK</>

![KakaoTalk_20240924_165036039](https://github.com/user-attachments/assets/d326edab-2837-4a59-a292-cdc3cf4848ae)


## 📖 프로젝트 소개

시니어 개발자와 주니어 개발자 간의 상호 작용을 촉진하여 코드 품질을 향상시키고, 주니어 개발자의 성장과 학습을 지원하는 코드리뷰 플랫폼 제공

### 팀원 구성
* 이석원   [@Frod90](https://github.com/Frod90)
* 이도경   [@leedokyong](https://github.com/leedokyong)
* 김성호   [@ksngh](https://github.com/ksngh)
* 김다영   [@keyy1315](https://github.com/keyy1315)

### 개발 기간
* 2024.08.08 ~ 2024.09.25

  
<br>


## 🖋 기술 스택
* front
  * Thymeleaf
  * JavaScript
* Back
  * Java 21
  * Spring boot 3.3.2
  * Spring Data Jpa
  * MariaDB 11.4.2
* Collaboration
  * Slack
  * Notion
  * GIT
* AWS
  * EC2
  * S3



<hr />

## 📌 구현 기능

<details><summary>회원 관리</summary>


 * [API](https://github.com/HTA-2402-TEAM3/codeback/blob/master/src/main/java/kr/codeback/api/MemberRestController.java)
 * 로그인
   * oAuth 로그인
   * 자사 이메일 로그인
 * 회원가입
   * oAuth 회원가입
   * 자사 이메일 회원가입
 * 로그아웃
 * 유저 정보수정
 * 유저 삭제
 * 유저 정보 불러오기
   * [토큰 받아오기](https://github.com/HTA-2402-TEAM3/codeback/blob/master/src/main/java/kr/codeback/util/JwtUtil.java)
   * 토큰 유저 정보 추출
</details>

<details><summary>게시판 관리
</summary>

 * [API(코드 리뷰 게시판)](https://github.com/HTA-2402-TEAM3/codeback/blob/master/src/main/java/kr/codeback/api/CodeReviewRestController.java)
 * [API(프로젝트 리뷰 게시판)](https://github.com/HTA-2402-TEAM3/codeback/blob/master/src/main/java/kr/codeback/api/ProjectReviewRestController.java)
 * 게시판 생성
   * 마크다운 에디터 사용
   * aws s3 이미지 업로드
 * 게시판 삭제
 * 게시판 수정
 * 게시판 조회
   * 게시판 검색 조건별 게시글 목록 조회
</details>

<details><summary>댓글 관리</summary>

* [API(코드 리뷰 댓글)](https://github.com/HTA-2402-TEAM3/codeback/blob/master/src/main/java/kr/codeback/api/CodeReviewCommentRestController.java)
* [API(프로젝트 리뷰 댓글)](https://github.com/HTA-2402-TEAM3/codeback/blob/master/src/main/java/kr/codeback/api/ProjectReviewCommentRestController.java)
 * 댓글 생성
 * 댓글 삭제
 * 댓글 수정
</details>
<details><summary>관리자 페이지</summary>

* [API](https://github.com/HTA-2402-TEAM3/codeback/blob/master/src/main/java/kr/codeback/controller/AdminRestController.java)
* 회원 목록 조회
* 회원 권한 변경
* 회원 삭제
* [통계](https://github.com/HTA-2402-TEAM3/codeback/blob/master/src/main/java/kr/codeback/controller/AdminController.java)
  * 언어별 작성된 게시글 개수
  * 비활성화된 회원 수
  * 날짜 별 게시글, 댓글, 좋아요 수
</details>

<details><summary>알림 관리</summary>

* [API](https://github.com/HTA-2402-TEAM3/codeback/blob/master/src/main/java/kr/codeback/api/NotificationRestController.java)
 * 알림 보기
 * 알림 삭제
 * 알림 읽음 처리
   * 전체 읽음 처리
   * 개별 읽음 처리
 * 알림 생성
</details>

<details><summary>좋아요 관리</summary>

* [Service](https://github.com/HTA-2402-TEAM3/codeback/blob/master/src/main/java/kr/codeback/service/impl/PreferenceServiceImpl.java)
* 게시글 좋아요 생성
* 댓글 좋아요 생성
</details>
<br />

### 🎨구현 화면
<br />

![image](https://github.com/user-attachments/assets/a785964e-098d-4d64-aebb-f717555c5349)
![image](https://github.com/user-attachments/assets/0f853114-d244-4dfe-a9d4-5ad0cb270f63)
![image](https://github.com/user-attachments/assets/8faaca51-79d2-4ddb-8e9c-97882b7b99f9)
![image](https://github.com/user-attachments/assets/bf822e5a-7264-4dd2-8f54-2e43045439f9)
![image](https://github.com/user-attachments/assets/7fb6cd56-87c7-436f-bccb-42dcd0df7c2a)
![image](https://github.com/user-attachments/assets/4e1cb91a-c889-42fd-81be-092253cc44af)










