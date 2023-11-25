import slick.codegen.SourceCodeGenerator
import slick.{model => m}

lazy val databaseUrl = sys.env.getOrElse("DB_DEFAULT_URL", "DB_DEFAULT_URL is not set")
lazy val databaseUser = sys.env.getOrElse("DB_DEFAULT_USER", "DB_DEFAULT_USER is not set")
lazy val databasePassword = sys.env.getOrElse("DB_DEFAULT_PASSWORD", "DB_DEFAULT_PASSWORD is not set")

lazy val flyway = (project in file("flyway"))
  .enablePlugins(FlywayPlugin)
  .settings(
    scalaVersion := "2.13.10",
    flywayUrl := databaseUrl,
    flywayUser := databaseUser,
    flywayPassword := databasePassword,
    flywayLocations := Seq("filesystem:web/conf/db/migration/default")
  )

lazy val web = (project in file("web"))
  .enablePlugins(PlayScala, CodegenPlugin)
  .settings(
    scalaVersion := "2.13.10",
    libraryDependencies ++= Seq(
      guice,
      jdbc,
      "com.typesafe.play" %% "play-slick" % "5.2.0",
      "com.typesafe.slick" %% "slick" % "3.3.3",
      "com.h2database" % "h2" % "2.1.214",
      "org.scalatest" %% "scalatest" % "3.2.13" % Test
    ),
    slickCodegenDatabaseUrl := databaseUrl,
    slickCodegenDatabaseUser := databaseUser,
    slickCodegenDatabasePassword := databasePassword,
    slickCodegenDriver := slick.jdbc.H2Profile,
    slickCodegenJdbcDriver := "org.h2.Driver",
    slickCodegenOutputPackage := "models",
    slickCodegenExcludedTables := Seq("schema_version"),
    slickCodegenCodeGenerator := { (model: m.Model) =>
      new SourceCodeGenerator(model) {
        override def Table = new Table(_) {
          override def Column = new Column(_) {
            override def rawType = model.tpe match {
              case "java.sql.Timestamp" => "java.time.Instant" // kill j.s.Timestamp
              case _ =>
                super.rawType
            }
          }
        }
      }
    },
    slickCodegenOutputToMultipleFiles := true,
    Compile / sourceGenerators += slickCodegen.taskValue,
    slickCodegenOutputDir := (Compile / sourceManaged).value / "a"
  )

lazy val root = (project in file("."))
  .aggregate(flyway, web)
  .settings(
    ThisBuild / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oF")
  )
