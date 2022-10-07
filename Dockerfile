FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar my.jar
ENTRYPOINT ["java","-jar","/my.jar"]