DB_DEFAULT_URL := "jdbc:h2:/tmp/example.db"
DB_DEFAULT_USER := "sa"
DB_DEFAULT_PASSWORD := ""
SBT := DB_DEFAULT_URL=$(DB_DEFAULT_URL) DB_DEFAULT_USER=$(DB_DEFAULT_USER) DB_DEFAULT_PASSWORD=$(DB_DEFAULT_PASSWORD) sbt

.PHONY: run
run:
	$(SBT) flyway/flywayMigrate # flyway configuration is in another subproject to avoid conflict between migration and compilation
	$(SBT) web/run
