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
- @NoArgsConstructor : 
- @Entity : 테이블과 매핑 될 클래스임을 나타낸다.
    - **기본값으로 클래스의 카멜케이싱 이름을 언더바 네이밍으로 테이블 이름을 매칭한다**  
    - ex ) SalesManager.java -> sales_manager table
- @Id : 매핑 될 테이블의 PK
- @GeneratedValue : PK 생성 규칙 ( GenerationType.IDENTITY 를 추가해야 autoincrement 가 된다. )
- @Column : 기본값 외 추가로 변경이 필요한 옵션이 있을 경우 사용
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
