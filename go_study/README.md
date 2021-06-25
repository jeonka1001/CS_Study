# GO Language 공부

## 기초
#### 디버깅 및 실행
> 컴파일, 빌드, 실행 하는 방법
- 컴파일 : ```go run <file_name>``` 작성한 파일 실행 ( 코드 수정 시 저장을 따로 해줘야 한다. ) 
- 빌드 : ```go build <file_name>``` 해당 파일에 해당되는 실행파일 생성 ( 코드 수정 시 저장을 따로 해줘야 한다. )
- 실행 : ```./<file_name>``` 해당 파일 바로 실행 ( 코드 수정 시 재 빌드 해줘야 한다. )
#### 겪은 오류
- can't not found moudle : go env -w GO111MODULE=auto  
    - ```go```의 ```1.10``` 을 포함한 상위 버전에서는 vgo 를 이용해여 GO modules 를 사용했었다.  
    - ```go``` 의 ```1.11``` 버전부터는 go mod 커맨드를 이용하여 GO modules 를 사용할 수 있다.  
    - 따라서 두 방식의 공존을 위해 ```GO111MODULE``` 라는 임시 환경 변수가 생겼다.  
###### GO111MODULE
```on``` : GOPATH 와 관계없이 GO modules 의 방식대로 사용  
```off``` : GO modules 사용하지 않고 기존 방식대로 사용 (GOPATH, verdor/)  
```auto``` : GOPATH/src 의 내부 커맨드는 기존 방식, 외부 커맨드는 GO modules 방식대로 사용

- main redeclared in this block 	previous ... : GO 언어의 경우 ```main``` 함수는 ```package``` 단위로 하나만 존재할 수 있다.

## 변수 선언
1. 명시적 선언 : var 변수명 변수타입
    - ex ) ```var name string``` , ```var name string = "jeonka"```
2. 암시적 선언 : var 변수명 
    - ex ) ```var name```, ```var name = "jeonka"```
3. 짧은 변수 선언 : 변수명 := 초기화 값 ( 전역변수로는 사용 불가능 )
    - ex ) ```name := "jeonka"```, ```num1, name1 := 1,"jeonka"```
    - 만약 여러개의 변수를 짧은 변수 선언 방식으로 선언 할 경우 그 중 **하나 이상의 변수가 새로운 변수** 라면 같이 선언된 기존 변수들은 선언이 아닌 **할당의 방식** 으로 동작된다.

## if - else
- IF 구조 : ```if 조건 {} ```
    - IF의 **조건** 의 경우 소괄호가 없어도 되며, ```if``` 와 공백으로 구분되어야 한다.
- ELSE 구조 : ```else {}```
    - ```else``` 키워드의 경우 IF/ELSE IF 문의 닫는 중괄호와 같은 행에 있어야 한다.
- ELSE IF 구조 : ```else if 조건 {}```
     - ```else if``` 또한 IF/ELSE IF 문의 닫는 괄호와 같은 행에 있어야 한다.

## switch 
- 구조
```
    swtich 변수 {
        case 조건:
            // 행위
        case 조건:
            // 행위
        default:
            // 행위
    }
```
- 예시 ( 2가지 방식 모두 가능 )
```
    // 1. switch 변수, 조건 간단
 	var age = 0;
 	switch age{
 	case 1:
 		fmt.Println("초등생1");
 	case 2 :
 		fmt.Println("초등생2");
 	default:
 		fmt.Println("학생 아님");
 	}

    // 2. switch, 조건 복잡
 	switch {
 		case age > 1 :
 			fmt.Println("초딩1")
 		case age < 0 :
 			fmt.Println("초딩3")
 		default :
 			fmt.Println("학생 아님")
 	}
```
- switch 는 break 가 필요없으며, 각 case 에 해당하는 행위가 끝나면 switch 를 빠져나간다.

## 반복문
- for 문법
1. for 조건 {}
```
x := 1;
for x < 5{
    fmt.Println(x);
    x++;
}
```
2. for 반복변수; 조건; 증감 {}
```
for i := 1 ; i < 5 ; i++ {
    fmt.Println(i);
}
```

**for 문** 에는 ```break```, ```continue``` 사용 가능.

## 수학 라이브러리, 한개 이상 라이브러리 import
1. 수학 라이브러리는 ```math``` 에 있다.
2. ```fmt``` 라이브러리와 ```math``` 라이브러리(여러개의 라이브러리)를 같은 파일에 ```import``` 하기 위해서는 소괄호를 사용한다.
```
ex) import (
    "math"
    "fmt"
)
```

## Array vs Slice
> Slice vs Array
- Array : 정적인 배열
    - var arr [...]int 
    - arr := [...]int{}
    - ...은 특정 숫자를 지정해 줄 수 있고, 지정하지 않을 경우 {} 를 통해 초기화를 해줘야 한다.
- Slice : 가변 배열
    - var slice []int = []int{}
    - slice := []int{}  
||Slice|Array|
|---|---|---|
|input|append()|방번호접근|
|복사|얕은복사(원본과 주소 같음)|깊은복사(원본과 주소 다름 )|
|cap|동적|정적|
  
Slice 의 얕은 복사를 막기 귀해 copy 함수를 사용한다.  

배열 및 슬라이스의 향상된 반복문은 range 를 사용한다.
```
for _, v := range arr {

}
```
여기서 _ 는 익명의 반복자로, 특정 변수를 선언해 줄 경우 사용해야하며, 사용하지 않을 시 익명으로 선언 가능하다.  
위 의 경우 arr의 범위만큼 반복문을 수행한다.

## Map
> Key, Value 를 하나의 묶음으로 저장하는 자료구조  
- 선언방식
```
var 이름 map[key_type]value_type = map[key_type]value_type{
        //.. 초기화
    }
```
- 예시
```
var zoo map[string]int = map[string]int {
    "코끼리":7,
    "사자":5,
}
```
특이점은 마지막 원소 뒤에도 ```,``` 를 써줘야 한다.  
값을 넣을 때는 아래와 같이 한다.  
```
// 이름[key] = value
zoo["코알라"] = 9
```
값을 삭제 시 ```delete``` 함수를 사용한다.
```
delete(이름,key_value)
delete(zoo,"코알라")
```
만약 키가 존재하지 않는다면 아무런 변화도 일어나지 않는다.

- 출력
fmt.Println() 을 통해 **zoo** 를 출력하면 ***map 의 형태 그대로** 출력이 된다. 그러나 **map의 특정 키에 접근** 해서 출력을 하면 해당하는 **value** 가 출력이 된다.

- map 응용 ( 추후 수정 )
```val, ok := zoo["기린"]```  
위의 코드의 결과는 ```기린```이 존재 할 경우 val 에 기린의 값, ok 에 true 가 들어간다. 그러나 존재하지 않을 경우 0, false 가 들어간다. 
이는 어떤 원리인지 더 찾아봐야겟다.

## 함수
- 함수의 선언  
GO 언어의 함수는 아래와 같이 선언된다.
```
func 함수명( 매개변수명 매개변수타입) 반환타입{
    // 함수 정의
}

fun operator ( x int, y int) (int,int){ 
    return x+y, x-y
}
```
위 코드에서 볼 수 있듯이 매개변수, 반환갑이 여러개이면 매개변수처럼 하나하나 선언할 수 있고, 반환값처럼 한번에 선언할 수 있다.
```
func operator(x,y int) (re1, re2 int){
    re1 = x + y
    re2 = x - y
    return
}
```
만약 위 코드처럼 반환 값에 이름을 선언하면 **return 만 하더라도** 알아서 해당 값이 **반환** 된다.

#### defer
> 함수가 반환값을 return 후 실행하는 구문
만약 아래와 같은 코드가 있다고 가정한다.
```
fun foo (x, y int) int {
    defer fmt.Println("add finish")
    fmt.Println("foo operate")
    return x + y
}
func main(){
    fmt.Println(foo(5,6))
}
```
위 의 경우 실행 결과는 아래와 같다.
```
foo operator
add finish
11
```
즉, ```return``` 전까지 함수의 기능을 수행 후 특정 값을 반환 후 함수 종료 직전 ```defer``` 를 수행한다. 그 후 함수가 종료된다.

#### 함수의 변수화
> 함수는 변수처럼 선언할 수 있고, 변수처럼 사용될 수 있다.  
만약 아래와 같은 함수가 선언되어 있다고 가정한다.
```
func foo( x int ) int {
    return x + 2
}
```
이를 ```main``` 함수에서는 변수처럼 사용할 수 있는 방법이 존재한다.
```
func main (){
    foo_value := foo // foo 변수화
    foo_value() // foo 실행
}
```
또는 선언과 동시에 변수화도 가능하다. 이는 익명함수와 비슷하다.  
```
func main(){
    foo_value := fun(x int )int {
        return x + 10
    } // foo_value 함수 선언
    foo_value() // 호출

}
```
위 의 방식대로 함수를 변수화 했다면 함수의 전달인자로 함수를, 함수의 반환값으로 함수를 사용할 수 있다.  
위에서 만든 ```foo_value``` 를 전달인자로 사용한다면 아래와 같다.
```
func func_test( func_param func(int)int ){
    fmt.Println(func_param(7))
}

func main(){
    func_test(foo_value) // 위에서 만든 함수
}
```
```foo_value``` 의 매개변수, 반환값이 int, int 이므로 func_test 의 매개변수 또한 ```func(int)int``` 가 된다.  

또한 아래와 같이 함수의 반환값을 함수로 할 수 있다. 이는 익명함수와 비슷하다.  
```
func foo_test(x int) func() {
    return func{ fmt.Println("hello",x)}
}
func main(){
    returnFunction := foo_test(5)
    returnFunction()
}
```
위 처럼 함수의 반환값을 함수로 했으므로 변수에 해당 반환값을 저장하여 실행할 수 있다. 바로 실행하고 싶은 경우 ```foo_test(5)``` 를 호출 후 바로 ```foo_test(5)()``` 로 실행할 수 있다.