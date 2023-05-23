FROM openjdk:17

EXPOSE 8080

LABEL maintainer = "neuefische@idspispopd.de"

ADD backend/target/QuizBackend.jar QuizBackend.jar

CMD ["sh", "-c", "java -jar /QuizBackend.jar"]
