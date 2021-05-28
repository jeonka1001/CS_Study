# 목록
- [알고리즘](https://github.com/jeonka1001/Study/blob/main/basic/algorithm.md)
- [데이터베이스](https://github.com/jeonka1001/Study/blob/main/basic/database.md)
- [자료구조](https://github.com/jeonka1001/Study/blob/main/basic/datastructure.md)
- [자바](https://github.com/jeonka1001/Study/blob/main/basic/java.md)
- [웹](https://github.com/jeonka1001/Study/blob/main/basic/web.md)

## 알고리즘
#### 시간 복잡도를 나타내는 방법
- Big-O 표기법이란?
#### 버블 정렬
- 버블 정렬이란?
- 버블 정렬의 시간 복잡도/ 공간 복잡도
#### 선택 정렬
- 선택 정렬이란?
- 선택 정렬의 시간 복잡도/ 공간 복잡도
#### 삽입 정렬
- 삽입 정렬이란?
- 삽입 정렬의 시간 복잡도/ 공간 복잡도
#### 퀵 정렬
- 퀵 정렬이란?
- 퀵 정렬의 시간 복잡도/ 공간 복잡도
#### 병합 정렬
- 병합 정렬이란?
- 병합 정렬의 시간 복잡도/ 공간 복잡도
#### 이분 탐색
- 이분 탐색이란
- 이분 탐색의 시간 복잡도
#### HashTable
- HashTable 이란?
#### BFS vs DFS 
- DFS BFS 사용 적합한 상황
- 구현 방식

---

## 데이터 베이스
#### 데이터 베이스 사용 이유?
#### SELECT
- SELECT 작성 순서
- SELECT 동작 순서
#### DML DCL DDL
- DDL 이란
- DML 이란
- DCL 이란
#### delete vs truncate vs drop
#### 트랜잭션
- 트랜잭션이란?
- commit vs rollback
- 트랜잭션의 ACID
- 트랜잭션 격리 수준
#### PK vs UK
- PK UK 정의
- 차이점
#### 인덱스
- 인덱스란?
- 사용하는 자료구조
#### Join
- join 이란
- join 의 종류
#### 정규화
- 1,2,3 정규화 설명
- 2,3 정규화의 차이
#### 반 정규화
- 반 정규화란?
- 반 정규화의 종류
#### JDBC
- JDBC 란?
- preparedstatement vs stataement
#### sql injection
- sql injection 이란?
- 해결방안
#### Replication
- 정의
- 장점
#### View
- 정의
- 장점, 단점

---

## 자료구조
#### List
- 정의
- 종류
#### Set
- 정의
- 종류
#### Map
- 정의
- 종류
#### Queue
- 정의 
#### Stack
- 정의
#### Tree
- 정의
- heap
- red black tree
    - 조건
    - 삽입 과정
- vs graph

---

## 자바
#### JDK JRE JVM
- 개념 설명
- JVM 내부 구조
- OracleJDK vs Open JDK 
- JDK 버전 사용 이유
#### OOP
- OOP란?
- 4가지 특징
    - 오버로딩 vs 오버라이딩
- 5대 원칙
#### 메모리 구조
#### 추상클래스 vs 인터페이스
- 각 정의
- 공통점
- 차이점
- 사용 케이스
#### import vs package
- 각 정의
- static import
- java.lang 은 import 하지 않는 이유
#### 어노테이션
- 정의
- 사용 이유
#### 마커 인터페이스
- 정의
- vs 마커 어노테이션
#### 가비지 컬렉터
- 정의
- 작동 시점, 방식
#### static 키워드
- 정의
- vs non-static
- public static void main 인 이유
#### == 과 equals
- 둘의 차이
- equals 와 hashcode 관계
- valueOf vs parseInt
- valueOf vs toString vs casting
#### 접근제어자
#### 직렬화 역직렬화
#### String StringBuffer StringBuilder
- 각각 설명
- buffer vs builder
- intern()
#### Thread
- 정의
- 상태 
- waiting vs blocked
- 구현 방식
- start() vs run()
- 동기화 vs 비동기화
#### comparable vs comparator
#### synchronized
- 정의
- 사용 방식
#### Reflection
- 정의
- 사용 방법
#### Stream
- 정의
- iterator 과 차이
#### final 
- 정의
- 사용 시 효과
- finally 는?
#### Wrapper class
- 정의
- optional 이란?
#### Generic
- 정의
- 장점
#### Error vs Exception
- 각각 정의
- 차이점
- unchecked exception vs checked exception
#### 람다
- 정의
- 장점

----

## web
#### http
- 정의
- 구조
- 메소드 설명
- get vs post
- vs https
- 상태 코드
#### session vs cookie
- 정의
- 차이점
- 각각 사용 예시
#### REST
- 정의
- RESTful 하다 ?
- 사용 이유
#### MVC1 vs MVC2
- 각각 정의
- 차이점
#### pulling polling
#### TCP UDP
- 각 프로토콜 설명
- 각 장점
#### 3,4 way handshake
- 각각 과정
- 차이점

----
## 운영체제
#### 프로세스 스레드
#### 교착상태 기아상태
#### 캐시


<!-- 
Http 통신
> hyper text transfer protocol 
> 인터넷에서 데이터를 주고 받기 위한 프로토콜
> httpMethod 는 GET POST PUT DELETE 등 이 있다.
> 특히 PUT 의 경우 멱등성이다.

동기
> 작업을 처리함에 있어 작업 순서를 지키며 처리하는 것
> 작업을 순차적으로 처리하기 때문에 설계가 간단 ( 이전 작업을 처리 후 해당 작업을 처리)
> 단점 : 이전 작업이 오래 걸릴경우 다음 작업도 오래 걸린다.
비동기
> 작업을 처리함에 있어 작업 순서를 지키지 않고 처리하는 것
> 순서를 지키지 않고 이전 작업의 끝 유무와 상관 없이 작업을 처리 ( 속도가 빠르다 )
> 단점 : 설계가 힘들다.
( 스레드/ 프로세스 )
( RESTful API )

메시지큐
> 비동기적으로 프로세스간 데이터 통신을 하기위해 사용하는 방법
> 중간에 메시지 브로커를 두어 서로 다른 프로세스가 직접적인 통신을 하는 것이 아닌, 메시지를 보내면 브로커가 받아 전달해 주는 방식으로 통신


동시성 이슈
> 하나의 자원을 동시에 여러 스레드 혹은 프로세스가 접근했을 때 일어나는 문제점
> 뮤텍스 혹은 세마포어 방식을 통해 해결 가능
> 뮤텍스 : 사용하는 자원에 락을 걸어 해당 락을 거는 스레드만 락을 해제할 수 있음
         즉, 공유 자원에 오직 1개의 스레드만 접근 가능
> 세마포어 : 공유 자원에 최대 허용치 만큼 스레드 ( 프로세스 ) 가 접근 가능
           락을 건 스레드(프로세스) 가 아니더라도 락을 해제할 수 있음


GC
> 자바의 heap 메모리 영역에서 메모리 누수를 막기 위한 기능
> 사용하지 않는 할당된 메모리를 reachability 개념을 통해 해제한다.
> root set 에서 도달 가능한 메모리는 reachable 아닐 경우 unreachable 로 unreachable 인 메모리 할당을 해제함

테스트 코드
> 내가 작성한 코드를 기능 단위로 테스트 하는 코드
> Junit 을 이용해봤으며 전체 프로젝트를 컴파일해서 테스트를 하는것이 아닌 해당 기능만 컴파일 후 테스트를 한다. 
> 이는 프로젝트 규모가 커지면서 안정적이게 작성 가능하고, 어디서 오류가 나는지 금방 찾을 수 있다.

리팩토링 
> 이미 작성된 코드를 재 수정하여 기능상 달라지지는 않지만 코드를 더 알아보기 쉽게 클린코드로 작성하는 것
> 중복 제거, 길이 줄이기, 거대한 클래스 분할, 절차지향적으로 구현한 코드를 수정

-->