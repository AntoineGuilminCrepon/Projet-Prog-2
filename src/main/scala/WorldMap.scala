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
import javafx.scene.paint._
import javafx.scene.transform._

import fightarena._

import initworldmap._

import nodemap._
import nodeshapes._

/* Classe principale de cette partie */
class WorldMap extends Application {
    val length = 25

	var initWM = InitWorldMap.createWM(length)
	var nodeMap = initWM._1
	var map = initWM._2
	var nodeGraph = initWM._3

	override def start(stage : Stage) : Unit = {
        stage.setTitle("World map")
		var root = new Group() {
			var grid = new GridPane()
			this.getChildren.add(grid)

			grid.setAlignment(Pos.CENTER)
			grid.getRowConstraints().addAll(new RowConstraints(700), new RowConstraints(340))		
			
			grid.add(map, 0, 0)
		}
		
		var scene = new Scene(root, 1920, 1080)
		nodeGraph(nodeMap.currentNode._1)(nodeMap.currentNode._2).setColor(Color.RED)

        def updateScene() : Unit = {

            scene.setOnKeyPressed(e => {
				nodeGraph(nodeMap.currentNode._1)(nodeMap.currentNode._2).setColor(Color.BLACK)
				val previousNode = nodeMap.currentNode
				var direction : Direction.EnumVal = Direction.Up
				
				val translateX = new Translate()
				translateX.setX(-1800)

                if (e.getCode == KeyCode.RIGHT) {
                    nodeMap.currentNode = (if (nodeMap.currentNode._1 < length + 1) nodeMap.currentNode._1 + 1 else nodeMap.currentNode._1, nodeMap.currentNode._2)
					direction = Direction.Right
				} else if (e.getCode == KeyCode.LEFT) {
                    nodeMap.currentNode = (if (nodeMap.currentNode._1 > 0) nodeMap.currentNode._1 - 1 else nodeMap.currentNode._1, nodeMap.currentNode._2)
					direction = Direction.Left
                } else if (e.getCode == KeyCode.UP) {
                    nodeMap.currentNode = (nodeMap.currentNode._1, 0)
					direction = Direction.Up
                } else if (e.getCode == KeyCode.DOWN) {
                    nodeMap.currentNode = (nodeMap.currentNode._1, 1)
					direction = Direction.Down
                } else if (e.getCode == KeyCode.SPACE || e.getCode == KeyCode.ENTER && nodeMap.map(nodeMap.currentNode._1)(nodeMap.currentNode._2) == NodeType.FightNode) {
					stage.close()
					var fightarena = new FightArena
					fightarena.start(stage)
					return
				}

				if (previousNode != nodeMap.currentNode && !nodeMap.isThereBound(previousNode, direction)) {
					nodeMap.currentNode = previousNode
				} else if (previousNode != nodeMap.currentNode) {
					e.getCode match {
						case KeyCode.RIGHT | KeyCode.LEFT =>
							map.getTransforms.clear()
							for (_ <- 1 to (nodeMap.currentNode._1 / 10)) {
								map.getTransforms.add(translateX)
							}
						case _ => ()
					}
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