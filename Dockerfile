# 1. 기반 이미지로 경량화된 OpenJDK JRE 사용 (예: Alpine 리눅스 기반)
FROM openjdk:21

# 2. 애플리케이션 JAR 파일을 빌드 아티팩트에서 가져오기 위한 ARG (Gradle 빌드 경로 적용)
ARG JAR_FILE=build/libs/*.jar

# 3. 컨테이너 내부에 app.jar 이름으로 JAR 복사
COPY ${JAR_FILE} app.jar

# 4. 컨테이너 시작 시 실행할 명령 (Spring Boot JAR 실행)
ENTRYPOINT ["java", "-jar", "/app.jar"]