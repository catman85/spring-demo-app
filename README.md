##db start
```docker-compose up -d```
##db interact
```docker exec -it postgres psql --host=database --username=unicorn_user --dbname=rainbow_database```

### Resources:

- PostgresDB with Docker: https://medium.com/analytics-vidhya/getting-started-with-postgresql-using-docker-compose-34d6b808c47c 

- Hot Reload in IntelliJ: https://dzone.com/articles/spring-boot-application-live-reload-hot-swap-with

- Exception Handling: https://www.toptal.com/java/spring-boot-rest-api-error-handling

### Bring up postgres only
```docker run -d -p 5432:5432 --env-file=database.env --mount source=spring_boot_app_database-data,target=/var/lib/postgresql/data/ --name=postgres postgres:latest```
```docker start postgres```

### Execution
Using IntelliJ IDEA run the main method
ctrl + f9 to build

> the Spring and Spring boot plugins are available and bundled only in the Ultimate version of IntelliJ IDEA. 
> But you can install the Spring Assistant plugin.