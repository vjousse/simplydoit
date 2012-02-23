import sbt._
import Keys._

trait Resolvers {
  val typesafe = "typesafe.com" at "http://repo.typesafe.com/typesafe/releases/"
  val typesafeSnapshot = "typesafe.com snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
}

trait Dependencies {
  val akka = "com.typesafe.akka" % "akka-actor" % "2.0-RC1"
  val scalaz = "org.scalaz" %% "scalaz-core" % "6.0.4"
  val specs2 = "org.specs2" %% "specs2" % "1.8.2"
}

object ApplicationBuild extends Build with Resolvers with Dependencies {

  val doit = Project("simplydoit", file("."), settings = Project.defaultSettings).settings(
    libraryDependencies := Seq(akka, scalaz, specs2),
    resolvers := Seq(typesafe),
    scalacOptions := Seq("-deprecation", "-unchecked")
  )
}
