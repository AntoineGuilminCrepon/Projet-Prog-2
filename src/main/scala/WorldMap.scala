package worldmap

import javafx._
import javafx.application._
import javafx.stage._
import javafx.scene._
import javafx.scene.control._
import javafx.scene.layout._
import javafx.geometry._
import javafx.scene.shape._

import nodemap._

class WorldMap extends Application {
    override def start(stage : Stage) : Unit = {
        stage.setTitle("World map")

        var nodeMap = new NodeMap(8)
        var root = new Label(nodeMap.toString())

        var scene = new Scene(root, 1290, 1040)

        stage.setScene(scene)
        stage.show()
    }
}