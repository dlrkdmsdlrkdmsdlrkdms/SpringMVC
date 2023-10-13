# Spring MVC
## MVC 패턴
- 디자인 패턴 일종
  - Model : 데이터를 관리하는 역할
  - View : 웹 페이지를 화면에 보여주는 역할
    - JSP, Thymeleaf, mustache, freemarker
    - (FrontEnd) React.js, Vue.js
    - FrontController(DispatchServlet)을 통해서 다른 컨트롤러로 접근
  - Controller : 클라이언트의 요청을 받아 관리하는 역할
  
![MVC](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/MVC-Process.svg/800px-MVC-Process.svg.png)
  - View 템플릿의 생성 위치
    - src > main > resources > templates
  - 정적 파일의 위치
    - src > main > static
  - 컨트롤러의 생성 위치
    - src > main > java > 기본 패키지 > controller

## H2 DataBase
- 설치가 필요 없고, 용량이 가벼워서, 개발용 로컬 DB로 사용하기 좋은 RDBMS
- 자바 기반 오픈 소스 관계형 RDBMS
- in-Memory DB 기능을 지원
- 2Mb 내외의 저용량 데이터베이스
- 표준 SQL지원
- 로컬 및 테스트 환경에서 많이 사용
- H2 콘솔 보기 순서
  1. application.properties 에서 설정 추가(콘솔보기)
     - `spring.h2.console.enabledc = true`
  2. 스프링 실행파일을 재시작 한 후 브라우저에서 
     - `http://localhost:8080/h2-console` 접속 
  3. 실행 로그에서 `H2ConsoleAutoConfiguration` 부분을 찾아 URL 주소 복사
  4. JDBC URL 부분에 URL을 붙여넣기
  5. [Connect]
- application.properties 파일에서
    - `spring.datasource.url=jdbc:h2:mem:testdb`
  을 추가한 후, 브라우저 콘솔 URL에 `jdbc:h2:mem:testdb`를 입력하면 앞으로 재시작하더라도 주소 고정
## form 데이터 전달
- HTML 폼 데이터에서 전달 받은 데이터는 name 속성의 이름에 맞춰 http로 전달
- 컨트롤러가 객체(DTO)에 담는다.
- DTO를 DB에 저장
  - JPA를 사용하는 경우
    - Entity 객체 : 자바 객체를 DB가 이해할 수 있게 만드는 것
    - Repository : 엔터티가 DB테이블에 접근, 저장, 관리 할 수 있게 하는 인터페이스
  - 외부에서 만들어진 객체(Controller에 Repository) 객체(Controller에 Repository)를 DI(의존성 주입)


## Lombok
- 프로젝트 롬복의 축약어
- 자바 코드를 더 간단하고 간결하게 사용하게 만들어주는 코드 생성
  - 애노테이션 기반으로 코드 생성
  - 코드의 중복 제거 => 코드의 가독성 향상, 실수 방지
  - @Data : getter,setter, equals, hashcode, toString까지 자동으로 생성
  - @Getter
  - @Setter
  - @ToString
  - @AllArgsConstructor : 모든 필드를 파라미터로 받는 생성자
  - @NoArgsConstructor : 아무 필드도 파라미터로 받지 않는 기본 생성자
  - @Builder : 빌더 패턴을 쉽게 적용할 수 있게 빌더 클래스 생성
  - @Slf4j : 로깅 모듈
- 다양한 애노테이션 지원

## 데이터 조회 과정
![](C:\Users\admin\Desktop\learn\Spring\springmvc\img\데이터 조회 과정)
1. 사용자가 URL 요청(Http Request(Method Get or Post))
2. 컨트롤러가 요청을 받아 데이터 정보를 Repository에 전달
3. 리파지토리는 데이터를 DB에 조회 요청
4. DB는 해당 데이터를 찾아와서 Entity로 반환
5. 반환된 엔티티는 모델을 통해 뷰 템플릿으로 전달
6. 뷰 페이지가 완성되어 사용자 화면에 출력
 - @PathVariable : URL 요청을 동적으로 받는 경로 매개변수
 - findById() : JPA 제공 메서드 id 기준으로 데이터 검색 Optional로 반환
 - findByAll : JPA 메소드 엔티티의 모든 데이터를 조회하고 Iterable러 반환
 - {{#article}} {{/article}} : 머스테치 분법, 해당 범위 내에 데이터를 (객체의 필드)사용 가능.
반복 가능한 객체일 경우(복수 객체) 해당 범위 코드가 반복
   - Entity에 기본 생성자가 있어야 객체 반복 및 필드 사용 가능
   - Java Bean
     - 특정 형태의 클래스(DTO, VO)
       - public 접근 가능한 기본 생성자
       - 모든 필드는 private
       - getter / setter
     - Spring Bean 과는 다름(스프링 빈 : 스프링에서 관리하는 객체)
- CommandLindRunner 인터페이스
  - 스프링부트 앱 구도 시점에 코드를 실행시킬 때 사용
  - @Component 애노테이션 선언으로 컴포넌트 스캔
  - 구동 시점에 run 메소드 실행

## PRG 패턴(Post-Redirect-Get)
- 웹 앱에서 사용자 상호작용을 다루는 디자인 패턴
  - Post : HTTP POST 메소드로 서버에 양식을 제출 
  - 서버에서 로직 실행 : Post 요청을 받고 필요한 로직 수행
  - Redirect : HTTP 리다이렉트 응답 생ㅅ어 다른 페이지로 이동
  - GET : HTTP Get 요청을 통해 결과 확인
- 중복 제축 : 새로고침을 통한 중복 제출 방지
- UX 개선

## 생성자 주입(코드가 줄어드는 과정 만 설명)
- 생성자가 1개만 있을 경우 @Autowired 새얅해도 주입이 가능한 편의성 제공
- 롬복의 @RequiredArgsConstructor 사용해 생성자 코드를 생략 가능
  (의존성 주입 필드에 private final 선언)

## HTTP 상태코드
- 1xx (정보) : 요청 수신
- 2xx (성공) : 요청이 정상적으로 처리
- 3xx (리다이렉션) : 요청을 완료하기 위해 추가 행동이 필요
- 4xx (클라이언트 요청 오류) : 요청이 잘못되어 서버 수행 불가
- 5xx (서버 응답 오류) : 서버 내부 에러 발생해 요청 수행 불가

## HTTP 메소드
- GET : 리소스 조회  (요청에 Body 없음, 응답에 Body 있음) 안전(리소스를 변경하지 않는다.)
- POST : 요청한 데이터를 처리 (생성)
- PATCH : 리소스의 부분 변경
- DELETE : 리소스 삭제 (요청에 Body 없음, 응답에 Body 있음)

## REST API 
- REST(REpresentational State Transfer)
  - HTTP URL을 통해 서버의 자원(Resource)을 명시하고, HTTP 메소드(GET, POST, PUT, DELETE)로 해당 자원을 CRUD(새성, 조회, 수정, 삭제)하는 것
  - API는 클라이언트가, 서버 자원을 요청할 수 있도록 서버 측에서 제공하는 인터페이스
  - 리소스(자원)지향
  - 상태 없음(Statless)
  - 웹 개발 및 서비스에서 통신 단순화, 확장성 등으로 중요 부분 차지

## REST 컨트롤러
- REST API로 설계된 URL의 요청을 받아서 처리하는 컨트롤러
- 뷰 대신에 텍스트, JSON 반환
- @RestController

## ResponseEntity
- REST 컨트롤러의 리턴 타입, REST API 응답을 위해 사용하는 클래스, HTTP 상태코드
Header, Body 등을 실어 보낼 수 있다.
## HttpStatus
- 상태코드를 관리하는 클래스, Enum 타입 200 => HttpStatus.OK

### @PathVariable 이란?
- 경로 변수를 표시하기 위해 메소드에 매개변수에 사용 된다.
- 경로 변수는 중괄호{id}로 둘러싸인 값을 나타낸다.
- URL 경로에서 변수 값을 추출해 매개변수에 할당한다.
- 기본적으로 경롭 변수는 반드시 값을 가져야 하고, 값이 없는 경우는 404 오류 발생
- 주로 상세조회, 수정, 삭제와 같은 작업 리소스 식별자로 사용