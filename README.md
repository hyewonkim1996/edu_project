![image](https://github.com/hyewonkim1996/edu_project/assets/153244876/8eafaf96-1883-4d6b-ae61-8ae712cc5866)

&nbsp;
&nbsp;

<h1 align="center"> 🌱 새싹 청소년 교육 복지 센터 🌱 </h1>

&nbsp;
&nbsp;

## 👩🏻‍💻 개발 인원
> 2인 개발

> 담당 구현 기능 : 로그인/카카오 로그인, 회원가입, 수강후기, 회원정보 수정

> 기여도 : 50%

&nbsp;
&nbsp;

## 📆 프로젝트 기간
> 2024.01.02 ~ 2024.01.24

&nbsp;
&nbsp;

## 📋 프로젝트 개요
> 개발 동기 : 디지털 소외 계층의 디지털 교육 격차 심화  
> 개발 목적 : 디지털 소외 청소년 계층에 공공 교육 서비스(강좌 정보 및 신청) & 커뮤니티 플랫폼 제공

> 🔗 [프로젝트 PDF_Link](https://github.com/hyewonkim1996/edu_project/blob/main/%EA%B3%B5%EA%B3%B5%20%EA%B5%90%EC%9C%A1%20%EC%84%9C%EB%B9%84%EC%8A%A4%20-%20%EC%83%88%EC%8B%B9%EC%B2%AD%EC%86%8C%EB%85%84%EA%B5%90%EC%9C%A1%EB%B3%B5%EC%A7%80%EC%84%BC%ED%84%B0.pdf)

&nbsp;
&nbsp;

## 🚧 개발 환경 

## ❓ 주요 사용 기술과 이유

|사용 기술|프로젝트 적용 범위|이유|
|------|---|---|
|전자정부 표준프레임워크|프로젝트 개발환경, DB 연동 등 설정, 서비스단|공통컴포넌트와 개발 표준화로 공공 서비스 부문 통일성 강화, 유지보수 용이|
|Spring Security|회원가입, 로그인, 로그아웃|비밀번호 암호화 및 검증된 로그인 절차 사용으로 회원정보 보안 강화|
|REST API|카카오 로그인, 회원가입, 댓글|코드 가독성 향상|
|Session|비회원 사용 기능 제한||
|DB - View|수강 후기 작성 시 수강 강좌 카테고리에 회원 수강 강좌 반영|기존 4줄 쿼리->1줄로 줄여 가독성 향상 및 속도 개선|
|lombok 라이브러리|각 VO @Getter, @Setter, @toString|코드 다이어트로 속도 개선|
