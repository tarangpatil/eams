FROM openjdk:21

WORKDIR /app

COPY target/auth-service-0.0.1-SNAPSHOT.jar /app/jserver.jar

ENV PORT=8000

EXPOSE 8000

CMD ["java" , "-jar" , "/app/jserver.jar"]