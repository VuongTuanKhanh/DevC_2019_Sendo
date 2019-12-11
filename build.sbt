
name := """kanta-play"""

version := "1.0-SNAPSHOT"

//lazy val root = (project in file(".")).enablePlugins(PlayJava)
lazy val GatlingTest = config("gatling") extend Test
lazy val root = (project in file(".")).enablePlugins(PlayJava, GatlingPlugin, PlayEbean).configs(GatlingTest)
  .settings(inConfig(GatlingTest)(Defaults.testSettings): _*)
  .settings(
    scalaSource in GatlingTest := baseDirectory.value / "/gatling/simulation"
  )

inThisBuild(
  List(
    scalaVersion := "2.12.2",
    dependencyOverrides := Set(
      "org.codehaus.plexus" % "plexus-utils" % "3.0.18",
      "com.google.code.findbugs" % "jsr305" % "3.0.1",
      "com.google.guava" % "guava" % "22.0",
      "com.typesafe.akka" %% "akka-stream" % "2.5.6",
      "com.typesafe.akka" %% "akka-actor" % "2.5.6"
    )
  )
)

libraryDependencies += guice
libraryDependencies += javaJpa
libraryDependencies += javaJdbc
libraryDependencies += ehcache
libraryDependencies += cacheApi
libraryDependencies += javaWs
libraryDependencies += ws

// Database
libraryDependencies += "com.h2database" % "h2" % "1.4.194"
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.0" % Test
libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.3.0" % Test
libraryDependencies += "org.hibernate" % "hibernate-core" % "4.3.6.Final"
libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final"
libraryDependencies += "io.dropwizard.metrics" % "metrics-core" % "3.2.1"
libraryDependencies += "com.palominolabs.http" % "url-builder" % "1.1.0"
libraryDependencies += "net.jodah" % "failsafe" % "1.0.3"
libraryDependencies += "com.auth0" % "java-jwt" % "3.3.0"
libraryDependencies += "org.mindrot" % "jbcrypt" % "0.3m"
libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.1"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "6.0.1"
libraryDependencies += "com.sendgrid" % "java-http-client" % "4.2.0"
libraryDependencies += "com.sendgrid" % "sendgrid-java" % "4.1.2"
libraryDependencies += "org.json" % "json" % "20171018"
libraryDependencies += "be.objectify" %% "deadbolt-java" % "2.6.1"
libraryDependencies += "com.google.code.gson" % "gson" % "2.2.4"
libraryDependencies += "org.jetbrains" % "annotations" % "15.0"
libraryDependencies += "commons-io" % "commons-io" % "2.4"
libraryDependencies += "com.google.apis" % "google-api-services-analyticsreporting" % "v4-rev125-1.24.1"
libraryDependencies += "com.google.apis" % "google-api-services-oauth2" % "v1-rev155-1.25.0"
libraryDependencies += "com.google.api-client" % "google-api-client-gson" % "1.19.1"

// Make verbose tests 
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

PlayKeys.externalizeResources := false

mainClass in assembly := Some("play.core.server.ProdServerStart")
fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)

assemblyMergeStrategy in assembly := {
  case manifest if manifest.contains("MANIFEST.MF") =>
    // We don't need manifest files since sbt-assembly will create
    // one with the given settings
    MergeStrategy.discard
  case referenceOverrides if referenceOverrides.contains("reference-overrides.conf") =>
    // Keep the content for all reference-overrides.conf files
    MergeStrategy.concat
  case x =>
    // For all the other files, use the default sbt-assembly merge strategy
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
