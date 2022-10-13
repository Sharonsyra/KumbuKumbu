name := "KumbuKumbu"

version := "0.1"
scalaVersion := "2.13.9"

val jackson =
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.4"

lazy val `kumbukumbu-common` = (project in file("kumbukumbu-common"))
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.3"
  )

lazy val `kumbukumbu-api` = (project in file("kumbukumbu-api"))
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      lagomScaladslApi,
      jackson
    )
  )
  .dependsOn(`kumbukumbu-common`)

lazy val `kumbukumbu-impl` = (project in file("kumbukumbu-impl"))
  .enablePlugins(LagomScala)
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "com.softwaremill.macwire" %% "macros" % "2.5.8" % "provided",
      jackson,
      lagomScaladslTestKit,
      "org.scalatest" %% "scalatest" % "3.2.14" % Test
    )
  )
  .dependsOn(`kumbukumbu-api`, `kumbukumbu-common`)
