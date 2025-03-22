#  VitaMatch

##  프로젝트 개요

**VitaMatch**는 Spring Boot 기반의 **개인 맞춤 영양제 추천 및 영양제 정보 제공 웹 애플리케이션**입니다. 사용자의 성별, 연령대, 건강 상태 등을 고려하여 **개인 맞춤형 영양제 성분 추천**과 **효과적인 영양제 조합 제시**를 목표로 합니다.

##  기획 의도 및 목표

-   성별, 연령대, 건강 상태 등을 바탕으로 **개인 맞춤형 영양제 추천**
-   **영양제 성분 조합** 및 **상호작용 분석** 기능 제공
-   **영양제 추천** 및 **건강 뉴스 제공** 기능

##  프로젝트 기간

-   **2024.11.13 ~ 2024.12.03**

##  개발 인원

-   **9명**

##  개발 환경

-   **운영체제**: Windows 10/11, Linux(Ubuntu)
-   **서버**: Apache Tomcat, AWS EC2
-   **데이터베이스**: MySQL 8
-   **개발 툴**: STS 4, Visual Studio Code, SQL Developer
-   **협업 툴**: GitHub, Notion, Discord, ERD Cloud, Draw.io, Figma, Google Drive, MiriCanvas

##  사용 기술

-   **프로그래밍 언어**: Java 21, HTML5, CSS, JavaScript, SQL
-   **프레임워크 및 라이브러리**:
    -   Spring Boot, Gradle, Spring Web, Spring Boot Security, Spring MVC, Spring Boot Devtools, Lombok, HikariCP, JPA, Query DSL, Thymeleaf, Jackson
    -   jQuery, Ajax, Bootstrap, RESTful API, HttpURLConnection
    -   Kakao Maps API, Naver Search API, OpenAI API

##  주요 기능

 **영양제 성분 조합 추천**

-   궁합이 맞는 영양제와 **함께 먹으면 안 되는 영양제** 추천
-   성분 상호작용 분석 제공

 **개인 맞춤 영양제 추천**

-   **건강 설문**을 바탕으로 성별, 연령대, 건강 고민 등을 고려한 영양제 추천

 **카테고리별 영양제 검색**

-   **영양 성분 카테고리**: 비타민, 미네랄 등
-   **건강 고민 카테고리**: 면역력 강화, 혈압 조절 등

 **건강 뉴스 제공**

-   **Naver Search API**를 활용하여 최신 건강 및 영양제 관련 뉴스 제공

 **주변 약국 검색**

-   **Kakao Map API**를 활용하여 **현재 위치 기준으로 가까운 약국 검색**

 **영양제 리뷰 게시판**

-   사용자가 영양제에 대한 **리뷰 작성 및 확인** 가능

 **AI 챗봇 상담**

-   **OpenAI API**를 활용한 **실시간 영양제 추천 및 건강 상담**


 **다국어 지원**

-   messages.properties를 사용하여 영어 등 **다국어 지원**
