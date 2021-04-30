# 스프링 부트
> 스프링 부트 기초 공부
- 참고서적 : [스프링 부트와 AWS로 혼자 구현하는 웹 서비스 - 이동욱 지음 -](https://book.naver.com/search/search.nhn?query=%EC%9D%B4%EB%8F%99%EC%9A%B1+%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8)

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

