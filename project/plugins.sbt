resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Flyway" at "http://flywaydb.org/repo"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.4.2")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")

// settings for slick-codegen and sbt-flyway

addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.0.0")

addSbtPlugin("org.flywaydb" % "flyway-sbt" % "3.2.1")

libraryDependencies += "com.h2database" % "h2" % "1.4.186"
