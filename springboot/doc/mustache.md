# Mustache 
- mustache 를 이용한 화면 개발

#### 템플릿 엔진이란 ? 
> 지정된 템플릿 양식과 데이터가 합쳐져 HTML 문서를 출력하는 소프트웨어

###### 서버 사이드 템플릿 엔진? 
- 서버에서 구동되는 템플릿 엔진
- 서버에서 코드로 문자열을 만든 후 html 로 변환 후 브라우저로 전달

###### 클라이언트 사이드 템플릿 엔진 ?
- 브라우저 위에서 작동하는 템플릿 엔진
- 서버에서 JSON, XML 형태로 데이터만 전달 한 후 클라이언트에서 이를 혼합해 화면을 구성


#### 장점
- 문법이 심플하다.
- View 와 Server가 명확히 분리된다.
- Java, Javascript 모두 지원하기 때문에 하나의 문법으로 클라이언트/서버 템플릿 모두 사용 가능하다.

## mustache 생성
- mustache 를 사용하기 위해서는 아래와 같이 해야한다.

1. marketplace 에서 ```mustache``` plugin 설치 후 재시작
2. build.gradle 에서 의존성 주입 ```.......starter.mustache```
3. ```src/main/resources/templates``` 에 ```.mustache``` 파일 생성
4. ```controller``` 생성 후 URI mapping ( 이 때 반환하는 String 값은 해당 페이지명 이며, 앞에 붙는 경로와 뒤에 붙는 확장자의 경우 starter plugin이 알아서 설정해 준다.)


## View 화면 개발
> bootstrap 과 jQuery를 이용하여 개발
- 레이아웃 방식으로 해당라이브러리를 추가한다.
    - 공통 영역을 별도의 파일로 분리하여 필요한 곳에서 가져다 쓰는 방식
- 해당 라이브러리 사용을 위해서는 라이브러리를 index.mustache 에 추가해야 한다.
    - 어느 파일에서나 사용하기 때문

#### 주의 할 점
- css 와 같이 화면 구성에 필수적인 파일은 header에서 include 한다.
- js 와 같이 화면 구성에 필수적이지는 않지만, 기능에 필요한 파일 ( 화면 구성보다 늦게 로딩되어도 상관 없는 파일 ) 은 footer 에 import 한다.
- 화면 로딩 시 header - body - footer 순으로 로딩되기 때문에 로딩 시간을 단축하기 위함.

- 스프링 부트는 src/main/resources/static 에 위치한 이미지, js, css 파일의 경우 정적 파일로 인식한다.( 경로상 ```/``` 에 위치한다고 간주 함)
