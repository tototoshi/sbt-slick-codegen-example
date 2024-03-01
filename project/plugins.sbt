addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")

addSbtPlugin("org.playframework" % "sbt-plugin" % "3.0.2")

// settings for slick-codegen and sbt-flyway
addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "2.2.0-RC2")

addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "7.4.0")

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "2.2.224",
  "org.flywaydb" % "flyway-core" % "8.5.13"
)

ThisBuild / libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
