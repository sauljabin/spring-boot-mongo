## Spring Boot and MongoDB Example

[Postman](https://www.postman.com/) Collection in [docs](docs).

## Dependencies

- [Docker](https://docs.docker.com/engine/install/)
- [Docker Compose](https://docs.docker.com/compose/install/)
- Java 11

## Getting Started

```
make init up
```

## Commands

`make image` creates containers

`make up` runs the application (needs `init`)

`make down` stops the application

`make delete` deletes containers and volumes

`make show` shows the current containers (needs `up`)

`make init` starts the configuration

`make import-db` creates database (needs `db-up`)

`make db-up` runs mongo only

`make clean` cleans compiles

`make run` runs the application (without docker)

`make jar` makes the spring boot artifact

`make test` runs all tests

`make it` runs all integration tests
