## =========================
## (1) 빌드 스테이지
## =========================
#FROM gradle:8.13-jdk21 AS builder
#
## 작업 디렉토리 설정
#WORKDIR /build
#
## 전체 프로젝트 복사 (gradle, src, build.gradle, settings.gradle 등 포함)
#COPY . .
#
## Gradle 빌드 (테스트 제외, 필요한 경우 테스트 포함으로 변경 가능)
#RUN gradle clean build -x test
#
## =========================
## (2) 실행(런타임) 스테이지
## =========================
#FROM openjdk:21-jdk-slim
#
## 작업 디렉토리 설정
#WORKDIR /app
#
## 빌드된 JAR 파일을 복사 (멀티 스테이지 빌드)
#COPY --from=builder /build/build/libs/*.jar app.jar
#
## 컨테이너 실행 시 노출할 포트 (Spring Boot의 내부 포트)
#EXPOSE 8091
#
## 컨테이너 실행 시 명령어
#ENTRYPOINT ["java", "-jar", "/app/app.jar"]



# 1. 기반 이미지로 경량화된 OpenJDK JRE 사용 (예: Alpine 리눅스 기반)
FROM openjdk:21

# 시스템 업데이트 & 로케일 패키지 설치
RUN apt-get update && apt-get install -y locales \
    && locale-gen ko_KR.UTF-8

# 환경변수 설정
ENV LANG ko_KR.UTF-8
ENV LANGUAGE ko_KR:ko
ENV LC_ALL ko_KR.UTF-8

# 2. 애플리케이션 JAR 파일을 빌드 아티팩트에서 가져오기 위한 ARG (Gradle 빌드 경로 적용)
ARG JAR_FILE=build/libs/*.jar

# 3. 컨테이너 내부에 app.jar 이름으로 JAR 복사
COPY ${JAR_FILE} app.jar



EXPOSE 8091



# 4. 컨테이너 시작 시 실행할 명령 (Spring Boot JAR 실행)
ENTRYPOINT ["java", "-jar", "/app.jar"]
