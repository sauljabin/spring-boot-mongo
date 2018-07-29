## Spring Boot and MongoDB Example

## Docker Version

- `docker-compose`: 1.17.1
- `docker-compose.yml`: 3.4
- `docker`: 18.06.0-ce

## Getting Started

```
make init up
```

## Commands

`make images` creates containers

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