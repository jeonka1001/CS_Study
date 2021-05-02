## build.gradle 내부 코드
- ```buildscript``` : IntelliJ 의 plugin 관리가 아닌 해당 **프로젝트의 의존성** 관리 설정 블록
    - 해당 블록은 최 상단에 위치해야 한다.
```
buildscript{

    // build.gradle 에서 사용하는 전역변수 선언 블록
    ext{ 
        springBootVersion = '2.1.7.RELEASE'
    }
    
    // 사용자 라이브러리 내려받을 원격 저장소 설정 블록
    repositories{ 
        mavenCentral() // 개발자가 라이브러리 업로드 하는 기존 원격 저장소
        jcenter() // mavenCentral 의 라이브러리 업로드 과정의 번거로움을 최소화 함수(?)
    }

    // 프로젝트 개발에 필요한 라이브러리 선언
    dependencies{
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}
```

- buildscript 설정을 적용할 위치 결정
    - 자바와 스프링 부트를 사용하기 위해 필수 선언
```
apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management' // 스프링 부트의 의존성을 관리해주는 플러그인 ( 필수 )
```

- 이 후 ```repositories``` 와 ```dependencies``` 에 의존성 걸정 관련 코드를 작정한다.

```
repositories{
    mavenCentral()
    jecenter()
}

dependencies{
    compile('org.springframework.boot:spring-boot-starter-web')
    testComplie('org.springframework.boot:spring-boot-starter-test')
}

```

- 모든 코드를 작성 하면 ```build.gradle``` 의 전체 코드는 다음과 같다.

```
buildscript{
    ext{
        springBootVersion = '2.1.7.RELEASE'
    }
    repositories{
        mavenCentral()
        jcenter()
    }
    dependencies{
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

group 'com.jeonka'
version '1.0-SNAPSHOT'
sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    compile('org.springframework.boot:spring-boot-starter-web')
    testCompile('org.springframework.boot:spring-boot-starter-test')
}

```

이 후 변경 사항을 반영하면 오른쪽 gradle 탭에서 testComplie 과 mainComplie 에 잘 반영된 것을 확인할 수 있다.

---

## application.properties 정리
- 쿼리 로그 보기
    - spring.jpa.show_sql=true 
- 테이블 엔진 변경
    - spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
- 웹 콘솔 활성화
    - spring.h2.console.enabled=true > 이후 Application 의 main() 실행

---
## 웹 데이터베이스 
웹에서 간단하게 데이터베이스 테스트를 하는 방법으 다음과 같다.
1. 웹 콘솔 활성화
2. ```http://localhost:8080/h2-console``` 로 접속
3. JDBC URL 을 jdbc:h2:mem:testdb 로 변경

---

[목차로](https://github.com/jeonka1001/Study/blob/main/springboot/README.md)