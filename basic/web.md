# 면접준비 - WEB
> 나만의 언어로 준비하는 면접준비

## 목록

---

## http vs https
- hypertext transfer protocal (secure) 의 약자
- 기존 http 의 경우 서버 - 브라우저 전송 데이터가 암호화 되지 않음.
- 이를 방지하기위해 https 의 경우 ssl(socket secure layer)인증서를 사용함.

#### http 포로토콜 구조 - request message
- request line
    - 요청 대상을 가리키는 한 줄의 문자열  
    -  http method / request uri / http version  
- request header
- blank line
- body 

#### http method

###### GET vs POST
||GET|POST|
|--|--|--|
|캐싱|가능|불가능|
|요청길이제한|있다|없다|
|보안|취약|안전|
|기록|남는다|남지않는다|
- GET 의 경우 서버에 리소스 조회를 요청할 시 사용하며, 요청 시 파라미터를 url 에 담아 서버로 보낸다
- POST 의 경우 서버에 리소스 추가, 수정을 요청할 시 사용되며, 요청 시 파라미터를 body 에 담아 서버로 보낸다.