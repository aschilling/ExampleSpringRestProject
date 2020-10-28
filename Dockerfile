FROM openjdk:8-jdk-alpine
ENV demo.version=0.9
ENV api_keys=alpha1234,beta6,charly4321
ENV Spring.profiles.active=Prod
RUN mkdir app
WORKDIR app/
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]