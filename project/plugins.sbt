resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")

// settings for slick-codegen and sbt-flyway

addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.2.0")

resolvers += "Flyway" at "https://flywaydb.org/repo"

addSbtPlugin("org.flywaydb" % "flyway-sbt" % "4.0")

libraryDependencies += "com.h2database" % "h2" % "1.4.186"
