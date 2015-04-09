# sbt-slick-codegen-example

An example Play application to demonstrate the integration with sbt-slick-codegen and flyway.

## How to run

```
$ export DB_DEFAULT_URL="jdbc:h2:/tmp/example.db"
$ export DB_DEFAULT_USER="sa"
$ export DB_DEFAULT_PASSWORD=""
$ sbt
> flyway/flywayMigrate # flyway configuration is in another subproject to avoid conflict between migration and compilation
> project web
> compile
> run
```
