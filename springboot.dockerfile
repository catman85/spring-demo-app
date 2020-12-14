FROM alpine:latest
# EXPOSE 8080

# installing system dependencies
# with alpine's package manager
RUN apk --no-cache add openjdk11
RUN apk add --no-cache maven

# it's better to not run our app with root privileges
# making spring user and group
# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring

ENV APP_DIR /home/spring/app
# VOLUME ${APP_DIR}
WORKDIR ${APP_DIR}
COPY . ${APP_DIR}

ENTRYPOINT ["java","-jar","target/demo-0.0.1-SNAPSHOT.jar"]

# ENTRYPOINT ["mvn","clean","install"]