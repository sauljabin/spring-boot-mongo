clean:
	@./gradlew clean

run: clean
	@./gradlew bootRun

jar:
	@./gradlew bootJar