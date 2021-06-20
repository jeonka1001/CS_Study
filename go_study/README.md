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
