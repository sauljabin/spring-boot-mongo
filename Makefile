clean:
	@./gradlew clean

run: db-up clean
	@./gradlew bootRun

jar:
	@./gradlew bootJar

db-up:
	@docker-compose up -d mongo && sleep 5

build:
	@docker-compose build

up:
	@docker-compose up -d && sleep 5

show:
	@docker-compose ps
	@echo '\nVolumes: ' && docker volume inspect spring_boot_mongodb  || true

down:
	@docker-compose stop

delete:
	@docker-compose rm -fsv
	@docker volume rm spring_boot_mongodb || true

import-db:
	@echo 'use bookstore\ndb.createUser({user:"springboot", pwd:"springboot", roles:["readWrite"]})' | docker exec -i $(shell docker-compose ps -q mongo) mongo admin -u root -p default

init: delete db-up import-db down build