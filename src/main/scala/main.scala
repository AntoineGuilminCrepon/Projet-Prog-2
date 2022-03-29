import javafx.application._

import worldmap._

object Main {
    def main(args : Array[String]) = {
        Thread.setDefaultUncaughtExceptionHandler((t, e) => System.err.println());

        Application.launch(classOf[WorldMap])
    }
}