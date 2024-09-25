# CODEBACK</>

![KakaoTalk_20240924_165036039](https://github.com/user-attachments/assets/d326edab-2837-4a59-a292-cdc3cf4848ae)


## 📖 프로젝트 소개

시니어 개발자와 주니어 개발자 간의 상호 작용을 촉진하여 코드 품질을 향상시키고, 주니어 개발자의 성장과 학습을 지원하는 코드리뷰 플랫폼 제공

## 팀원 구성

<br>


## 🖋 기술 스택
* front
  * Thymeleaf
  * JavaScript
* Back
  * Java 21
  * Spring boot
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

### 코드, 프로젝트 게시판 관리
1. 게시글 작성
  * 코드 리뷰  
    * 마크다운 문법으로 코드를 코드블럭으로 구분하여 게시글을 작성할 수 있습니다.
    * 작성된 코드의 언어를 선택할 수 있습니다.
![image](https://github.com/user-attachments/assets/799d50cb-13be-44b1-ad3e-7ec637e2493e)
  * 프로젝트 리뷰
    * 프로젝트의 실행 화면 이미지와 관련된 태그, github url을 함께 작성합니다. 
![image](https://github.com/user-attachments/assets/1c4cd64c-94e6-45bf-8e14-64ed9d802e1c)

2. 게시글 목록 조회
  * 코드 리뷰
    * 작성된 언어와 검색어를 통해 게시글 조회가 가능합니다.
    * 페이지네이션 기능으로 페이지 이동으로 게시글 조회가 가능합니다.
![image](https://github.com/user-attachments/assets/4d59e0b5-c867-4666-b6a7-57df2411c21d)
  * 프로젝트 리뷰
    * 검색어를 통해 게시글 조회가 가능합니다.
![image](https://github.com/user-attachments/assets/752a5d52-8558-488b-ba7f-ebb1005e0091)
    * 게시글의 해시태그로 관련 게시글 조회가 가능합니다.
![image](https://github.com/user-attachments/assets/4d490e28-465c-44ad-9bfd-210c59cf8327)

3. 게시글 조회
* 코드 리뷰
  * 코드 블럭으로 작성된 코드와 질문 내용을 구분하여 게시글을 조회할 수 있습니다.
  * 게시글에 달린 댓글과 좋아요 개수도 조회가 가능합니다.
![image](https://github.com/user-attachments/assets/47da5b84-db4b-4593-84a8-83d6f6a3da4b)
* 프로젝트 리뷰
  * 태그 버튼을 누르면 태그별 게시글 목록 조회 페이지로 이동할 수 있습니다.
  * 깃허브 아이콘을 누르면 작성자가 입력한 github url로 이동할 수 있습니다.
![image](https://github.com/user-attachments/assets/12651e3b-c525-489d-ae85-a96e412cfa2d)

4. 댓글 작성 및 수정
* 마크다운 문법으로 코드블럭으로 구분하여 댓글을 작성할 수 있습니다.
  ![image](https://github.com/user-attachments/assets/b8158a21-8bbb-46d3-a503-312fa8bf5ae3)
* 본인이 작성한 댓글만 수정 혹은 삭제가 가능합니다.
* 연필모양 아이콘을 누르면 마크다운 에디터가 생기고 작성되었던 댓글이 랜더링 됩니다.
* 연필모양 아이콘을 누르면 수정 내용이 적용되고, 쓰레기통 아이콘을 누르면 작성했던 댓글이 삭제됩니다.
![image](https://github.com/user-attachments/assets/09e22c65-2fa4-49d6-b90c-7770b42d75a2)

6. 게시글 수정 및 삭제
* 본인이 작성한 게시글만 수정 혹은 삭제가 가능합니다.







