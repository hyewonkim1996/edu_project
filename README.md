![image](https://github.com/hyewonkim1996/edu_project/assets/153244876/1c18d377-43c0-4a8e-a59a-90336753b1f8)

&nbsp;
&nbsp;

<h1 align="center"> 🌱 새싹 청소년 교육 복지 센터 🌱 </h1>

&nbsp;
&nbsp;

## 👩🏻‍💻 개발 인원
> 2인 개발

> 담당 구현 기능 : 로그인/카카오 로그인, 회원 관리 (가입 / 정보 수정 / 탈퇴), 수강후기

> 기여도 : 50%

&nbsp;
&nbsp;

## 📆 프로젝트 기간
> 2024.01.02 ~ 2024.01.24

&nbsp;
&nbsp;

## 📋 프로젝트 목표 
> 개발 목표 : 전자정부 표준프레임워크 숙련도 강화로 공공 프로젝트 실무에 대한 간접 경험 및 새로운 프레임워크에 대한 적응력 키우기 / 실습으로 웹 구조 흐름 이해도 높이기 

> 🔗 [프로젝트 시연 영상](https://www.youtube.com/watch?v=v1fOSp8c0Dk)

> 🔗 [프로젝트 일지](https://hyewonkim1996.tistory.com/category/%F0%9F%93%81%20%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%20%EC%9D%BC%EC%A7%80/%F0%9F%91%A9%E2%80%8D%F0%9F%8F%AB%20eGov%20-%20%EA%B3%B5%EA%B3%B5%20%EA%B5%90%EC%9C%A1%20%EC%84%9C%EB%B9%84%EC%8A%A4?page=2)

> 🔗 [프로젝트 PDF](https://github.com/hyewonkim1996/edu_project/blob/main/%EA%B3%B5%EA%B3%B5%20%EA%B5%90%EC%9C%A1%20%EC%84%9C%EB%B9%84%EC%8A%A4%20-%20%EC%83%88%EC%8B%B9%EC%B2%AD%EC%86%8C%EB%85%84%EA%B5%90%EC%9C%A1%EB%B3%B5%EC%A7%80%EC%84%BC%ED%84%B0.pdf)

&nbsp;
&nbsp;

## 🚧 개발 환경 
![image](https://github.com/hyewonkim1996/edu_project/blob/main/%EA%B3%B5%EA%B3%B5%20%EA%B5%90%EC%9C%A1%20%EC%84%9C%EB%B9%84%EC%8A%A4%20%EC%83%88%EC%8B%B9%EC%B2%AD%EC%86%8C%EB%85%84%EA%B5%90%EC%9C%A1%EB%B3%B5%EC%A7%80%EC%84%BC%ED%84%B0%20-%20%EA%B0%9C%EB%B0%9C%ED%99%98%EA%B2%BD.png?raw=true)

&nbsp;

## ❓ 주요 사용 기술과 이유

|사용 기술|프로젝트 적용 범위|🌟이유🌟|
|------|---|---|
|전자정부 표준프레임워크|프로젝트 개발환경, DB 연동 등 설정, 서비스단, DAO단|Spring 기반 2차 가공 프레임워크로 Spring DI, IOC, POJO 개념 적용 가능 / 공통컴포넌트와 개발 표준화로 공공 서비스 부문 통일성 강화, 유지보수 용이|
|Spring Security|회원가입, 로그인, 로그아웃|비밀번호 암호화 및 검증된 로그인 절차 사용으로 회원정보 보안 강화|
|REST API|카카오 로그인, 회원가입, 댓글|코드 가독성 향상|
|Session|비회원 사용 기능 제한, 로그아웃, 수강후기|잦은 DB 조회보다 서버에 최소한의 회원 정보 등록해 보안 강화 및 불필요한 중복 READ 코드 생략|
|DB - View|수강 후기 작성 시 수강 강좌 카테고리에 회원 수강 강좌 반영|기존 후기+강좌 테이블 조인한 4줄 쿼리->1줄로 줄여 가독성, 편의성 향상|
|open API|카카오 로그인|SNS 간편 로그인으로 사용자의 편의성 향상->더 많은 회원 유치 가능|
|lombok 라이브러리|각 VO @Getter, @Setter, @toString|Getter, Setter 어노테이션으로 대체해 코드 다이어트, 개발 편의성 향상|

&nbsp;

## 🔑 트러블 슈팅

|사용 기술|발생한 문제|해결 과정|밝혀진 원인|해결 방법|
|------|---|---|------|------|
|개발환경 세팅 - 전자정부 프레임워크 DB 연동|DB 커넥션 실패|dataSource bean 설정 부분에 문제가 있을 것으로 추정하여 pom.xml과 context-dataSource.xml을 꼼꼼히 살펴봄|커넥션 라이브러리 아이디(dbcp)와 dataSource 기존 class 설정(dbcp2)의 불일치|class 설정을 아이디와 같게 수정|
|Spring Security|bad credentials 오류 - 알맞은 비밀번호를 입력해도 로그인 실패|비밀번호 암호화 과정에서 문제가 생긴 것으로 추정|전자정부프레임워크에 적용하기 전 스프링에서 연습한 튜플을 그대로 db에서 삽입|Java에서 비밀번호 암호화를 거친 새로운 튜플 삽입|
|REST API|post로 회원가입 구현 시 @RequestBody로 매핑이 안되는 오류 발생|프론트에서 넘겨주는 값 개발자 모드 payload에서 확인-> 문제없음 / VO 필드와 DB 컬럼 일치 -> 문제없음으로 전자정부 프레임워크 REST API 설정에 대해 구글링|전자정부 프레임워크에서 @RequestBody 사용 시 어노테이션 별도 설정 필요|dispatcher.servlet.xml에 messageConverter bean 설정|
|REST API|파라미터 붙이는 get 구현 시 404 오류 발생|프론트에서 요청하는 값과 컨트롤러 value가 같음 -> 문제없음으로 전자정부 프레임워크 REST API 설정에 대해 구글링|전자정부 프레임워크 url에 디폴트로 .do를 붙이는 설정으로 인해 계층적 url 적용 안됨|web.xml에서 url 매핑 설정 수정|
|CRUD|수강후기 작성, 수정 시 줄바꿈한 내용이 조회 시 반영되지 않음|DB에 줄바꿈 내용이 잘못 저장되는 것으로 추정하여 작성, 조회 시 프론트단과 컨트롤러에서 디버깅|HTML의 줄바꿈은 <br>태그, DB에 저장될 때는 개행문자 \r\n으로 저장되기 때문|컨트롤러에서 getter,setter,replace 사용하여 개행문자 태그로 변경|
|파일 업로드|post로 파일 정보와 함께 후기 등록 시 컨트롤러에 매팽이 안되는 오류 발생|해당 태그의 name, VO 필드, DB 컬럼명 일치했으나 매핑이 안 되는 이유는 어딘가 다른 부분이 있는 것이므로 꼼꼼히 살핌|컨트롤러에서 파일 정보는 multipartfile로 받고 VO에서는 String 자료형으로 설정해 자료형 불일치로 매핑 실패|VO 파일 정보 필드 자료형 multipartfile로 수정|
|파일 다운로드 - 원본 파일명 반영|서버에 파일명을 변환해 전환 후 다운로드 시 원본 파일명을 반영했으나 이상한 문자로 나오는 오류 발생|관련 오류 구글링|한글의 경우 컴파일 언어에서 UTF-8로 재인코딩하는 과정 필요|URLEncoder 사용해 UTF-8로 인코딩 후 원본 파일명 반영|

&nbsp;

## 💡 배운 점

> **1) 체계적인 프로젝트 설계의 중요성**

```
😄 : 테이블 설계 시 테이블명, 컬럼명, 제약조건을 디테일하게 의논하고 통일하여
      코드 작성 시 테이블을 수정할 일이 거의 없어 시간을 절약할 수 있었다.

😥 : 요구사항 정의 시 정의서에 작성하지 않고 종이에 러프하게 작성하여
      코드 작성 시 디테일한 기능이 계속 추가되어 구현한 기능들을 정리할 때 혼란스러웠다.
```
&nbsp;

> **2) 새로운 프레임워크에 적응하는 법**

```
😄 : 프레임워크의 특징과 설정 파일의 위치, 내용을 우선적으로 파악하고
      샘플 프로젝트 등을 통해 DAO-서비스-컨트롤러의 형식을 파악했다.

😥 : 파악한 설정 파일의 내용을 처음에 문서로 정리해 두었다면 시간을 더 절약할 수 있었을 것 같다.
```
&nbsp;

> **3) 배우지 않은 기술을 적용하는 법**

```
😄 : 자료를 조사해 기술의 개념을 익히고 다양한 예시를 보며 어떻게 사용되는지 익혔다.
      이때 예시 코드를 무작정 복사하면 오류가 발생할 확률이 높기 때문에
      우선적으로 코드를 이해한 후 내 코드에 알맞게 변형해 적용한다.
```
&nbsp;

> **4) 프로젝트 로그의 중요성**

```
😄 : 오늘의 목표, 달성한 목표, 보류한 일, 오류 로그 등을 기록하여 똑같은 오류가 발생했을 때
      시간낭비하지 않고, 무엇을 해야 하는지 혼란스러워하는 일 없이 프로젝트를 진행해 나갈 수 있었다.

😥 : 팀원과 의논한 내용에 대해서도 디테일하게 기록했다면 커뮤니케이션이
      잘 되고 있는지 파악하기 더 쉬웠을 것 같다.
```

