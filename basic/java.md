# 면접 준비 - JAVA
> 나만의 언어로 하는 면접준비

## 질문 목차
- [JDK, JRE, JVM](#jdk,-jrm,-jvm)
- [자바의 메모리 구조](#자바의-메모리-구조)
- [추상 클래스와 인터페이스](#추상-클래스-vs-인터페이스)
- [Import vs Package](#import-vs-package)
- [트랜잭션처리](#트랜잭션처리)-- 수정하기
- [자바의 대표 collection](#자바의-대표-collection)
- [어노테이션](#어노테이션)
- [마커 인터페이스](#마커-인터페이스)
- [Garbage Collector](#garbage-collector)
- [접근 제어자 4가지](#접근-제어자-4가지)
- [직렬화 역직렬화](#직렬화-역직렬화)
- [String StringBuilder StringBuffer](#string,-stringbuilder,-stringbuffer)
- [Synchroized](#synchronized)

---

## JDK, JRE, JVM
> 범주는 JDK 안에 JRE 안에 JVM 이 있다.  
- JDK : Java Development Kit 의 약자로 JRE + 개발을 위해 필요한 도구 를 포함한다.  
- JRE : Java Runtime Enviroment 의 약자로 JVM 이 자바 프로그램을 동작시킬 때 필요한 라이브러리, 기타 파일(javac, 디버거, 컴파일러) 을 가지고 있다.  
- JVM : Java Virtual Machine 의 약자로 자바 소스코드로부터 만들어지는 자바 바이너리 파일을 실행할 수 있다.  

#### JVM
> class loader, execution engine, garbage collector, runtime data area 
- class loader : 자바 파일 컴파일 후 생성된 바이트코드를 엮어 runtime 메모리에 적재하는 역할
- execution engine : 메모리에 적재된 클래스를 기계어로 변경해 실행하는 역할
- [garbage collector](#garbage-collector)
- [runtime data area](#자바의-메모리-구조)

#### Oracle JDK vs OpenJDK
- 두 JDK의 이는 Oracle 에서는 재산권이 걸린 플러그인을 제공한다.
- JDK(8버전 이후)가 상업적 용도로 사용된 경우 라이센스 비용을 지불해야 한다.
- 따라서 기업에서는 jdk8 버전을 많이 사용하고 있다.
- 초기 ide 설치 후 jdk 11 을 사용했으나, 위 의 사항을 알게되고 jdk 8 버전을 사용하고 있다.

- 8 버전부터 람다, stream 사용 가능 
--- 

## OOP ( Object Oriented Programming )
- 프로그램을 객체를 중점으로 어떤 객체가 어떤 일을 할것인지, 어떤 객체와 상호작용을 하는지 를 중심으로 생각하며 프로그래밍 하는 것.

#### 4가지 특징
> 추상화, 캡슐화, 상속, 다형성
- 추상화
    - 객체의 공통점을 파악아혀 하나의 집합으로 다루는 것
- 캡슐화
    - 관련있는 변수, 메소드를 하나로 묶어 객체화 하는것
    - 이를 통해 정보 은닉을 할 수 있다.
- 다형성
    - 상속을 통해 기능을 확장하거나 변경하는 것을 가능하게 해주는 것
    - 오버로딩, 오버라이딩

###### 오버로딩 vs 오버라이딩
- 오버로딩  : 이름이 같은 메서드로 다른 기능을 구현하는 것으로 함수 선언 시 이름은 같고 전달인자는 다르게 선언 하는 것.
    - 리턴값 ? 함수 호출 단계에서 리턴값으로 함수를 구분할 수 없기 때문에 리턴값을 다르게 한다해서 다른 함수로 인식하지 않는다 .

- 오버라이딩 : 상위 클래스의 특정 함수를 재 구현한 것으로 매서드명, 매개변수, 리턴타입을 모두 같게 해야한다.
    -   재정의 시 메서드 내에서 super()를 통해 부모 메서드를 호출할 수 있으며, 이를 하지 않을 시 부모 메서드는 호출되지 않는다.( 가려진다 )

#### 5대 원칙
> SOLID 원칙
- S 단일 책임 원칙( single responsibility principle )
    - 객체는 단 하나의 책임만 가져야 한다.
- O 개방 폐쇄 원칙( open closed principle )
    - 모든 개체는 확장에 열려있어야 하며, 수정에는 닫혀있어야 한다.
- L 리스코브 치환 원칙( liskov substitution principle )
    - 자식 클래스는 부모 클래스에서 가능한 행위는 수행할 수 있어야 한다.
    - 부모 클래스 인스턴스 대신 자식 클래스 인스턴스로 대채해도 문제가 없어야 한다.
- I 인터페이스 분리 원칙( interface segregation principle )
    - 객체가 사용하지 않는 메서드에 의존하지 않아야 한다.
    - 즉, 큰 덩어리의 기능을 구체적이고 작은 단위 로 분리하여 꼭 필요한 메서드만 이용할 수 있도록 하는 것
- D 의존관계 역전 원칙( dependency inversion principle )
    - 의존 관계를 맺을 때 변하기 쉬운 객체게 의존해서는 안된다.
     ex) 자동차가 스노우 타이어에 의존 할 시 의존관계를 스노우타이어가 아닌 일반 타이어에 의존해야 한다.



---

## 자바의 메모리 구조
- 크게 static stack class 영역으로 나누어 진다.
#### Static 영역(= class, method )
- 패키지, 클래스정보, static 멤버, 전역변수 가 메모리에 할당된다.
- 전역변수, static 멤버는 시작 시 할당되며, 패키지와 클래스 정보는 호출 시 할당된다.
- 시스템 종료시까지 사라지지 않는다.

#### Stack 영역
- { 를 만날 때 마다 스택 프레임이 하나씩 생기고, }를 만날 때 마다 스택프레임이 하나씩 사라진다.
- 즉 , 함수 뿐만 아니라 조건문, 반복문에도 스택프레임이 할당된다.
- 스택영역 내부에서 선언된 지역변수는 스택 영역에 할당된다.
- 기본형 타입과 참조 타입의 참조하는 변수는 stack 에 저장된다.

#### heap 
- 생성된 객체( 인스턴스 )타입이 올라간다
- 생성된 객체의 상위 클래스까지 할당 된다.
- 가비지 컬렉터가 관리하는 메모리 영역

---

## 추상 클래스 vs 인터페이스
- 추상 클래스 : 추상 메소드가 하나 이상 포함되어있거나, abstract 로 선언된 클래스를 말한다.
- 인터페이스 : 모든 메소드가 추상 메소드인 것을 말한다.(자바 8에서는 default 를 이용해서 일반 메서드 정의 가능)
#### 공통점 
- 상속하거나 구현하는 클래스에게 내부에 있는 추상 메서드를 구현하도록 한다.
- 인스턴스 생성 ( new 를 통한 객체 생성 )이 불가능하다.

#### 차이점
- 자바는 다중 상속이 안되기 때문에 추상 클래스는 한개만 상속할 수 있으며, 인터페이스 구현은 여러개의 인터페이스를 구현할 수 있다.
- 인터페이스의 모든 변수는 기본적으로 ```public static final``` 이지만 추상 클래스는 아니다.

#### 사용 케이스
- 추상 클래스
    - 클래스가 서로 공통으로 갖는 메서드나 멤머변수가 많은 경우
- 인터페이스
    - 서로 연관성이 없는 클래스가 같은 행동을 할 수 있는 경우 ( cloneable, comparable 등.. )

---

## import vs package 
> 컴파일러에게 소스파일에 사용된 클래스 패키지에 대한 정보를 주는 것 

### import 
- 해당 클래스의 패키지 위치를 생략하고 사용할 수 있다.
- 클래스 선언 보다 상단에 위치해야 한다.
- \* 을 사용하는 것은 하위 모든 **클래스** 를 포함하는 것이지 하위 **패키지** 의 클래스까지 포함할 수 없다.

##### static import 
- import 를 사용하면 클래스의 패키지를 생략할 수 있지만 클래스명은 생략할 수 없다.
- 따라서 자주 사용하는 클래스를 생략하고 싶을 때 **static import** 를 사용한다. 
```
System.out.println() // 원래  

import static java.lang.System.out;
out.println() // System. 을 생략할 수 있다.
```
###### java.lang 
- java.lang 의 경우 기본적으로 자주 사용하는 클래스를 포함하는 패키지 이다.
- 따라서 내부적으로 자동으로 **import** 되어있기 때문에 따로 import 하지 않아도 된다.


### package 
- import 보다 상단에 명시되어야 하며, 소스 파일당 한 번만 명시 할 수 있다.
- 다른 역할을 하는 동일 클래스가 만들어 진 경우 이를 구분하기 위해 패키지를 사용하며, 이때 동일 이름 클래스 중 어떤 클래스를 사용할지 명시하기 위해 사용

---

## 트랜잭션 처리?
- db connection 객체의 setAutoCommit 을 false 로 해주고 트랜잭션이 정상 작동 시 commit 실패시 rollback 을 한다.

---

## 자바의 Collections Framework
> Collection을 구현하는 클래스와 Map을 구현하는 클래스가 있다.
#### Java의 Collection
- 데이터의 순서나 집합적인 저장공간.
- 대표적으로 List Set이 있습니다.  
- List는 중복 데이터 값을 다룰 수 있으며, Set은 중복 데이터 값을 다룰 수 없다.
- List 는 순서가 있으며, Set은 순서가 없다.

#### Java의 Map
- key와 value 형태로 데이터를 저장하는 클래스
- HashMap TreeMap 등이 있다.
- Key는 중복일 수 없지만, Value 는 중복값을 저장할 수 있다.


[이 후 자료구조는 여기 참고](#./datastructure.md)

---

## 어노테이션
- 컴파일러에게 정보를 제공하기 위함
- Override, SurppressWarning 등 여러가지가 있다.

#### 어노테이션 사용 이유
- 예시 : 오버라이드 어노테이션
- 기존에 특정 라이브러리 클래스의 메서드를 오버라이딩 했는데 그 라이브러리가 업데이트 되며 메서드의 **시그니쳐가 변경** 될 수 있다. 이 때 어노테이션을 통해 자바가 오버라이딩 된 매서드를 인식하여 **컴파일 에러** 를 낼 수 있습니다. 이 경우 개발자가 오류를 발견하기 쉬우며, **코드의 가독성** 또한 증가한다.

---

## 마커 인터페이스
> 대표적으로 Serializable 인터페이스가 있다.
- 이는 아무런 메서드를 담고있지 않으며, 이를 구현한 객체가 해당 인터터페이스 타입임을 나타내기 위함이다.

#### 마커 인터페이스 vs 마커 어노테이션
- 일관성을 위해 어노테이션을 사용하는 프레임 워크에서는 어노테이션을 사용하는것이 좋다.
- 차이점은 인터페이스는 자식 클래스 까지 영향을 미치지만, 어노테이션은 해당 클래스에만 영향을 미친다.

---

## Garbage Collector
- heap 메모리 누수를 막기 위해 자바 자체적으로 사용하지 않는 객체의 메모리 할장을 해제하는 것
- 힙 메모리가 충분하지 않은 시점에서 메모리 할당 시 수행됨.
- GC는 가비지(사용하지 않는 객체)를 수거하기 위해 reachability 개념을 사용한다.

#### Reachability 개념
- 객체가 유효한 참조가 있는지 확인하는 것으로 유효한 참조가 있는 것을 reachable , 유요한 참조가 없는 것을 unreachable 이라고 한다.
- 이 때 유효한 참조의 기준은 root set 의 참조 여부로 한다.

###### root set ?
- root set 은 메서드영역의 정적 변수, 메서드 실행 시 사용하는 지역변수, 파라미터가 참조 하는 객체를 root set 이라고 한다.

---
## static 
#### public static void main
- 자바 언어에서 main 메서드가 먼저 실행되기 위해서는 main 메서드가 메모리 상 먼저 로드 뒤어야 한다. 그러기 위해 static 을 사용해 먼저 할당 한 것 이다.
- static 키워드 성격상 인스턴스 없이 호출 가능하다. JVM은 인스턴스 없이 main()을 호출해야 하기 때문에 static 으로 선언한다.

#### static vs non-static 멤버 변수

||static|non-static|
|--|--|--|
|호칭|클래스멤버|인스턴스멤버|
|생성|클래스당 하나|인스턴스당 하나|
|생성시점|객체 생성 이전| 객체 생성시|
|소멸|프로그램 종료시|객체 소멸시|

---

## equals() hashCode() == 
- ```==``` 의 경우 기본 자료형은 객체가 같은지 비교하지만, 객체나 참조 타입의 경우 객체가 가리키는 주소를 비교한다.
- ```equals()``` 의 경우 객체의 내용이 같은지 비교한다.  
#### hashcode 와 equals
- ```equals``` 에 의해 같은 객체라는 결과가 나온 객체를 ```hash``` 값을 사용하는 자료 구조에 넣었을 경우 중복으로 인식하지 않아 중복된 객체 모두 들어갈 수 있다.
- 이를 막기위해 객체 내에 ```equals``` 를 재정의했으면 ```hashcode``` 또한 재정의 하여 ```hash``` 계열의 자료구조 사용 시 오류가 없도록 해야한다.
- ```equals``` 로 같은 객체라면 ```hashcode``` 또한 같은 객체여야 한다.
- 이와 반대로 ```hashcode``` 가 같다고 해서 ```equals``` 가 같지는 않다.

### valueOf vs parseInt
- valueOf 의 경우 객체 타입을 리턴하며, parseInt의 경우 원시타입을 리턴한다.

### valueOf vs toString vs casting
||valueOf|toString|Casting|
|--|--|--|--|
|null|예외 발생하지 않음 (공백 출력)|NPE 발생|NPE 발생|
|Object is not string type|예외발생X|예외발생X|ClassCastException 발생|

---

## 접근 제어자 4가지
> public default protected private
- public 
    - 모든 범위 공개
- default 
    - 동일 패키지 공개
- protected 
    - 같은 패키지, 다른 패키지의 상속을 한 하위 클래스만 공개
- private
    - 해당 클래스 내에서만 공개

---

## 직렬화 역직렬화
- 직렬화는 해당 데이터를 시스템 외부에 저징, 전송 하기 위해 바이트 형태로 변환하는 기술
- 역 직렬화는 외부 저장, 전송 데이터를 받아와 복구 후 사용하기 위함.
- serializable 인터페이스를 구현해야함
- 동일한 serialVersionUID 를 가지고 있어야 함.

---

## String, StringBuilder, StringBuffer
- String 은 객체 타입이기 때문에 heap 에 할당된다. 그러나 불변 객체이기 때문에 기존 객체를 수정 할 경우 새로운 객체가 생성된다. 따라서 이를 막기 위해 ```StringBuffer``` , ```StringBuilder``` 를 사용한다.
- StringBuffer 와 StringBuilder 는 Thread Safe 에 차이가 있습니다. 단일 스레드에서는 StringBuilder 가 성능이 뛰어나지만 멀티 스레드 환경에서 동기화를 지원하지 않기 때문에 멀티 스레드 환경에서는 StringBuffer 를 사용해야 한다.

#### string constant pool
> String 객체를 리터럴로 생성 시 생성되는 영역
- ```""``` 를 이용해서 즉 리터럴로 생성시 String 객체는 heqp 영역 중 constant pool 에 생성된다. pool 에서는 내부적으로 같은 문자열의 객체는 하나만 생성한다.
- new 를 이용해서 생성하면 constant pool 에 생성되지 않기 때문에 매 번 다른 객체가 생성된다.

#### intern() 
- string constant pool 에 해당 문자열과 같은 문자열이 있는경우 이를 반환하고 없을 경우 생성해서 반환하는 메서드

#### thread safe  ? 
- 멀티 스레드 환경에서 어떤 메서드 객체 클래스가 여러 스레드로부터 동시 접근이 이루어 져도 프로그램상 문제가 없는 것을 말한다.

---

## Thread
> 또 하나의 흐름을 만드는 객체 ( 비동기화적 얀산 )
- 내부에 Runnable 객체를 가지고 있다.

#### Thread 상태
- 생성
    - NEW : 스레드가 생성된 상태
- 실행대기
    - RUNNABLE : 스레드가 start() 호출이되며 실행 대기 상태로 진입
- 실행
    - RUN : CPU 를 할당받고 run 메소드를 실행
    - 시분할 실행으로 인해 실행대기와 실행 상태를 번갈아 가며 실행된다.
- 일시정지
    - 경우에 따라 실행중인 스레드가 실행 대기로 가지 않고 일시정지가 된 후 실행대기가 된다.
    - waiting : 다른 스레드에서 통지가 있을 때까지 대기
    - time waiting : 일정 시간 대기
    - blocked : 사용 할 객체가 잠금되어 있는 상태
- 종료
    - 스레드 종료
###### WAITING vs BLOCKED
- BLOCKED
    - 자원을 얻기 위해 모든 스레드가 경쟁을 하는 
    상태
    - 어떤 스레드가 자원을 얻을 지 스케줄러가 관여
- WAITING
    - 선행 스레드의 알림이 있을 때까지 휴면상태가 되며, 스케쥴러가 이를 고려하지 않음

#### thread 구현 방법
> 두가지 방식이 있으며 두 방식 모두 run() 을 오버라이딩 해야함
- thread class 를 상속받는다.
- runnable interface 를 구현한다.
    - Thread로 구현 할 경우 Runnable 의 run() 을 또 구현해야 하기 때문에 Runnable 을 구현하는것이 일반적이다. ( 일관성을 위해 )

#### run() vs start()
- run 의 경우 Thread 클래스에 오버라이딩 된 run 을 호출한다. 이 메서드의 경우 내부 Runnable 객체가 있다면 Runnable 의 run() 을 호출하고, 없다면 비어있는 run() 을 호출한다.
- start 의 경우 JVM 에게 또하나의 콜 스택을 반환받아 또 하나의 흐름을 만드는 것이다.

#### 동기화 vs 비동기화
||동기화|비동기화|
|--|--|--|
|자원 접근|동시 접근 제한|동시 접근 가능|
|작업 순서|순차적 진행|순서 없이 진행|
|다음 명령|현재 진행 명령 완료 대기|현재 진행 명령 상관 없음|
|예시|synchronized|thread|

---

## comparable vs comparator
> 두 객체는 비교 가 아닌 정렬을 위해 사용된다.
- comparable : compareTo(T o1)
    - 일반적인 기준으로 정렬할 때 하며, 기본적인 정렬 기준으로 사용할 때 사용
- comparator : compare(T o1, To2)
    - 사용자 정의 기준을 사용할 때 사용하며, 특수한 목적의 기준을 사용할 때 사용 
- Ex) 직원 정보 저장 배열을 기본적으로 이름 기준으로 정렬 한다면 comparable 을 사용하며, 이 배열에서 연봉 기준으로 정렬된 것을 보고싶을 시 매번 comparable 을 재 구현하는 것이 아닌 **comparator의 compare() 를 구현해서 이를 기준으로 정렬** 한다.

---

## synchronized
- 한 가지 자원에 동시에 한 작업만 수행하도록 하는 것
##### 동기화 메서드 방식
- 메서드에 키워드를 사용해 메서드 자체를 동기화 메서드로 만드는 것 
    - 해당 메서드가 포함된 객체에 lock 을 건다.
##### 동기화 객체에 사용하는 방식 ( 블럭 )
- 동기화가 되어야 할 부분을 묶어 블럭 형태로 사용 하는 것
    - 블럭의 전달인자로 lock 을 걸 대상을 지정한다.
    - this 의 경우 메서드 방식과 동일 

---

## Reflection
> 투영하다 라는 의미
- 객체를 통해 클래스 정보를 분석해 내는 프로그램 기법
- 런타임 시점에서 동적으로 특정 클래스의 정보를 객체화 하여 추출하는 기법
- Class.forName() 을 통해 클래스틑 투영받아 사용한다.
- 위 에서 투영받은 클래스를 통해 ```getField()``` , ```getMethod()``` 를 사용해서 멤버필드, 메서드를 사용할 수 있다.
- 이는 확장성에 아주 좋다.

----

## stream
- 내부 반복자
- 컬랙션의 요소를 하나씩 참조해서 반복적으로 처리할 때 사용
- 람다식을 이용하여 처리하기 때문에 코드가 간결해진다.
    - 기존 iterator 를 이용해서 처리하던 것을 stream 을 이용해서 처리한다.


----

## final 
> 해당 키워드가 붙은 대상을 재 선언 불가능 하도록 하는 것
> Class, Method, Variable, Argument 에 사용가능
#### Use Class
- 해당 클래스 상속 불가능
- 내부 변수 setter를 통해 변경 가능
#### Use method
- 해당 메서드의 클래스 상속 시 자식 클래스에서 오버라이딩 불가능
#### Use Variable
- 해당 변수 재 선언 불가능
- 해당 변수에 다른 객체 할당 불가능
#### Use Argument
- 전달받은 전달인자 변경 불가능

#### finally
- try-catch 구문에 사용되는 키워드로 try-catch 이후 마무리 해줘야 하는 작업이 존재하는 경우 해당 작업을 마무리 할 수 있도록 하는 코드 블록

---

## wrapper class 
- 감싸다 라는 의미로 기본 타입 데이터를 객체화 시킨 클래스
- 기본 타입의 경우 기본 메서드를 사용할 수 없지만, Wrapper 클래스의 경우 compareTo 와 같은 메서드를 사용할 수 있다.
- 따라서 기본 타입의 경우 Collection 객체의 데이터값으로 사용할 수 없지만, Wrapper클래스는 사용할 수 있다. 

#### optional
- 일종의 Wrapper class 로 null 값을 감싸는 Wrapper class 이다.
- NPE( NullPointerException ) 을 방지하기 위한 키워드
- 제네릭 기법을 통해 감싸고자 하는 객체를 ```Optional``` 객체를 통해 감싸며, ```get``` 을 통해 꺼낼 수 있다.

---

## Generic
> 컴파일 과정에서 타입 체크를 해주는 기능
- 다양한 타입의 객체를 다루는 메서드나 컬렉션 클래스에서 
사용한다.
- 컬랙션 객체에 특정 객체만 추가할 수 있도록 한다. 이를 개발자가 일일이 체크하지 않아도 됨.
    - 코드의 간결함, 안정성

---

## Error vs Exception
- Error
    - 시스템 레벨에서 발생한 것
    - 발생 시 복구 불가( try-catch 불가능 )
- Exception
    - 개발자가 작성한 로직에서 발생한 오류
    - 발생 시 후 조치 가능(try-catch 가능)
    - checked exception vs unchecked exception 으로 나뉜다
    
#### Exception
- unchecked exception : 런타임 시 발생할 수 있는 예외로 RuntimeException 을 상속
    - 발생 시 롤백 처리한다.
    - 명시적으로 처리하지 않아도 된다.( throws )
    - NullPointerException
- checked exception : 컴파일 시 발생할 수 있는 예외
    - 발생 시 롤백하지 않는다.
    - 명시적으로 처리해야 한다 ( try-catch )
    - IOException, SQLException
    
---

## 람다
- 익명 함수
    - 함수의 호출부가 없이 매개변수와 몸체로만 이루어짐
- 코드의 간결성
- 병렬처리 가능
