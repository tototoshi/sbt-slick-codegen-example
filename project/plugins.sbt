resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.7")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.2")

// settings for slick-codegen and sbt-flyway

addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.4.0")

 addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "6.0.7")

libraryDependencies += "com.h2database" % "h2" % "1.4.199"
