FROM alpine:latest

# installing system dependencies
# with alpine's package manager
RUN apk --no-cache add openjdk11
# RUN apk add --no-cache maven

#with root priviliges we create a folder
ARG APP_DIR=/home/spring/app
RUN mkdir -p ${APP_DIR}/.logs/

# it's better to not run our app with root privileges
# making spring user and group
RUN addgroup -S spring && adduser -S spring -G spring

# we need to change the ownership of our working directory to the new user
# otherwise logging will fail
RUN chown spring ${APP_DIR}
USER spring:spring
WORKDIR ${APP_DIR}

# ARG is like an environmental variable with limited scope
# which can also be overwritten with build arguments
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]