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
import nodeshapes._

class WorldMap extends Application {
    val length = 8

	override def start(stage : Stage) : Unit = {
        stage.setTitle("World map")

        var nodeMap = new NodeMap(length)
		println(nodeMap.toString())

		var nodeGraph = Array.ofDim[Node with NodeShape](length + 2, 2)
		for (i <- 0 to length + 1) {
			for (j <- 0 to 1) {			
				nodeGraph(i)(j) = 
				nodeMap.map(i)(j) match {
					case NodeType.FightNode => new CrossNode(((i.toFloat + 0.5) * (1290.toFloat / (length - 1).toFloat)).toInt, 520)
					case NodeType.NeutralNode => new EllipseNode(((i.toFloat + 0.5) * (1290.toFloat / (length - 1).toFloat)).toInt, 520)
					case NodeType.EmptyNode => new NoneNode
				}
			}
		}

		var root = new GridPane() {
			this.setAlignment(Pos.CENTER)
			this.getRowConstraints().addAll(new RowConstraints(350), new RowConstraints(350), new RowConstraints(340))		
			
			for (i <- 0 to length + 1) {
				this.getColumnConstraints.add(new ColumnConstraints((1290.0 / (length - 1).toFloat).toInt))
				for (j <- 0 to 1) {
					nodeGraph(i)(j).setColor(Color.BLACK)
					this.add(nodeGraph(i)(j), i, j)
				}
			}
		}
		
		var scene = new Scene(root, 1290, 1040)
		nodeGraph(nodeMap.currentNode._1)(nodeMap.currentNode._2).setColor(Color.RED)

        def updateScene() : Unit = {

            scene.setOnKeyPressed(e => {
				nodeGraph(nodeMap.currentNode._1)(nodeMap.currentNode._2).setColor(Color.BLACK)
                if (e.getCode == KeyCode.RIGHT) {
                    nodeMap.currentNode = (if (nodeMap.currentNode._1 < 8 + 1) nodeMap.currentNode._1 + 1 else nodeMap.currentNode._1, nodeMap.currentNode._2)
                } else if (e.getCode == KeyCode.LEFT) {
                    nodeMap.currentNode = (if (nodeMap.currentNode._1 > 0) nodeMap.currentNode._1 - 1 else nodeMap.currentNode._1, nodeMap.currentNode._2)
                } else if (e.getCode == KeyCode.UP) {
                    nodeMap.currentNode = (nodeMap.currentNode._1, 0)
                } else if (e.getCode == KeyCode.DOWN) {
                    nodeMap.currentNode = (nodeMap.currentNode._1, 1)
                }
                
				nodeGraph(nodeMap.currentNode._1)(nodeMap.currentNode._2).setColor(Color.RED)
                updateScene()
            })

            stage.setScene(scene)
        }

        updateScene()
        stage.show()
    }
}