FROM openjdk:8
ARG JAR_FILE
WORKDIR /usr/local/web
COPY ${JAR_FILE} app.jar
CMD ["java", "-Xmx1G", "-jar", "app.jar"]
