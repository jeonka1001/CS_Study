## 무중단 배포
> AWS 를 이용해서 무중단 배포를 한다.

- 클라우드의 형태
    - IaaS : 기전 믈리 장비를 미들웨어와 함께 묶어둔 추상화 서비스
        - AWS. EC2, S3 등..
    - Paas : IaaS 에서 한번 더 추상화 한 서비스 ( 많은 기능이 자동화 됨 )
        - AWS 의 Beanstalk, Heroku 등..
    - Saas : 소프트웨어 서비스
        - 구글드라이브, 드롭박스, 와탭

## 배포 절차 - 서버 생성
1. AWS 무료 회원 가입 ( Master Visa 카드가 필요하다.)
2. ECS ( AWS 에서 제공하는 성능,용량 등을 유동적으로 사용 가능한 서버 ) 인스턴스 생성
    - 검색창에 EC2 검색 -> 인스턴스 시작 -> Amazon Linux 64bit 선택 -> t2 micro 체크
3. 태그 추가 ( 해당 인스턴스를 표현하는 이름 )
4. 보안그룹 생성
    - SSH TCP 22 내IP ( 지정 아이피를 통해 보안 향상 ), 공용에서 접속 가능하기 위해 8080 ? 
5. 키페어 생성 
6. 고정 IP 할당 ( EIP )
    - 인스턴스 중지 후 다시 시작시 IP가 바뀌는 현상 방지
    - 위 경우 매번 IP를 확인해야 하기 때문에 번거롭다. 따라서 해당 IP를 고정해야 한다.
    - 네트워크 보안 - 탄력적 IP - 새 주소 할당 - 할당
7. EC2 주소와 EIP 연결
8. 인스턴스 목록에서 연결 여부 확인 ( 사용하지 않을 시 EIP 삭제 )

## 접속
> 위 에서 생성한 서버로 접속
> 해당 로컬 서버에서 EC2 에 접속하여 여러 설정을 할 수 있다.  

EC2 접속을 위해 아래와 같은 절차를 진행한다.  
- 키 위치 변경
    - ```cp [기존 키 위치] ~/.ssh/``` 
    - ex ) cp ~/Documents/pem/freelec-springboot2-webservice.pem ~/.ssh/
- ```~/.ssh``` 폴더 하위에 ```config``` 파일 생성
    - vi ~/.ssh/config
    - 해당 config 파일 내용은 아래와 같다

    ```
    Host [실행 시 사용 할 호스트 이름] 
        HostName [EIP]
        User ec2-user
        IdentityFile [파일의 위치]

    Host freelec-springboot2-webservice
        HostName 3.37.32.130
        User ec2-user
        IdentityFile ~/.ssh/freelec-springboot2-webservice.pem
    ```
- 키 권한 변경
    - chmod 600 ~/.ssh/freelec-springboot2-webservice.pem
- config 권한 변경
    - chmod 700 ~/.ssh/config
- 접속
    - ssh [Hoat] 
    - ex) ssh freelec-springboot2-webservice

#### Java-Spring 기반 서비스 AWS 사용 시 주의사항
- 프로젝트에 맞는 Java 버전 설치
    - 해당 프로젝트는 Java 8 이며, 기본 AWS 설치 버전은 7 버전이다.
- 타임존 변경
    - 기본 EC2 시간은 미국이므로 한국 시간대 설정을 해줘야 함
- 호스트네임 변경
    - 현재 접속한 서버의 별명 등록 ( 실무에서는 여러 서버를 구동하므로 별명을 등록해줘야 함 )

1. Java 설치
    1. 위 에서 생성한 서버로 접속
    2. ```sudo yum install -y java-1.8.0-openjdk-devel.x86_64``` 를 이용해서 Java8 버전 설치
    3. ```sudo /usr/sbin/alternatives --config java ``` 를 이용해서 java 버전 변경
    4. ```java -version``` 을 이용해 자바 버전 확인
2. 타임존 변경
    1. 서버 접속
    2. 기존 서버시간 삭제
        - ```sudo rm /etc/loacltime```
    3. 아시아 서버 시간으로 변경
        - ```sudo ln -s /usr/share/zoneinfo/Asia/Seoul /etc/localtime```
    4. 변경 확인
3. HostName 변경
> Amazon Linux 2 와 Amazon Linux AMI 의 변경 방법이 약간 다르다.  

- Amazon Linux 2
    - ```hostnamectl``` 명령어를 이용해서 수정한다.
    - sudo hostnamectl set-hostname [원하는 host name]
- Amazon Linux AMI
    - ```sysconfig/network``` 파일 수정을 통해 HOSTNAME 을 변경한다

위 두가지 중 해당하는 것에 대해 수정을 했다면 ```/etc/hosts``` 에 ```HOSTNAME``` 을 등록한다.  
이 후 ```curl [등록한호스트이름]``` 을 통해 호스트 이름 등록을 확인한다.  
- (6) Could not resolve host ~~~ : 등록 실패
- (7) Failed to connect to ~~ : 등록 성공 ( 호스트 등록은 잘 됐으나 80 포트로 실행된 서비스가 없기 때문에 발생 )



## AWS에 데이터베이스 생성
> 데이터베이스를 직접 구축하는 것이 아닌, 관리형 서비스를 이용한 데이터 베이스

#### RDS 인스턴스 생성
1. aws - rds - 데이터 베이스 생성
2. 생성 과정 시 아래 설정 선택
> 표준생성, MariaDB, MariaDB 10.2.21, 프리티어
> 할당 스토리지 : 20
> 인스턴스 식별자 및 아이디 비밀번호
> 연결 - 퍼블릭 엑세스 기능 예, 서브넷 그룹 : 인스턴스 서버 생성 시 설정한 인바운드 그룹
> 추가 데이터베이스 테이블 설정 ( 데이터 베이스 옵션 - 이름, 포트, 파라미터그룹 )
3. 파라미터 그룹 생성
> rds 버전과 파라미터 그룹 패밀리를 맞춘다.
> time_zone, character%, collation%, max_connections 150 설정
4. 3 에서 생성한 파라미터 그룹을 rds 파라미터 그룹으로 설정
5. VPC 보안그룹 설정 ( 본인 아이피, 보안그룹 ID 추가 )

#### 접속
> 인텔리제이에서 접속하기

1. Database 플러그인 설치
2. Name, Host(RDS - 연결&보안 - 엔드포인트 ), User, Password 입력
3. 테스트 후 연결

#### 로컬에서 테스트
> 터미널 창에서 데이터 베이스에 접속

1. ssh 명령어로 서버 접속
2. ```sudo yum install mysql``` 을 이용하여 mysql 설치
3. mysql -u [유저이름] -h [호스트] -p [비밀번호] 를 통해 접속, 이 때 host의 경우 인텔리제이와 동일

## 배포
1. 서버에 git 설치 및 clone
2. .gradelw test 를 이용하여 해당 프로젝트 테스트
3. 배포 스크립트 만들기 ( 아래 과정을 포괄 )
    >git clone 또는 git pull 을 통해 새 버전의 프로젝트를 받는다.  
    >Gradle 이나 Maven 을 통해 프로젝트 테스트와 빌드  
    >EC2 서버에서 해당 프로젝트 실행 및 재실행  
4. 배포 스크립트 권한 추가 후 실행
5. nohup.out 파일을 열어 로그 확인

#### 권한 추가
- application-oauth.properties 파일 생성
7. nohup 확인

#### RDS 접근
> RDS 접근을 위해서는 아래 작업을 진행해야한다.
1. 테이블 생성
    - 기본 테이블 생성 (프로젝트 실행 시 로그에 나오는 쿼리문 복붙 가능), session table 생성 ( command + shift + o -> schema-mysql.sql )
2. 프로젝트 설정
    - mariadb 드라이버를 build.gradle 에 등록
    - 서버에서 구동될 환경 추가 ( application-real.properties 를 만든다. profile=real 이 추가됨)
3. EC2 설정
    - 서버에 application-real-db.properties 를 만든다 ( 디비 접근 계정 및 host )
    - 실행 ( curl localhost:8080 ) 후 html 코드가 나오면 성공
4. 브라우저에서 실행
    - google, naver에 public ip dns 와 pid:8080/login/oauth2/code/[domain] 을 입력한다.

## CI CD 자동화
> 서비스 배포 환경 구축 ( CI Continuous Integration 지속적인 통합 , CD Continuous Deployment 지속적인 배포)  

- CI 규칙
    - 모든 소스 코드가 살아 있고(현재 실행되고) 누구든 현재 소스에 접근할 수 있는 단일 지점을 유지
    - 빌드 프로세스를 자동화 해서 누구든 소스로부터 시스템을 빌드하는 단일 명령어 사용
    - **테스팅을 자동화** 해서 단일 명령어로 언제든지 시스템에 대한 건전한 테스트 수트를 실행
    - 누구나 현재 실행 파일을 얻으면 지금까지 가징 **완전한 실행 파일** 을 얻었다는 확신을 하게 할 것  

위 의 사항을 지키는 CI 구축을 위해 **github** 에서 무료로 제공하는 **TravisCI** 를 사용할 것이다.  

#### Travis CI 연동
1. https://travis-ci.org 에서 깃허브 계정으로 로그인
2. 계정 Setting - 동기화
3. 프로젝트 .travis.yml 파일 생성 ( 프로젝트의 최상단이 아닌 연동된 Repository 최상단에 둬야 한다. )
    - branches, cahce, script, notifications, before_script 설정
4. commit - push 후 travis 에서 해당 레포지토리 history 확인

#### Travis - AWS 연동
1. AWS에 접근 가능한 권한을 가진 Key 생성
    - AWS console - IAM - 접근 가능한 사용자 추가
    - 권한 : 기존 정책 직접 연결 ( AmazonS3FullAccess, AWSCodeDeployFullAccess )
    - 태그 : Name = freelec-travis-deploy
2. 생성한 권한의 AWS_ACCESS_KEY, AWS_SECRET_KEY 를 Travis의 Repository 의 setting 에서 등록한다.
    - AWS에서 Key 생성 시 secretkey 는 생성 최초 1회만 볼 수 있기 때문에 따로 저장해야한다.
3. S3(Simple Storage Service) 버킷 생성
    - 일종의 파일 서버
    - EC2 에 배포할 Zip 파일이 이 곳에 모인다.
    - 엑세스 차잔 - 모두 차단
4. .travis.yml 에 배포 파일 전달을 위한 코드 작성




