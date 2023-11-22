FROM openjdk:11
VOLUME /tmp
EXPOSE 9080
ARG JAR_FILE=target/knowledge-sharing.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

