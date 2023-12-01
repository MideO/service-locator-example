ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "service-locator-example",
    libraryDependencies ++= Seq("org.reflections" % "reflections" % "0.10.2",
      "org.scalatest" %% "scalatest" % "3.2.17" % Test
    )

)
