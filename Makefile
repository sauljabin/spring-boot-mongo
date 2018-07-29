clean:
	@./gradlew clean

run: clean
	@./gradlew bootRun

jar:
	@./gradlew bootJar

db-up:
	@docker-compose up -d mongo

build:
	@docker-compose build

up:
	@docker-compose up -d && sleep 5

show:
	@docker-compose ps
	@echo '\nVolumes: ' && docker volume inspect spring_boot_mongodb

down:
	@docker-compose down

delete:
	@docker-compose rm -fsv
	@docker volume rm spring_boot_mongodb || true