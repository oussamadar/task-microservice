FROM openjdk:8
ADD target/spring-boot-crud-example-0.0.1-SNAPSHOT.jar spring-boot-crud-example-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-boot-crud-example-0.0.1-SNAPSHOT.jar"]