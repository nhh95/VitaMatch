# =========================
# (1) 빌드 스테이지
# =========================
FROM gradle:8.13-jdk21 AS builder

# 작업 디렉토리 설정
WORKDIR /build

# 전체 프로젝트 복사 (gradle, src, build.gradle, settings.gradle 등 포함)
COPY . .

# Gradle 빌드 (테스트 제외, 필요한 경우 테스트 포함으로 변경 가능)
RUN gradle clean build -x test

# =========================
# (2) 실행(런타임) 스테이지
# =========================
FROM openjdk:21-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 복사 (멀티 스테이지 빌드)
COPY --from=builder /build/build/libs/*.jar app.jar

# 컨테이너 실행 시 노출할 포트 (Spring Boot의 내부 포트)
EXPOSE 8091

# 컨테이너 실행 시 명령어
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
