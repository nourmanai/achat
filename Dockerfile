FROM openjdk:8-jdk-alpine
ADD target/tpAchatProject-0.0.1-SNAPSHOT.jar tpAchatProject.jar
ENTRYPOINT ["java","-jar","/tpAchatProject.jar"]
EXPOSE 8089