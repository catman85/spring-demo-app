FROM alpine:latest
# EXPOSE 8080

# installing system dependencies
# with alpine's package manager
RUN apk --no-cache add openjdk11
# RUN apk add --no-cache maven

# it's better to not run our app with root privileges
# making spring user and group
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# ARG is like an environmental variable with limited scope
# which can also be overwritten with build arguments
ARG APP_DIR=/home/spring/app
WORKDIR ${APP_DIR}
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} application.jar
RUN chown spring application.jar && mkdir .logs/
ENTRYPOINT ["java", "-jar", "application.jar"]