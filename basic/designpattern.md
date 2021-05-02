# 면접준비 - DesignPattern
> 나만의 언어로 준비하는 면접준비

## 목록

---

## 디자인 패턴 분류
> 생성 구조 행위
- 생성 패턴
    - **객체 생성** 에 관련된 패턴
- 구조 패턴
    - 클래스나 객체를 조합해 **더 큰 구조를 만드는** 패턴
- 행위 패턴
    - 객체나 클래스 사이 알고리즘이나 **책임 분배** 에 관련된 패턴

## 생성 패턴

#### 싱글톤 패턴
> 전역 변수를 사용하지 않고 객체를 한개만 생성해 어디서든 참조 가능하도록 하는 패턴
- 여러 객체를 생성하지 않기 때문에 비용이 적다. ( 메모리, 성능 효율적)
- 객체가 무조건 한개만 생성하는 것을 보장
- EX ) DB connection pool 

#### 예시코드
> 하나의 프린터가 있고, 이 프린터 하나를 공유하며 사용하는 경우라면 ?

- 특징 
    - 생성자를 private 으로 선언하기 때문에 해당 클래스를 마음대로 생성할 수 없다.
    - getInstance 를 통해 해당 클래스의 인스턴스를 얻어서 사용하며 static 메서드 이기 때문에 어디에서나 사용 가능하다.
    - 해당 클래스의 인스턴스는 private static 이기 때문에 1개만 생성 가능하다.

```
public class Printer{
    private static Printer printer;

    private Printer(){}

    public static Printer getInstance(){
        if(printer == null){
            printer = new Printer();
        }
        return printer;
    }
}
```



###### 빌더 패턴

###### 팩토리 메서드 패턴

###### 추상 팩토리 패턴

###### 프로토타입 패턴

---

## 구조 패턴

###### 어탭터 패턴

###### 데코레이터 패턴

###### 프록시 패턴

---

## 행위패턴

#### 전략 패턴
> 객체가 할 수 있는 행위를 전략으로 두고 행위가 변하거나 필요 할 경우 동적으로 전략을 변경하는 것
- 기존 객체의 기능이 확장될 경우 해당 클래스의 코드를 수정하는 것이 아닌, 새로운 행위를 만들어 변경할 수 있다. ( 개방 폐쇠 원칙에 적합 )
###### 예시코드 
> 기존 도로에서 달리던 버스가 이제 기차처럼 철로를 달릴 수 있다면?
- 특징
    - 객체가 사용하는 기능을 하나의 클래스로 만들어 이를 전략으로 사용한다.


```
## 기능 관련 코드
interface MoveStrategy{
    public void move();
}

class MoveRoad implements MoveStrategy{
    public void move(){
        System.out.println("길에서 움직임");
    }
}

class MoveRail implements MoveStrategy{
    public void move(){
        System.out.println("철로에서 움직임");
    }
}

class Moving{
    private MoveStrategy moveStrategy;

    public void move(){
        moveStrategy.move();
    }

    public void setMoveStrategy(MoveStrategy moveStrategy){
        this.moveStrategy = moveStrategy;
    }
    
}

class Bus extends Moving{

}

class Train extends Moving{

}
```
위 와 같은 코드가 작성되었다면 아래 코드에서는 다음과 같이 작동할 수 있다.

```
public class Main{
    public static void main(String[] args){
        Bus bus = new Bus();
        Train train = new Train();

        // 초기 이동방식 설정
        bus.setMoveStrategy(new MoveRoad());
        train.setMoveStrategy(new MoveRail());

        bus.move(); // 도로 위에서 움직인다.
        train.move(); // 철로 위에서 움직인다.

        // 만약 철로 위에서 움직이는 버스가 생긴다면?
        bus.setMoveStrategy(new MoveRail());

        bus.move(); // 철로 위에서 움직인다.
    }
}
```
새로운 방식으로 기능이 확장된다면 해당 클래스의 전략을 변경해주며 내부 코드 수정을 하지 않아도 기능 확장을 할 수 있다.

###### 이터레이터 패턴

###### 옵저버 패턴

###### 커맨드 패턴

###### 템플릿 매서드

---

