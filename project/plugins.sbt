addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.16")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.3")

// settings for slick-codegen and sbt-flyway

addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.4.0")

addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "7.4.0")

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "2.1.214",
  "org.flywaydb" % "flyway-core" % "9.1.6"
)
