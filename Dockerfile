FROM openjdk:17
MAINTAINER Henrique Almeida
ARG PORT
ENV PORT=${PORT}
COPY build/libs/*.jar app.jar
ENTRYPOINT [ "java", "-Dserver.port=${PORT}", "-jar", "app.jar" ]
