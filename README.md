◈MyPetmily (2020/07/29~2020/10/23)
◈반려동물의 스케줄을 기록하고 관리할 수 있다.

◈Android : Tech stack & Open-source libraries
- Tool : Eclipse photon (4.8)
- Spring Tool Suite4 v4.7.1 (Spring legacy)
- Data Base : Oracle Databse 11g Express Edition
- Web Server : Tomcat v8.5
- Android Studio(3.5.5)
- Material CalendarView

◈Android : 화면구성(Calendar)

![캘린더1](https://user-images.githubusercontent.com/69283479/120292606-690f6e00-c2ff-11eb-97e9-4346bc2162db.png)
- 내가 등록한 반려동물의 일정을 기록한 캘린더를 불러옴
- 화면 Fragment 전환 및 Bundle로 데이터 값 전달
- 캘린더에 아이콘 삽입이 가능한 외부 Library인 Material CalendarView를 이용
- 날짜를 선택하여 일정 추가, 아이콘 선택과 간단한 메모 추가

![캘린더2](https://user-images.githubusercontent.com/69283479/120292613-6a409b00-c2ff-11eb-971e-01f3a9f2de4c.png)
- 시간 순서대로 일정을 RecyclerView 정렬, 캘린더 가시성을 고려해 최대 3개까지 설정
- 이미 등록된 일정을 선택하여 수정 버튼을 통해 수정페이지로 이동
- 이미 등록된 일정을 선택하여 삭제 버튼으로 간단하게 삭제
- 자동 커밋, 실시간 동기화

◈Web : Tech stack & Open-source libraries
- Tool : Eclipse photon (4.8)
- Spring Tool Suite4 v.4.7.1 (Spring legacy)
- Data Base : Oracle Databse 11g Express Edition
- Web Server : Tomcat v8.5

◈Web : 화면구성(회사소개, QnA, 커뮤니티 게시판)
![웹](https://user-images.githubusercontent.com/69283479/120397408-40c15700-c373-11eb-9b7d-f78dd0758a40.png)
- 커뮤니티 게시판 글 읽기, 쓰기, 수정, 삭제 & 관리자 모드 추가
- 필수 입력 양식 유효성 검사
- 사진 첨부, 첨부된 사진 미리 보기
- 삼중 셀렉트 박스 검색(게시판 분류, 지역, 지역 상세), 글 제목 본문 내용, 작성자 검색
- 페이징 처리
- ajax 통신으로 댓글 쓰기, 수정, 삭제 & 관리자 모드 추가
- ajax 통신으로 Q&A 페이지 관리자만 등록, 수정, 삭제 가능
