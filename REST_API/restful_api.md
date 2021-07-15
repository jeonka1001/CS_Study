# RESTful API

## REST
>자원의 표현 상태를 전달하는 것  
>REpresentation State Transfer  

- 즉, 데이터가 보내지는 시점의 상태를 전달
- JSON,XML 을 통해 데이터를 주고받음
- www 와 같은 분산 하이퍼미디어 시스템을 위한 소프트웨어 개발 아키텍처
    - HTTP 프로토콜을 그대로 활용
    - Client - Server 의 통신 방법 중 한가지

## REST 구성요소
1. 자원
- 모든 자원에 고유 ID 존재, 자원은 Server에 존재  

2. 행위
- HTTP.Method 를 사용

3. 표현
- Client가 상태 조작 요청 시 Server에서 적절한 Representation 을 보냄
- 자원은 ***JSON*** , ***XML*** , TEXT, RSS 등 여러 형태로 나타냄

## REST 특징
1. Server - Client 구조
- Server : API 제공, 비즈니스 로직 처리 및 저장
- Client : 사용자인증, Context(세션, 로그인정보 등) 관리

2. Stateless 
- Client의 Context를 Server에 저장하지 않음
- 이전 요청이 다음 요청 처리에 연관되지 않는다 ( DB 조작 제외 )

3. Cacheable
- Last-Modified or E-Tag 를 사용하여 캐싱 가능
- 대량 요청 빠르게 처리 가능 ( 성능 향상 )

4. Layered System
- Proxy GateWay 와 같은 네트워크 기반의 중간 매체 사용 가능
- 비즈니스 로직과 그 외 기능을 분리할 수 있다.

5. Code-On-Demand ( optional )
- 서버사이드 가능

6. Uniform Interface
- 통일된 인터페이스 
    - 어떤 개발자가 봐도 용도를 빠르게 파악할 수 있음

## 설계 규칙
1. URI 는 정보의 자원을 표현해야 함
- 동사보다 명사 사용, 대문자보다 소문자 사용
- 도큐먼트는 단수 명사 사용
    - 도큐먼트 : 객체 인스턴스, 데이터베이스 레코드 유사
- 컬렉션은 복수 명사 사용
    - 서버에서 관리하는 디렉터리
- 스토어 이름은 복수명사 사용
    - 클라이언트에서 관리하는 리소스 저장소  

2. 자원에 대한 행위는 HTTP Method 사용
- URI 에 HTTP Method 포함 안됨
- URI 를 동사로 명시하면 안됨  

3. 기타  
- 마지막 문자를 슬래시로 끝내지 않기
- 언더바(_) 는 사용하지 않고 하이픈(-) 은 가독성을 높이기 위해 사용
- 파일 확장자 포함하지 않기

## 응답 상태 코드
- 1XX : 전송 프로토콜 수준의 정보 교환
- 2XX : 클라이언트 요청 성공적으로 수행
- 3XX : 클라이언트 요청 완료하기 위해 추가 행동 요구
- 4XX : 클라이언트의 잘못된 요청
- 5XX : 서버의 오류

## URL vs URI 
- URI
    - Uniform Resource Identifier 
    - 모든 것을 자원으로 표현하며, 자원은 유일한 것을 나타냄 ( 식별자 ) 
- URL
    - Uniform Resource Locator
    - html 과 같은 파일을 이용한 통신 시 파일의 위치를 나타냄  


#### 프로시저 ? 
- 특정 로직을 처리하기만 하고 결과 값을 반환하지 않는 서브 프로그램.  

