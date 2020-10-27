FROM openjdk:8-jdk-alpine
ENV demo.version=0.9
RUN mkdir app
WORKDIR app/
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]