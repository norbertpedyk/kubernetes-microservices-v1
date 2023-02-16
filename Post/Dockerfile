FROM openjdk:17
WORKDIR /
COPY target/*.jar post.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/post.jar"]

