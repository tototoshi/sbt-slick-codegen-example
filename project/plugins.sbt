addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.16")

// settings for slick-codegen and sbt-flyway

addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "2.0.0")

addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "7.4.0")

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "2.2.224",
  "org.flywaydb" % "flyway-core" % "10.5.0"
)

ThisBuild / libraryDependencySchemes ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always
)
