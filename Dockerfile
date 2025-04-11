FROM openjdk:21-alpine3.14

# copy the packaged jar file into our docker image
COPY target/poc-service-template-2.1.0-SNAPSHOT.jar /poc-service-template.jar
# set the startup command to execute the jar
CMD ["java", "-jar", "-Dspring.profiles.active=template","/poc-service-template.jar"]
