val scalafx = "org.scalafx" %% "scalafx" % "16.0.0-R22"

val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  	"io.circe" %% "circe-core",
  	"io.circe" %% "circe-generic",
  	"io.circe" %% "circe-parser"
).map(_ % circeVersion)

lazy val root = (project in file(".")).
    settings(
        name := "ProjRPG",
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