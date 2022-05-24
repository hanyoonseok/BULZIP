# 공공 데이터를 활용한 주택거래정보 검색 사이트

![Untitled](img/untitled.png)

## **프로젝트 개요**

- 공공 데이터를 활용한 주택거래정보 검색 사이트
- 프로젝트 제목 : Happy House Project
- 프로젝트 기간 : 2022.05.018.월
- 참여자 : 최승원, 한윤석

## **사용 기술 스택**

- Spring Boot
- Spring
- Java
- HTML5
- CSS3
- JavaSript
- Vue.js

## **개발 도구**

- Spring Tool Suite 3
- VSCode

# 폴더구조

![Untitled](img/untitled 1.png)

# 요구사항 정리

![Untitled](img/untitled 2.png)

# 구현해야할 기능과 구현 사항

### 1. 기본(필수) 기능

> Happyhouse 프로젝트의 QNA 게시판을 Vue를 활용하여 SPA 구조로 설계하고 구현하여 보자.
> 
- 매물 관리
    - 매물 리스트
    - 아무런 필터값, 키워드가 없을 때는 이름 순으로 매물 목록을 불러옵니다.
    
    ![Untitled](img/untitled 3.png)
    
    - 검색 결과 반영
    - 검색어 입력시 검색어가 포함된 동네, 아파트명이 있다면 목록에 보여줍니다
    
    ![Untitled](img/untitled 4.png)
    
    - 매물 선택시 상세정보 및 주변 병원 정보 제공
    - 선택한 매물과 같은 동네에 위치한 병원만 불러옵니다.
    
    ![Untitled](img/untitled 5.png)
    
    ![Untitled](img/untitled 6.png)
    
    - 회원가입
    - 아이디 입력시 keyup 이벤트에 유효성 검사를 실시합니다
    
    ![Untitled](img/untitled 7.png)
    
    - 로그인
    - 로그인시 로컬스토리지에 로그인한 정보가 저장됩니다.
    
    ![Untitled](img/untitled 8.png)
    
    - 회원 정보 수정
    - 내 정보에서 이름과 전화번호에 대한 정보를 수정할 수 있습니다.
    - 혹은 관리자일 경우 모든 회원의 상세정보에 접근 가능하며, 마찬가지로 정보 수정이 가능합니다.
    
    ![Untitled](img/untitled 9.png)
    
    ![Untitled](img/untitled 10.png)
    
    - 비밀번호변경
    
    ![Untitled](img/untitled 11.png)
    
    ![Untitled](img/untitled 12.png)
    
    - (관리자) 유저 리스트 조회
    - 로그인한 정보가 관리자인 경우 유저 전체의 목록을 관리할 수 있는 페이지에 접근할 수 있습니다.
    - 리스트에서 유저 정보 클릭시 유저 상세정보 페이지로 이동합니다.
    
    ![Untitled](img/untitled 13.png)
    
- QnA 게시판 구현
    - Header Navigation에서 QNA를 누르면 QnA 게시판이 나타나고 등록된 QnA 게시물들의 리스트가 출력된다.
      
        ![Untitled](img/untitled 14.png)
        
    - 특정 게시물을 누르면 해당 게시물의 수정 페이지가 출력된다.
      
        ![Untitled](img/untitled 15.png)
        
        - 글 제목 또는 글 내용을 바꾸고 수정이 되고 리스트 페이지로 이동한다.
    - 해당 게시글에 댓글 등록이 가능하다. → 답변 기능
      
        ![Untitled](img/untitled 16.png)
        
    - 해당 댓글은 수정, 삭제 기능을 구현하였다.

### 2. 추가 기능

> 추가 기능을 Vue를 적용하여 변경
> 

### 3. 심화 기능

> 메인페이지를 Vue를 이용하여 변경하자
> 
- 공지사항 관리
    - 공지사항 리스트
    
    ![Untitled](img/untitled 17.png)
    
    - 공지사항 작성
    - 공지사항은 관리자만 작성 가능하므로, 작성자는  디폴트로 관리자가 됩니다. 공지 제목과 공지 내용을 입력후 글쓰기하면 공지가 추가됩니다.
    
    ![Untitled](img/untitled 18.png)
    
    ![Untitled](img/untitled 19.png)
    
    - 공지사항 상세 조회 및 수정
    - 공지사항의 상세 내용을 조회하며, input의 값 변경후 수정이 가능합니다.
    
    ![Untitled](img/untitled 20.png)
    
    ![Untitled](img/untitled 21.png)
    
    - 공지사항 삭제
    
    ![Untitled](img/untitled 22.png)
    
    ![Untitled](img/untitled 23.png)
    

# 요구사항 외의 구현 내용