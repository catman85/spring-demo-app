## Start application with docker
Install dependencies and generate .jar
- ```mvn clean install```

Start postgres and the spring boot service
- ```docker-compose up -d```

### Create and start the postgres container
```docker run -d -p 5432:5432 --env-file=database.env --mount source=spring_boot_app_database-data,target=/var/lib/postgresql/data/ --name=postgres postgres:latest```

```docker start postgres```

### Interact with the postgres container
```docker exec -it postgres psql --host=database --username=unicorn_user --dbname=rainbow_database```

### To create the schema
The first time you run the spring boot app
use
```spring.jpa.hibernate.ddl-auto = create```
in ***application.properties***

### Execution
Using IntelliJ IDEA run the main method
ctrl + f9 to build, and trigger the hot-reload
make sure the postgres container is app and running.

> the Spring and Spring boot plugins are available and bundled only in the Ultimate version of IntelliJ IDEA. 
> But you can install the Spring Assistant plugin.

### Resources:

- PostgresDB with Docker: https://medium.com/analytics-vidhya/getting-started-with-postgresql-using-docker-compose-34d6b808c47c

- Running spring boot with docker: https://www.baeldung.com/spring-boot-postgresql-docker

- Hot Reload in IntelliJ: https://dzone.com/articles/spring-boot-application-live-reload-hot-swap-with

- Exception Handling: https://www.toptal.com/java/spring-boot-rest-api-error-handling
