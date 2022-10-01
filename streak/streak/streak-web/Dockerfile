FROM openjdk:11
EXPOSE 8888
EXPOSE 5005
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} streak-web.jar
#ENTRYPOINT ["java", "-jar", "streak-web.jar"]
ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","streak-web.jar"]
#docker run -p 8888:8888 -p 5005:5005 --name debug-streak-web-container debug-streak-web