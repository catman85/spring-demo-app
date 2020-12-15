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

ARG APP_DIR=$HOME/app
# VOLUME ${APP_DIR}
WORKDIR ${APP_DIR}
COPY . ${APP_DIR}

# CMD ["mvn","clean","install"]
RUN mvn clean install
ENTRYPOINT ["java","-jar","target/demo-0.0.1-SNAPSHOT.jar"]