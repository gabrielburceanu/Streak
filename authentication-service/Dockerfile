FROM openjdk:17
EXPOSE 7777
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} authentication-service.jar
COPY honeycomb-opentelemetry-javaagent.jar honeycomb-opentelemetry-javaagent.jar
ENTRYPOINT ["java", "-jar", "authentication-service.jar"]
#ENTRYPOINT ["java", "-javaagent:honeycomb-opentelemetry-javaagent.jar", "-Dotel.javaagent.debug=false", "-jar", "authentication-service.jar"]
