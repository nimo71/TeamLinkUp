name := "TeamLinkUp"

version := "0.1"

scalaVersion := "2.9.1"

libraryDependencies ++= Seq(  )

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "0.6.3",
  "net.databinder" %% "unfiltered-jetty" % "0.6.3",
  "org.clapper" %% "avsl" % "0.3.6",
//  "net.databinder" %% "dispatch-http" % "0.8.8",
//  "nu.validator.htmlparser" % "htmlparser" % "1.4",
  "org.scalatest" %% "scalatest" % "1.6.1" % "test"
//  "org.specs2" %% "specs2" % "1.11" % "test",
//  "org.mockito" % "mockito-all" % "1.9.0" % "test",
//  "junit" % "junit" % "4.8" % "test"
)

//resolvers ++= Seq(
//  "java m2" at "http://download.java.net/maven/2"
//)