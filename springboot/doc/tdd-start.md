## TDD
> test driven development - 테스트 주도 개발

#### tdd와 단위 테스트는 다른 이야기 이다 ?
- TDD 의 과정
    - 항상 실패하는 테스트를 먼저 작성한다
    - 테스트가 통과하는 프로덕션 코드를 작성한다
    - 테스트가 통과하면 프로덕션 코드를 리팩토링 한다.

이 중 첫 번째 단계를 단위 테스트 ( 기능 단위의 테스트 코드 작성 ) 라고 한다.

#### 단위 테스트 ?
> 개발 시 프로그램 전체 테스트가 아닌 기능 단위로 테스트를 하는 것
- JUnit(Java), DBUnit(DB) 등..
###### 장점 ?
- 추후 서비스 확장 시 기존 기능이 올바르게 작동하는지 확인 할 수 있다.
- 문제를 초기 단계에 파악할 수 있음
- 수동 검증( 블랙 박스 테스트 )를 하지 않아도 된다.


## 단위 테스트
먼저 테스트용 클래스를 하나 작성한다.

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}

```
```Application``` 클래스는 해당 프로젝트의 메인 클래스가 된다.  
```@SpringBootApplication``` 은 스프링 부트의 자동 설정, 스프링 빈 읽기 및 생성 을 자동으로 해준다. 이 때, 해당 어노테이션이 있는 클래스의 위치부터 설정, 읽기, 생성을 하기 때문에 **해당 클래스는 프로젝트의 최 상단에 위치** 해야 한다.  
해당 어노테이션의 ```run``` 으로 인해 springboot 내장 WAS 가 실행된다. 따라서 톰켓을 따로 설치 할 필요가 없으며, **스프링 부트로 만들어진 jar 파일** 을 실행하면된다.  

해당 클래스와 동일 선상에 ```web``` 이라는 패키지를 만든 후 패키지 내부에 컨트롤러를 생성한다. 앞으로 개발하는 모든 **컨트롤러와 관련된 클래스는 해당 패키지 하위** 에 위치하면 된다.

이 후 컨트롤러 코드를 작성한다.

```
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
```
@RestController (= @ResponseBody) : JSON을 반환하는 컨트롤러로 지정됨  
@GetMapping : 기존 @RequestMapping(method = RequestMethod.GET) 어노테이션과 같은 의미  

- 위 의 클래스를 **WAS 를 실행하지 않고** 단위테스트를 할 수 있다.

#### 단위 테스트를 하기위한 클래스

위 에서 작성한 클래스를 단위테스트 하기 위해서는 ```src/test/java``` 하위 패키지로 테스트 할 컨트롤러를 똑같은 패키지에 작성해준다. 따라서 ```HelloController``` 를 테스트 할 것이기 때문에 ```HelloControllerTest``` 를 ```com.jeonka.web``` 하위에 작성해 준다. ( 패키지 구조는 동일해야하며, 해당 클래스명은 일반적으로 **테스트 할 클래스명 + Test 로 작성한다. )

```
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

// 정적 import
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

// 테스트 클래스
@RunWith(SpringRunner.class) 
@WebMvcTest(controllers=HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다()throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
}
```

```@RunWith(SpringRunner.class)``` : JUnit 과 springboot-test 사이 연결자 역할(테스트 실행 등록 어노테이션)  
```@WebMvcTest``` : 컨트롤러에만 해당하는 스프링 테스트 어노테이션  
```MockMvc``` 클래스 : 웹 API 테스트 시 사용, 스프링 MVC 테스트의 시작점  
```mvc.perform(get("/hello))``` : ```"/hello"``` 에 ```GET``` 방식 요청을 테스트 한다.
```status().isOk()``` : 상태코드 Ok(200 번) 응답인지 체크  
```content().string(hello)``` : 응답 본문의 반환 문자열(페이지명)을 체크한다.  

이 후 해당 메서드 테스트 시 통과했다면 ```Tests Passed : 1``` , 실패 시 ```Tests Failed : 1``` 이 출력된다.



