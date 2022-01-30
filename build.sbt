val swing = "org.scala-lang.modules" %% "scala-swing" % "3.0.0"
lazy val root = (project in file(".")).
    settings(
    name := "Graphics",
    libraryDependencies += swing
    )