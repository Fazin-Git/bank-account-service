FROM openjdk:8
EXPOSE 8080
ADD target/bank-account-service-docker.jar bank-account-service-docker.jar
ENTRYPOINT ["java","-jar","/bank-account-service-docker.jar"]