## 스프링 시큐리티 OAuth 2.0
> 스프링 기반 애플리케이션에서의 보안을 위한 표준
- 인터셉터, 필터 기반의 보안 기능보다는 스프링 시큐리티를 통해 구현하는 것을 권장
#### 장점은 ?
해당 기능을 사용하지 않을 경우 아래 목록을 직접 구현해야 함  
- 로그인 시 보안
- 회원가입 시 이메일 혹은 전화번호 인증
- 비밀번호 찾기
- 비밀번호 변경
- 회원정보 변경
**그러나 OAuth 를 사용하면 이를 구현하지 않고 서비스 개발에 집중할 수 있다.**  

#### OAuth1.5 vs OAuth 2.0 ? 
- 2.0 에서는 springboot 용 라이브러리 출시
- 신규 기능의 경우 2.0 방식에서만 지원  
- 1.5 버전은 url 주소를 모두 명시해야 하지만 2.0 버전은 인증 정보만 입력  
- 아래 enum 타입 제공
```
public enum CommonOAuth2Provider{
    GOOGLE{
        ...
    },
    NAVER{
        ...
    },
    ...
}
```
등... 

#### GOOGLE 인증 서비스 등록
- http://console.cloud.google.com 에서 등록한다.
1. OAuth 동의 화면 구성
2. OAuth 클라이언트 ID 생성
3. 발급받은 클라이언트 ID 복사
4. ```application-oauth.properties``` 파일 생성
5. 해당 파일에 키,비밀번호 범위 설정
```
spring.security.oauth2.client.registration.google.client-id=968318398008-hr60r0h8qs4jnjh6dv52l7qjst198pn8.apps.googleusercontent.com
spring.security.oauth2.client.registration.google.client-secret=wZfWFNGEACpsxIAIVct84w_B
spring.security.oauth2.client.registration.google.scope=profile,email
```
6. .gitignore 에 application-oauth.properties 추가

###### 5번의 scope 를 별도 등록하지 않으면?
- 기본값이 openid, email, profile 이기 때문에 google 외의 서비스(OpenId Provider 가 아닌 서비스 )에서는 OAuth2Service 를 별도로 나눠서 만들어야 한다.
- 따라서 scope 에 openid 를 빼고 등록한다.


#### 회원의 권한?
- 회원 관련 entity 에 대한 각 회원의 권한 관리용 객체
- 아래는 권한을 관리하기 위한 enum 이다.
```
package com.jeonka.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    
    GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER","일반 사용자");

    private final String key;
    private final String title;
}

```
스프링 시큐리티 에서는 권한 코드에 항상 **ROLE_** 이 **앞에** 있어야 한다.

- 이 후 build.gradle 에 의존성을 추가해 준다
    - ```compile('org.springframeworkboot:spring-boot-starter-oauth2-client')```
