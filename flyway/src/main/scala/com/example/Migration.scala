package com.example

import org.flywaydb.core.Flyway

object Main {

  def main(args: Array[String]): Unit = {
    val databaseUrl = sys.env.getOrElse("DB_DEFAULT_URL", "DB_DEFAULT_URL is not set")
    val databaseUser = sys.env.getOrElse("DB_DEFAULT_USER", "DB_DEFAULT_USER is not set")
    val databasePassword = sys.env.getOrElse("DB_DEFAULT_PASSWORD", "DB_DEFAULT_PASSWORD is not set")
    val flyway = Flyway
      .configure()
      .dataSource(databaseUrl, databaseUser, databasePassword)
      .load()
    flyway.migrate()
  }

}
