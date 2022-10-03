name := "KumbuKumbu"

version := "0.1"
scalaVersion := "2.13.9"

lazy val `kumbukumbu-common` = (project in file("kumbukumbu-common"))
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.3"
  )

lazy val `kumbukumbu-api` = (project in file("kumbukumbu-api"))
  .settings(
    version := "1.0-SNAPSHOT",
    libraryDependencies += lagomScaladslApi
  ).dependsOn(`kumbukumbu-common`)

lazy val `kumbukumbu-impl` = (project in file("kumbukumbu-impl"))
  .enablePlugins(LagomScala)
  .settings(
    version := "1.0-SNAPSHOT"
  )
  .dependsOn(`kumbukumbu-api`, `kumbukumbu-common`)
