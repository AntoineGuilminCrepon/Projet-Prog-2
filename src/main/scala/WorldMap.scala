package worldmap

import javafx._
import javafx.application._
import javafx.stage._
import javafx.scene._
import javafx.scene.control._
import javafx.scene.layout._
import javafx.geometry._
import javafx.scene.shape._
import javafx.scene.input._

import javafx.scene.text._
import javafx.scene.paint._

import nodemap._

class WorldMap extends Application {
    override def start(stage : Stage) : Unit = {
        stage.setTitle("World map")

        var nodeMap = new NodeMap(8)

        def updateScene() : Unit = {
            val textParts = nodeMap.toColoredString()
            var root = new TextFlow(textParts(0), textParts(1), textParts(2))
            var scene = new Scene(root, 1290, 1040)

            scene.setOnKeyPressed(e => {
                println(e.getCode())
                if (e.getCode == KeyCode.RIGHT) {
                    nodeMap.currentNode = (if (nodeMap.currentNode._1 < 8 + 1) nodeMap.currentNode._1 + 1 else nodeMap.currentNode._1, nodeMap.currentNode._2)
                } else if (e.getCode == KeyCode.LEFT) {
                    nodeMap.currentNode = (if (nodeMap.currentNode._1 > 0) nodeMap.currentNode._1 - 1 else nodeMap.currentNode._1, nodeMap.currentNode._2)
                } else if (e.getCode == KeyCode.UP) {
                    nodeMap.currentNode = (nodeMap.currentNode._1, 0)
                } else if (e.getCode == KeyCode.DOWN) {
                    nodeMap.currentNode = (nodeMap.currentNode._1, 1)
                }
                
                updateScene()
            })

            stage.setScene(scene)
        }

        updateScene()
        stage.show()
    }
}