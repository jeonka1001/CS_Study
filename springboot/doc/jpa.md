## JPA 
> Java 표준 ORM 기술
- SQL에 종속적인 개발을 하지 않도록 한다.
- ```Spring``` 에서 ```JPA``` 를 다루기 위해서는 ```Spring Data JPA``` 를 이용하여 다룬다.
    - 이는 의존성을 느슨하게 하기 위함.

---

### Spring Data JPA 
- 객체 값의 불변을 지켜야 할 경우 setter 는 만들지 않는다. 해당 엔티티 값을 어디서나 변경할 수 있고, 어느 부분에서 변경 된 것인지 파악하기 어렵기 때문.

- 삽입의 경우 생성자를 통해 갑을 채운 후 DB에 삽입한다.
- 수정의 경우 해당 이벤트에 맞는 public 메소드를 호출한다. ( 아래 예시 )

1. 잘못 된 예 - 값을 어디서나 변경 가능
```
public void 주문취소(boolean status){
    this.orderStatus = status;
}
```
2. 올바른 예 - 해당 의도에 맞는 이벤트
```
public void 주문취소(){
    this.orderStatus = false;
}
```


##### 의존성 등록

```Spring Data JPA``` 를 이용하기 위해서는 ```build.gradle``` 에 의존성을 등록해야 한다.  

즉, ```org.springframework.boot:spring-boot-starter-data-jpa``` 와 ```com.h2database:h2``` 를 ```dependencies``` 블록에 등록한다.  

등록 후 코드는 ```build.gradle``` 의 ```dependencies``` 블록은 다음과 같다.  

```

dependencies {
    compile('org.springframework.boot:spring-boot-starter-web')
    compile('org.projectlombok:lombok')
    compile('org.springframework.boot:spring-boot-starter-data-jpa')
    compile('com.h2database:h2')
    testCompile('org.springframework.boot:spring-boot-starter-test')
}

```
```org.springframework.boot:spring-boot-starter-data-jpa``` : 스프링 부트용 ```Spring Data JPA``` 추상화 라이브러리  
```com.h2database:h2``` : 인메모리 관계형 데이터베이스로 재시작 시 초기화 되기 때문에 테스트 용도로 많이 사용된다.  


#### 코드 작성
실제 DB 와 매핑 될 ```Posts``` 라는 클래스를 ```domain``` 패키지 하위에 작성 할 것이다. 여기서 ```domain``` 패키지는 개발 할 **서비스의 비즈니스 관련 클래스** 가 들어가는 패키지이다.

###### Posts 클래스 작성 코드

```
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // defualt 생성자
@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더패턴 클래스 생성 
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
```
- ```@NoArgsConstructor``` : 디폴트 생성자 생성
- ``@Entity`` : 테이블과 매핑 될 클래스임을 나타낸다.
    - **기본값으로 클래스의 카멜케이싱 이름을 언더바 네이밍으로 테이블 이름을 매칭한다**  
    - ex ) SalesManager.java -> sales_manager table
- ```@Id``` : 매핑 될 테이블의 PK
- ```@GeneratedValue``` : PK 생성 규칙 ( GenerationType.IDENTITY 를 추가해야 autoincrement 가 된다. )
- ```@Column``` : 기본값 외 추가로 변경이 필요한 옵션이 있을 경우 사용
    - ex ) varchar(255) 를 varchar(500) 또는, 타입을 text 로 변경 등
- 빌더패턴을 이용한 이유는 ? 
    - 생성자의 경우 런타임 전까지 어떤 값을 보내야 하는지 명확히 알 수 없지만, 빌더 패턴은 명확히 어떤 값을 넘겨야 하는지 인지할 수 있다.


###### PostsRepository 코드 작성
- MyBatis 에서 Dao 라 불리는 객체와 같은 것
- JPA 에서는 Repository 라 불리며, 인터페이스로 생성한다.
- 이 후 ```JpaRepository<Entity클래스, PK타입>``` 을 상속한다.  

```
package com.jeonka.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    
}
```

해당 클래스를 작성하면 자동으로 CRUD 메소드가 생성된다. 여기서 주의 할 점은 상속한 ```JpaRepository``` 의 ```Entity``` 클래스와 같은 위치에 위치해야 한다.

###### PostRepositoryTest 작성
- save() , findAll() 테스트 수행

```
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){

        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jeonka@naver.com")
                .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
```
- ```@After``` : 단위 테스트가 끝날 때 마다 수행되는 메소드 지정
    - 배포 전 전체 테스트 수행 시 테스트간 데이터 침범을 막기 위해 사용
- ```postsRepository.save()``` : insert/update 를 수행
    - id 값이 있다면 update 없다면 insert
- ```postsRepository.findAll()``` : 전체 조회
  
  
###### 해당 테스트에서 수행한 쿼리를 보기 위해서는?
```src/main/resources``` 하위에 ```application.properties``` 라는 ```spring-boot-configuration``` 파일을 만든 후 ```spring.jpa.show_sql=true``` 문구를 추가한 후 테스트를 수행한다.

--

## CRUD 기능 구현
해당 기능의 API를 구현하기 위해서는 3개의 클래스가 필요하다
- Request 데이터를 받을 Dto
- API 요청을 받을 Controller
- 트랜잭션, 도메인 기능 순서 보장을 위한 Service

#### Q) Service 에서 비즈니스 로직을 처리하는것이 아닌가?
- A) Service 에서는 트랜잭션, 도메인 간 순서 보장 역할을 한다.

이를 이해하기 위해 Spring 웹 계층을 알아야 한다. Spring 웹 계층에는 3개의 층으로 나뉘어 있다.
- ```Web Layer``` : ```@Controller``` 와 ```View``` (JSP 등..) 의 영역이며, 외부 요청 응답에 대한 영역
- ```Service Layer``` : ```@Service``` 영역으로 트랜잭션이 사용되어야 하는 영역
- ```Repository Layer``` : ```DataBase``` 에 접근하는 영역 (DAO)

3개로 나뉘어진 각 계층을 연결하는 역할은 DTO와 Domain Model 이 한다.
- ```DTO``` : ```Web Layer``` 와 ```Service``` 영역의 데이터 교환을 담당한다.
- ```Domain Model``` : 비즈니스 로직을 담당하는 부분으로 ```@Emtity``` 와 ```VO``` 등을 이 예로 볼 수 있다.

#### 등록 기능 구현
해당 기능을 구현하기 위해 ```@Controller``` ```@Service``` ```@Dto``` 클래스가 필요하다. 먼저 ```POST``` 요청을 처리 할 ```@Controller``` 를 작성한다.  

```
package com.jeonka.web;

import com.jeonka.service.posts.PostsService;
import com.jeonka.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // 의존성 생성자 주입
@RestController // json 을 반환하는 controller 로 지정
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts") // POST Method 매핑
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
}
```
해당 클래스는 사용자가 요청한 uri 에 해당하는 서비스 객체를 호출한다.
- ```@RequestBody``` : http Post 방식으로 전송된 body의 데이터를 자바의 ```Object``` 로 변경시킨다.  

이 후 ```@Service``` 를 작성한다.

```
package com.jeonka.service.posts;

import com.jeonka.domain.posts.PostsRepository;
import com.jeonka.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // 의존성 생성자 주입
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}

```
해당 ```@Service``` 클래스는 트랜잭션 처리 관련 클래스이다. **트랜잭션을 구성** 하고, ```@Transactional``` 키워드를 통해 자동 **commit** 혹은 **rollback** 이 가능하다.  

다음은 ```Dto``` 클래스를 작성한다.

```
package com.jeonka.web.dto;

import com.jeonka.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private  String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder().title(title).content(content).author(author).build();
    }

}
```
###### Entity 클래스가 있는데 Dto 클래스를 따로 작성하는 이유는 ?
- ```@Entity``` Class 는 데이터베이스와 맞닿은 클래스로, 해당 클래스를 기준으로 테이블과 스키마가 생성된다.
    - 클래스의 컬럼을 모두 멤버필드로 가지며 그외 멤버필드는 가질 수 없다.
    - ```Domain``` 로직만 가지며, ```Presentation``` 로직은 가지면 안된다.
    - 변경 시 다른 클래스에 영향을 미친다.  

따라서 ```Domain``` 과 ```Presentation``` 을 분리하기 위해 Dto 를 따로 만들어 해당 객체를 View 를 위한 객체로 사용한다.

- ```Dto``` 객체는 주로 데이터 가공 처리를 위해 사용하므로, 자주 변경이 되고 **테이블에 없는 속성** 을 갖거나 몇몇 **속성을 빼고** 사용할 수 있다.
    - ```posts Entity``` 에 ```id```, ```title```, ```contetn```, ```author``` 이 있다면, ```Dto``` 는 이 중 ```id``` 를 뺀 나머지 멤버필드로 구성될 수 있고, ```Entity``` 에 없는 ```price``` 라는 멤버 필드를 가질 수 있다.

###### 영속성 컨텍스트 ?
- 엔티티를 영구 저장하는 환경
- JPA 의 핵심 내용은 ```Entity```가 영속성 컨텍스트에 포함되어 있냐 아니냐 로 갈린다.

###### dirty checking
- 트랜잭션이 끝나는 시점에서 ```Entity``` 의 변경 내용을 데이터베이스에 반영하는 것
    - 즉, 트랜잭션 도중에 쿼리를 날리지 않는다.
    - 트랜잭션이 끝나면 최초 조회 상태를 기준으로 변경 사항이 있는 Entity flush 한다.
    - flush : 영속성 컨텍스트에 있는 Entity 를 디비와 동기화 하는것(rollback 가능)
    - commit : 영속성 컨텍스트에 있는 Entity 를 영구적으로 디비에 반영 ( rollback 불가능 )


---

## JPA Auditing 
> 생성시간/수정시간 을 자동으로 주입해주는 기능
- 기존 Data, Calendar 의 경우 불변객체가 아니기 때문에 멀티 스레드 환경에서 문제가 발생 할 수 있다.

#### 예제 코드
- 해당 클래스는 JPAEntity 의 상위 클래스가 되어 해당 클래스를 상속한 Entitydml 생성, 수정 시간을 자동으로 관리하는 역할을 한다.

```
package com.jeonka.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
```
- ```@MappedSuperclass``` : 