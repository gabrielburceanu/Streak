FROM openjdk:17
EXPOSE 9999
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} streak-client.jar
COPY honeycomb-opentelemetry-javaagent.jar honeycomb-opentelemetry-javaagent.jar
ENTRYPOINT ["java", "-javaagent:honeycomb-opentelemetry-javaagent.jar", "-Dotel.javaagent.debug=false", "-jar", "streak-client.jar"]
#ENTRYPOINT ["java", "-jar", "-javaagent:honeycomb-opentelemetry-javaagent.jar -Dotel.javaagent.debug=true", "streak-client.jar"]
#ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","streak-web.jar"]
#docker run -p 8888:8888 -p 5005:5005 --name debug-streak-web-container debug-streak-web