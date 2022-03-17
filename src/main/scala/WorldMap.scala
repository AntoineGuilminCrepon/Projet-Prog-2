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

import fightarena._

import nodemap._
import nodeshapes._

class WorldMap extends Application {
    val length = 8

	var nodeMap = new NodeMap(length)
	println(nodeMap.toString())

	def coordToPosition(coord : (Int, Int)) : (Int, Int) = {(((coord._1.toFloat + 0.5) * (1290.toFloat / (length - 1).toFloat)).toInt, if (coord._2  == 0) 175 else 525)}

	var nodeGraph = Array.ofDim[Node with NodeShape](length + 2, 2)
	for (i <- 0 to length + 1) {
		for (j <- 0 to 1) {			
			nodeGraph(i)(j) = 
			nodeMap.map(i)(j) match {
				case NodeType.FightNode => new CrossNode(coordToPosition((i,j)))
				case NodeType.NeutralNode => new EllipseNode(coordToPosition((i,j)))
				case NodeType.EmptyNode => new NoneNode
			}
		}
	}
	
	override def start(stage : Stage) : Unit = {
        stage.setTitle("World map")

		var root = new Group() {
			var grid = new GridPane()
			this.getChildren.add(grid)

			grid.setAlignment(Pos.CENTER)
			grid.getRowConstraints().addAll(new RowConstraints(700), new RowConstraints(340))		
			
			var map = new GridPane()
			grid.add(map, 0, 0)

			map.getRowConstraints.addAll(new RowConstraints(350), new RowConstraints(350))
			for (i <- 0 to length + 1) {
				map.getColumnConstraints.add(new ColumnConstraints((1290.0 / (length - 1).toFloat).toInt))
				for (j <- 0 to 1) {
					nodeGraph(i)(j).setColor(Color.BLACK)
					map.add(nodeGraph(i)(j), i, j)
				}
			}

			nodeMap.nodeBounds.foreach(couple => this.getChildren.add(new PathBetweenNodes(coordToPosition(couple._1), coordToPosition(couple._2))))

		}
		
		var scene = new Scene(root, 1290, 1040)
		nodeGraph(nodeMap.currentNode._1)(nodeMap.currentNode._2).setColor(Color.RED)

        def updateScene() : Unit = {

            scene.setOnKeyPressed(e => {
				nodeGraph(nodeMap.currentNode._1)(nodeMap.currentNode._2).setColor(Color.BLACK)
				val previousNode = nodeMap.currentNode

                if (e.getCode == KeyCode.RIGHT) {
                    nodeMap.currentNode = (if (nodeMap.currentNode._1 < 8 + 1) nodeMap.currentNode._1 + 1 else nodeMap.currentNode._1, nodeMap.currentNode._2)
                } else if (e.getCode == KeyCode.LEFT) {
                    nodeMap.currentNode = (if (nodeMap.currentNode._1 > 0) nodeMap.currentNode._1 - 1 else nodeMap.currentNode._1, nodeMap.currentNode._2)
                } else if (e.getCode == KeyCode.UP) {
                    nodeMap.currentNode = (nodeMap.currentNode._1, 0)
                } else if (e.getCode == KeyCode.DOWN) {
                    nodeMap.currentNode = (nodeMap.currentNode._1, 1)
                } else if (e.getCode == KeyCode.SPACE) {
					println("SPACE")
				}

				if (!nodeMap.isThereBound(previousNode, nodeMap.currentNode)) {
					nodeMap.currentNode = previousNode
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