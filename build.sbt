import scalariform.formatter.preferences._
import slick.codegen.SourceCodeGenerator
import slick.{ model => m }

lazy val databaseUrl = sys.env.getOrElse("DB_DEFAULT_URL", "DB_DEFAULT_URL is not set")
lazy val databaseUser = sys.env.getOrElse("DB_DEFAULT_USER", "DB_DEFAULT_USER is not set")
lazy val databasePassword = sys.env.getOrElse("DB_DEFAULT_PASSWORD", "DB_DEFAULT_PASSWORD is not set")

lazy val flyway = (project in file("flyway"))
  .enablePlugins(FlywayPlugin)
  .settings(
  scalaVersion := "2.13.6",
  flywayUrl := databaseUrl,
  flywayUser := databaseUser,
  flywayPassword := databasePassword,
  flywayLocations := Seq("filesystem:web/conf/db/migration/default")
)

lazy val web = (project in file("web"))
  .enablePlugins(PlayScala, CodegenPlugin)
  .settings(
    scalaVersion := "2.13.6",
    libraryDependencies ++= Seq(
      guice,
      jdbc,
      "com.typesafe.play" %% "play-slick" % "5.0.0",
      "com.typesafe.slick" %% "slick" % "3.3.3",
      "joda-time" % "joda-time" % "2.10.10",
      "org.joda" % "joda-convert" % "2.2.1",
      "com.github.tototoshi" %% "slick-joda-mapper" % "2.5.0",
      "com.h2database" % "h2" % "1.4.200",
      "org.scalatest" %% "scalatest" % "3.2.10" % Test
    ),
    scalariformPreferences := scalariformPreferences.value
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(DoubleIndentConstructorArguments, true)
      .setPreference(DanglingCloseParenthesis, Preserve),
    slickCodegenDatabaseUrl := databaseUrl,
    slickCodegenDatabaseUser := databaseUser,
    slickCodegenDatabasePassword := databasePassword,
    slickCodegenDriver := slick.jdbc.H2Profile,
    slickCodegenJdbcDriver := "org.h2.Driver",
    slickCodegenOutputPackage := "models",
    slickCodegenExcludedTables := Seq("schema_version"),
    slickCodegenCodeGenerator := { (model:  m.Model) =>
      new SourceCodeGenerator(model) {
        override def code =
          "import com.github.tototoshi.slick.H2JodaSupport.{getDatetimeResult => _, _}\n" + "import org.joda.time.DateTime\n" + super.code
        override def Table = new Table(_) {
          override def Column = new Column(_) {
            override def rawType = model.tpe match {
              case "java.sql.Timestamp" => "DateTime" // kill j.s.Timestamp
              case _ =>
                super.rawType
            }
          }
        }
      }
    },
    Compile / sourceGenerators += slickCodegen.taskValue,
    slickCodegenOutputDir := (Compile / sourceManaged).value / "a"
)
