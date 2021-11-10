FROM openjdk:11
ARG APP=app.jar1
WORKDIR /app
COPY ./build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

