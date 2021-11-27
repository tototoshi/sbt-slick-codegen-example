addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.9")

addSbtPlugin("org.scalariform" % "sbt-scalariform" % "1.8.3")

// settings for slick-codegen and sbt-flyway

addSbtPlugin("com.github.tototoshi" % "sbt-slick-codegen" % "1.4.0")

 addSbtPlugin("io.github.davidmweber" % "flyway-sbt" % "7.4.0")

libraryDependencies += "com.h2database" % "h2" % "1.4.200"
