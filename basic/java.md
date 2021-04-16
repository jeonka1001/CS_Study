# 면접 준비 - JAVA
> 나만의 언어로 하는 면접준비

## 질문 목차
- [자바의 대표 Collection](#java의-대표-collection에는-무엇이-있을까?)
- [Java version](#java-사용-버전)
- [트랜잭션](#트랜잭션-처리?)

---

## JDK, JRE, JVM
> 범주는 JDK 안에 JRE 안에 JVM 이 있다.  
- JDK : Java Development Kit 의 약자로 JRE + 개발을 위해 필요한 도구 를 포함한다.  
- JRE : Java Runtime Enviroment 의 약자로 JVM 이 자바 프로그램을 동작시킬 때 필요한 라이브러리, 기타 파일을 가지고 있다.  
- JVM : Java Virtual Machine 의 약자로 자바 소스코드로부터 만들어지는 자바 바이너리 파일을 실행할 수 있다.  

#### Oracle JDK vs OpenJDK
- 두 JDK의 차이는 Oracle 에서는 재산권이 걸린 플러그인을 제공한다.
- JDK(8버전 이후)가 상업적 용도로 사용된 경우 라이센스 비용을 지불해야 한다.
- 따라서 기업에서는 jdk8 버전을 많이 사용하고 있다.
- 초기 ide 설치 후 jdk 11 을 사용했으나, 위 의 사항을 알게되고 jdk 8 버전을 사용하고 있다.

--- 

## 자바의 메모리 구조


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

##  오버로딩 vs 오버라이딩
- 오버로딩  : 이름이 같은 메서드로 다른 기능을 구현하는 것으로 함수 선언 시 이름은 같고 전달인자는 다르게 해야한다. 이는 자바 자체적으로 함수 호출 시 어떤 함수를 호출하는 것인지 명확하게 하기 위함이다.

- 오버라이딩 : 상위 클래스의 특정 함수를 재 구현한 것으로 매서드명, 매개변수, 리턴타입을 모두 같게 해야한다. 재정의 시 메서드 내에서 super()를 통해 부모 메서드를 호출할 수 있으며, 이를 하지 않을 시 부모 메서드는 호출되지 않는다.


## 트랜잭션 처리?
- db connection 객체의 setAutoCommit 을 false 로 해주고 트랜잭션이 정상 작동 시 commit 실패시 rollback 을 한다.

## Java의 대표 Collection에는 무엇이 있을까?
- 대표적으로 List Map Set Stack Queue 가 있습니다.  
- 이 Collection 은 추상화된 Interface 이며, 특정 기능에 맞게 Interface를 구현한 구현체 또한 존재합니다.  
- List 를 예로 들자면 List를 구현한 ArrayList 와 LinkedList 가 존재합니다.

[이 후 자료구조는 여기 참고](#./datastructure.md)

---

## 어노테이션
- 컴파일러에게 정보를 제공하기 위함
- Override, SurppressWarning 등 여러가지가 있다.


## Garbage Collector
- heap 메모리 누수를 막기 위해 자바 자체적으로 사용하지 않는 객체의 메모리 할장을 해제하는 것
- GC는 가비지(사용하지 않는 객체)를 수거하기 위해 reachability 개념을 사용한다.

#### Reachability 개념
- 객체가 유효한 참조가 있는지 확인하는 것으로 유효한 참조가 있는 것을 reachable , 유요한 참조가 없는 것을 unreachable 이라고 한다.
- 이 때 유효한 참조의 기준은 root set 의 참조 여부로 한다.

###### root set ?
- root set 은 메서드영역의 정적 변수, 메서드 실행 시 사용하는 지역변수, 파라미터의 참조, 자바 네이티브 인터페이스에 의해 생성된 객체의 참조 가 이루어 지는 객체를 root set 이라고 한다.

## Exception
- unchecked exception : 런타임 시 발생할 수 있는 예외
    - 발생 시 롤백 처리한다.
    - 명시적으로 처리하지 않아도 된다.( throws )
- checked exception : 컴파일 시 발생할 수 있는 예외
    - 발생 시 롤백하지 않는다.
    - 명시적으로 처리해야 한다 ( try-catch )

## public static void main
- 자바 언어에서 main 메서드가 먼저 실행되기 위해서는 main 메서드가 메모리 상 먼저 로드 뒤어야 한다. 그러기 위해 static 을 사용해 먼저 할당 한 것 이다.



## 참고 사이트
- [JDK, JVM, JRE](#https://wikidocs.net/257)