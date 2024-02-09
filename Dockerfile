FROM openjdk:17-alpine
RUN mkdir -p /app
COPY --from=build /app/target/ProTasker-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 9090
CMD ["java", "-jar", "app.jar"]