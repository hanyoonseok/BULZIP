# BULZIP
> 벌써 찾았다, 내 집. 사용자 맞춤 아파트 매물 정보 제공 서비스

![image](https://user-images.githubusercontent.com/28249948/192792244-bd139393-0f89-4f33-81de-77f961740909.png)

## **프로젝트 개요**

- 삼성 청년 SW 아카데미 1학기 관통 프로젝트 최우수상작
- 공공 데이터를 활용한 사용자 맞춤 아파트 매물 정보 제공 서비스
- 프로젝트 제목 : Happy House Project
- 프로젝트 기간 : 2022.05.17.화 ~ 2022.05.27.금
- 참여자 : 한윤석(FE), 최승원(BE)
- 시연 링크 : https://www.youtube.com/watch?v=3eEdog3Aiag&ab_channel=yoonseokhan

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

## 폴더구조

![image](https://user-images.githubusercontent.com/28249948/192789314-c3e07770-a56a-49fa-b405-3b75554731db.png)


## 주요 기능

### 온보딩
![image](https://user-images.githubusercontent.com/28249948/192789890-747581c0-05fa-4213-a12f-89e10d327689.png)

### 회원관리
- 로그인
![image](https://user-images.githubusercontent.com/28249948/192790035-8ffcbd27-443b-43e7-b9e5-75162bfeff1f.png)

- 회원가입
    -  아이디, 비밀번호, 이름, 전화번호 입력
    -  관심 상권 키워드 10개 선택
![image](https://user-images.githubusercontent.com/28249948/192790260-d47fdcc0-e84e-4181-a0c1-1097e7b8929f.png)


### 공지사항
- CRUD
![image](https://user-images.githubusercontent.com/28249948/192790440-af8d3364-c93c-47b1-b89a-30bc84af020e.png)

### QNA
- CRUD, 댓글
![image](https://user-images.githubusercontent.com/28249948/192790558-df900985-b61d-4fe3-9464-cea16a7228f2.png)

### 매물 정보
- 초기 정보
![image](https://user-images.githubusercontent.com/28249948/192790674-ec358a88-e5ce-4ecd-a55a-a8850b60b239.png)

- 검색 기능 (지하철, 키워드)
    - 자체 지하철 DB 구축하여 지하철 역과 역 주변 상권 검색
    - 구, 동, 아파트 명으로 매물 검색
![image](https://user-images.githubusercontent.com/28249948/192790830-b018ca1a-08fc-44ca-a2cb-792396552308.png)

- 상호작용
    - 스크롤링, 드래깅을 통해 지도 내 매물 정보 갱신
    - 매물 마커 클릭으로 커스텀 오버레이를 토글하여 매물 간략 정보 확인
    - 지도 클러스터링을 통한 마커 정보 시각화
![image](https://user-images.githubusercontent.com/28249948/192791011-ff4cea2c-fcc7-448f-ac0c-a95292cf890b.png)

- 매물 상세정보
    - 특정 매물 클릭시 해당 매물의 상세정보 제공
    - 과거 거래 내역을 차트로 사각화
    - 매물 주변 100 ~ 1000m 내 상권 정보 맵에 표시
![image](https://user-images.githubusercontent.com/28249948/192791163-34d697ce-75d7-4aba-9dd9-990e9d2decfe.png)

- 상권 정보 조회
    - 주변 병원 보기 버튼 클릭을 통한 지도 내 병원 마커 표시 기능
    - 상권 키워드 탭 클릭을 통한 상권 정보 조회 가능 (범위 조절 가능)
![image](https://user-images.githubusercontent.com/28249948/192791463-03393769-3296-4339-b78f-94c7f8be6215.png)
![image](https://user-images.githubusercontent.com/28249948/192791528-fbc72372-c643-4a18-a3a3-a033466f5833.png)

- 추천 매물 리스트
    - 사용자가 선택한 키워드를 활용해 회원들간의 유사도 도출
    - 유사도 상위 3명을 선정해 해당 회원들이 등록한 관심 매물 추천
![image](https://user-images.githubusercontent.com/28249948/192791659-1fbcd643-cd11-43a1-a986-86ad955b2b2a.png)
