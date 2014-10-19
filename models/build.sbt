name := "models"

version := Common.version

scalaVersion := "2.10.4"

exportJars := true

artifactName := { (sv: ScalaVersion, module: ModuleID, artifact: Artifact) =>
  s"${Common.name}-${module.revision}-${module.name}.${artifact.extension}"
}
