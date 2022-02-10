val scalafx = "org.scalafx" %% "scalafx" % "16.0.0-R22"

lazy val root = (project in file(".")).
    settings(
        name := "FightArena",
        fork := true,
        libraryDependencies += scalafx,
        libraryDependencies ++= {
        // Determine OS version of JavaFX binaries
        lazy val osName = System.getProperty("os.name") match {
            case n if n.startsWith("Linux") => "linux"
            case n if n.startsWith("Mac") => "mac"
            case n if n.startsWith("Windows") => "win"
            case _ => throw new Exception("Unknown platform!")
        }
        Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
        .map(m => "org.openjfx" % s"javafx-$m" % "16" classifier osName)
        }
    )