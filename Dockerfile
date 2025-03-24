# 1. 기반 이미지로 경량화된 OpenJDK JRE 사용 (예: Alpine 리눅스 기반)
#FROM openjdk:21
#
## 2. 애플리케이션 JAR 파일을 빌드 아티팩트에서 가져오기 위한 ARG (Gradle 빌드 경로 적용)
#ARG JAR_FILE=build/libs/*.jar
#
## 3. 컨테이너 내부에 app.jar 이름으로 JAR 복사
#COPY ${JAR_FILE} app.jar
#
## 4. 컨테이너 시작 시 실행할 명령 (Spring Boot JAR 실행)
#ENTRYPOINT ["java", "-jar", "/app.jar"]


# (1) 빌드 스테이지
FROM gradle:8.13-jdk21 AS builder
WORKDIR /app

# 로컬 소스코드를 컨테이너에 복사
COPY . .

# Gradle 빌드 (테스트 스킵 예시: -x test)
RUN gradle clean build -x test

# (2) 런타임 스테이지
FROM openjdk:21-jdk-slim
WORKDIR /app

# builder 스테이지에서 빌드된 jar 파일을 복사
#  예: 빌드 결과물이 build/libs/ 폴더에 *.jar 로 생성된다고 가정
COPY --from=builder /app/build/libs/*.jar app.jar

# 컨테이너 실행 시 노출할 포트 (예: 8091)
EXPOSE 8091

# 컨테이너가 실행될 때 Spring Boot JAR을 구동
ENTRYPOINT ["java", "-jar", "/app/app.jar"]