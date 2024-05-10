FROM openjdk:12

WORKDIR /app

COPY . /app

EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "/app/build/libs/Movie-World-Backend-1.0-SNAPSHOT.jar" ]