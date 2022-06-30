FROM openjdk:8
EXPOSE 8888
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} streak-web.jar
ENTRYPOINT ["java", "-jar", "streak-web.jar"]