name := Common.name

version := Common.version

organization := Common.organization

scalaVersion := "2.10.4"

scalacOptions += "-target:jvm-1.6"

scalacOptions ++= Seq("-feature", "-unchecked", "-deprecation")

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

lazy val core = project in file(".")

lazy val models = project.in(file("models"))
  .settings(
    publish := {},
    publishLocal := {},
    publishM2 := {}
  )

addArtifact(Artifact(Common.name, Common.classifier), modelsTask in models)

unmanagedJars in Runtime += (modelsTask in models).value

//
// publishing settings
//

publishArtifact in (Compile, packageSrc) := true

publishArtifact in (Compile, packageDoc) := false

// publish to a maven repo
publishMavenStyle := true

// the standard maven repository
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

// let’s remove any repositories for optional dependencies in our artifact
pomIncludeRepository := { _ => false }

// mandatory stuff to add to the pom for publishing
pomExtra := (
  <url>https://github.com/sistanlp/processors</url>
  <licenses>
    <license>
      <name>Apache License, Version 2.0</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
  <url>https://github.com/sistanlp/processors</url>
  <connection>https://github.com/sistanlp/processors</connection>
  </scm>
  <developers>
    <developer>
    <id>mihai.surdeanu</id>
    <name>Mihai Surdeanu</name>
    <email>mihai@surdeanu.info</email>
  </developer>
  </developers>)

//
// end publishing settings
//

// we need this to find the snapshot releases for BANNER
resolvers += 
  "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.10.4",
  "org.scalatest" %% "scalatest" % "2.0" % "test",
  "junit" % "junit" % "4.10" % "test",
  "com.novocode" % "junit-interface" % "0.11" % "test",
  "xom" % "xom" % "1.2.5",
  "joda-time" % "joda-time" % "2.1",
  "de.jollyday" % "jollyday" % "0.4.7",
  "com.googlecode.efficient-java-matrix-library" % "ejml" % "0.19",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.3.1",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.3.1" classifier "models",
  "ch.qos.logback" % "logback-classic" % "1.0.10",
  "org.slf4j" % "slf4j-api" % "1.7.3",
  "nz.ac.waikato.cms.weka" % "weka-dev" % "3.7.10",
  "net.sf.jopt-simple" % "jopt-simple" % "4.5",
  "de.bwaldvogel" % "liblinear" % "1.94",
  "log4j" % "log4j" % "1.2.17",
  "tw.edu.ntu.csie" % "libsvm" % "3.17",
  "edu.arizona.sista" %% "banner" % "1.0-SNAPSHOT",
  "edu.arizona.sista" %% "banner" % "1.0-SNAPSHOT" classifier "dragontool",
  "edu.arizona.sista" %% "banner" % "1.0-SNAPSHOT" classifier "heptag"
)