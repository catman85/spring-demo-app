##db start
```docker-compose up -d```
##db interact
```docker exec -it [container_name] psql --host=database --username=unicorn_user --dbname=rainbow_database```

### Resources:

- PostgresDB with Docker: https://medium.com/analytics-vidhya/getting-started-with-postgresql-using-docker-compose-34d6b808c47c 

- Hot Reload in IntelliJ: https://dzone.com/articles/spring-boot-application-live-reload-hot-swap-with

### Execution
Using IntelliJ IDEA run the main method
ctrl + f9 to build

> the Spring and Spring boot plugins are available and bundled only in the Ultimate version of IntelliJ IDEA. 
> But you can install the Spring Assistan plugin.