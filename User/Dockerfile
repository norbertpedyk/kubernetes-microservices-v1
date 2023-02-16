FROM openjdk:17
WORKDIR /
COPY target/*.jar user.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/user.jar"]