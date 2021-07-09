# 개발 관련 공부  
- [basic](https://github.com/jeonka1001/Study/tree/main/basic) : 개발 관련 기본 지식 정리
- [springboot](https://github.com/jeonka1001/Study/tree/main/springboot) : springboot-jpa 공부 정리
- [go_study](https://github.com/jeonka1001/Study/tree/main/go_study) : GO 언어 정리
- [REST-API](https://github.com/jeonka1001/Study/tree/main/REST_API) : RESTful API 관련 공부 ( CORS 등)


#### git bash 응답 없을 때- windows
- 현상  
```commit``` 까지는 정상이나, ```push``` 할 시 커서가 깜빡이면서 응답이 없음.  
- 해결책  
해당 ***폴더로 직접*** 들어간뒤, 경로창에 ***cmd*** 를 입력하면 해당 경로의 ***cmd 창*** 이 뜬다. 이후 ```git push``` 를 하면  

> 1. 인터넷 승인 어쩌고 ~~~  
> 2. 개인 승인 토큰 ~~~~  

1번 선택 후 ***Git*** 아이디 로그인을 한 후 ( 이미 했다면 생략 ) 권한을 주면 해결  
두 번째 ```push``` 부터는 ```bash``` 로 정상 작동.
