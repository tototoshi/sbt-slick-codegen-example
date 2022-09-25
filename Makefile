DB_DEFAULT_URL := "jdbc:h2:/tmp/example.db"
DB_DEFAULT_USER := "sa"
DB_DEFAULT_PASSWORD := ""
SBT := DB_DEFAULT_URL=$(DB_DEFAULT_URL) DB_DEFAULT_USER=$(DB_DEFAULT_USER) DB_DEFAULT_PASSWORD=$(DB_DEFAULT_PASSWORD) sbt

.PHONY: migrate run test

migrate:
	$(SBT) flyway/run # flyway configuration is in another subproject to avoid conflict between migration and compilation

run: migrate
	JAVA_OPTS="--add-opens=java.base/java.lang=ALL-UNNAMED" $(SBT) web/run

test: migrate
	$(SBT) -v test
