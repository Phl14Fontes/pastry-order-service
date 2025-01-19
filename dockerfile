FROM amazoncorretto:21 as build

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle
COPY src ./src

RUN chmod +x gradlew

RUN ./gradlew clean build -x test

FROM amazoncorretto:21 as order-service

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 7000

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
