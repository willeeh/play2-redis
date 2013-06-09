import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "LandingPagePlay"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "redis.clients" % "jedis" % "2.1.0",
    "com.google.inject" % "guice" % "3.0",
    javaCore,
    javaJdbc,
    javaEbean
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += "Maven repository" at "http://repo1.maven.org/maven2"
  )

}
