FROM openjdk:11.0.1
EXPOSE 8080
VOLUME /tmp
ARG JAR_FILE=build/libs/backend.jar
COPY ${JAR_FILE} kona.jar
ENTRYPOINT ["java","-jar","/kona.jar"]