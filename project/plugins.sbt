addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.16")

// settings for slick-codegen and sbt-flyway

addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "2.0.0")

addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "7.4.0")

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "2.1.214",
  "org.flywaydb" % "flyway-core" % "9.22.2"
)

ThisBuild / libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
