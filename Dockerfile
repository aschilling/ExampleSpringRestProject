FROM openjdk:8-jdk-alpine
ENV demo.version=0.9
RUN apk add curl
WORKDIR target/
COPY *.jar app.jar
EXPOSE 8080
CMD ["java","-jar","/app.jar"]
