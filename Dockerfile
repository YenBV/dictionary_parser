FROM openjdk:8

COPY dictionary-service.jar /dictionary-service/dictionary-service.jar

WORKDIR dictionary-service

EXPOSE 1111

ENTRYPOINT ["java", "-jar", "dictionary-service.jar"]