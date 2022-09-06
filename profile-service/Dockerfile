FROM openjdk:8
ADD target/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar","/app.jar"]