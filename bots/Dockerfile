FROM eclipse-temurin:18
EXPOSE 8080
COPY build/libs/*.jar /app/app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]