# 면접 준비 - JAVA
> 나만의 언어로 하는 면접준비

## 질문 목차
- [JDK, JRE, JVM](#jdk,-jrm,-jvm)
- [자바의 메모리 구조](#자바의-메모리-구조)
- [추상 클래스와 인터페이스](#추상-클래스-vs-인터페이스)
- [다형성](#다형성) -- 수정하기
- [Import vs Package](#import-vs-package)
- [트랜잭션처리](#트랜잭션처리)-- 수정하기
- [자바의 대표 collection](#자바의-대표-collection)
- [어노테이션](#어노테이션)
- [마커 인터페이스](#마커-인터페이스)
- [오버로딩 vs 오버라이딩](#오버로딩-vs-오버라이딩)
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
- 두 JDK의 차이는 Oracle 에서는 재산권이 걸린 플러그인을 제공한다.
- JDK(8버전 이후)가 상업적 용도로 사용된 경우 라이센스 비용을 지불해야 한다.
- 따라서 기업에서는 jdk8 버전을 많이 사용하고 있다.
- 초기 ide 설치 후 jdk 11 을 사용했으나, 위 의 사항을 알게되고 jdk 8 버전을 사용하고 있다.

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

## 다형성
- 상속과 큰 관련이 있는 개념으로, **여러가지 형태를 가질 수 있는 능력** 을 의미 합니다.
- 조상 클래스(인터페이스) 타입 변수로 자손 클래스의 인스턴스 참조가 가능 한 것.
- ```Animal``` 이라는 클래스를 상속받아 ```dog``` 와 ```cat``` 클래스를 만들었다 하면, ```Animal``` 타입의 변수는 ```dog``` 와 ```cat``` 객체를 참조할 수 있다.

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

## Java의 대표 Collection
- 대표적으로 List Map Set Stack Queue 가 있습니다.  
- 이 Collection 은 추상화된 Interface 이며, 특정 기능에 맞게 Interface를 구현한 구현체 또한 존재합니다.  
- List 를 예로 들자면 List를 구현한 ArrayList 와 LinkedList 가 존재합니다.

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

## 오버로딩 vs 오버라이딩
- 오버로딩  : 이름이 같은 메서드로 다른 기능을 구현하는 것으로 함수 선언 시 이름은 같고 전달인자는 다르게 선언 하는 것.
    - 리턴값 ? 함수 호출 단계에서 리턴값으로 함수를 구분할 수 없기 때문에 리턴값을 다르게 한다해서 다른 함수로 인식하지 않는다 .

- 오버라이딩 : 상위 클래스의 특정 함수를 재 구현한 것으로 매서드명, 매개변수, 리턴타입을 모두 같게 해야한다. 재정의 시 메서드 내에서 super()를 통해 부모 메서드를 호출할 수 있으며, 이를 하지 않을 시 부모 메서드는 호출되지 않는다.( 가려진다 )

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

## Exception
- unchecked exception : 런타임 시 발생할 수 있는 예외로 RuntimeException 을 상속
    - 발생 시 롤백 처리한다.
    - 명시적으로 처리하지 않아도 된다.( throws )
    - NullPointerException
- checked exception : 컴파일 시 발생할 수 있는 예외
    - 발생 시 롤백하지 않는다.
    - 명시적으로 처리해야 한다 ( try-catch )
    - IOException, SQLException

---

## public static void main
- 자바 언어에서 main 메서드가 먼저 실행되기 위해서는 main 메서드가 메모리 상 먼저 로드 뒤어야 한다. 그러기 위해 static 을 사용해 먼저 할당 한 것 이다.

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
- 직렬화는 해당 데이터를 시스템 외부에 저징, 전송 하기 위한 작업
- 역 직렬화는 외부 저장, 전송 데이터를 받아와 복구 후 사용하기 위함.

---

## String, StringBuilder, StringBuffer
- String 은 객체 타입이기 때문에 heap 에 할당된다. 그러나 불변 객체이기 때문에 기존 객체를 수정 할 경우 새로운 객체가 생성된다. 따라서 이를 막기 위해 ```StringBuffer``` , ```StringBuilder``` 를 사용한다.
- StringBuffer 와 StringBuilder 는 Thread Safe 에 차이가 있습니다. 단일 스레드에서는 StringBuilder 가 성능이 뛰어나지만 멀티 스레드 환경에서 동기화를 지원하지 않기 때문에 멀티 스레드 환경에서는 StringBuffer 를 사용해야 한다.

#### thread safe  ? 
- 멀티 스레드 환경에서 어떤 메서드 객체 클래스가 여러 스레드로부터 동시 접근이 이루어 져도 프로그램상 문제가 없는 것을 말한다.

#### thread 구현 방법
- thread 클래스를 상속받는다.
- runnable interface를 구현한다.

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

### volatile

### Atomic class

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

---

## wrapper class 
- 감싸다 라는 의미로 기본 타입 데이터를 객체화 하기 위한 클래스
- 기본 타입의 경우 기본 메서드를 사용할 수 없지만, Wrapper 클래스의 경우 compareTo 와 같은 메서드를 사용할 수 있다.
- 따라서 기본 타입의 경우 Collection 객체의 데이터값으로 사용할 수 없지만, Wrapper클래스는 사용할 수 있다. 

#### optional
- 일종의 Wrapper class 로 null 값을 감싸는 Wrapper class 이다.
- NPE( NullPointerException ) 을 방지하기 위한 키워드
- 제네릭 기법을 통해 감싸고자 하는 객체를 ```Optional``` 객체를 통해 감싸며, ```get``` 을 통해 꺼낼 수 있다.
