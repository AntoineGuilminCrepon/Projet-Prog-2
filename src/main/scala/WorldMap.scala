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
import javafx.geometry._

import initfightarena._
import fighter._
import monsters._
import wiki._

import initworldmap._
import savesfrontend._
import savesbackend._
import nodemap._
import nodeshapes._

/* Classe principale de cette partie */
class WorldMap extends Application with InitFightArena {
    val length = 25

	var initWM = InitWorldMap.createWM(length)
	var nodeMap = initWM._1
	var map = initWM._2
	var nodeGraph = initWM._3
	var heroes = initWM._4

	var initSave : (NodeMap, Array[Fighter]) = (new NodeMap(1), Array())
	var initGraph : (Pane, Array[Array[Node with NodeShape]]) = (new Pane(), Array())


	override def start(stage : Stage) : Unit = {
        stage.setTitle("World map")
		var root = new Group() {
			var grid = new GridPane()
			this.getChildren.addAll(grid, SavesPane)
			SavesPane.setVisible(false)

			grid.setAlignment(Pos.CENTER)
			grid.getRowConstraints().addAll(new RowConstraints(700), new RowConstraints(340))		
			
			grid.add(map, 0, 0)

			var wikiButton = new Button("Wiki") {
				setMinHeight(110)
				setMinWidth(645)
				setFocusTraversable(false)
				getTransforms.add(new Translate() {setX(635)})
				setOnAction(_ => new Wiki(stage))
			}

			grid.add(wikiButton, 0, 1)
		}

		var scene = new Scene(root, 1920, 1080)
		nodeGraph(nodeMap.currentNode._1)(nodeMap.currentNode._2).setColor(Color.RED)
	
		SaveButton.setOnAction(_ => {
			Saves.makeSave(nodeMap, heroes)
			SaveLabel.setText("Sauvegarde effectuée")
		})

		LoadButton.setOnAction(_ => {
			initSave = Saves.loadSave()
			nodeMap = initSave._1
			heroes = initSave._2
			initGraph = InitWorldMap.nodeMapToGraph(length, nodeMap.map, nodeMap.bounds)

			map = initGraph._1
			root = new Group() {
				var grid = new GridPane()
				this.getChildren.addAll(grid, SavesPane)
				SavesPane.setVisible(false)

				grid.setAlignment(Pos.CENTER)
				grid.getRowConstraints().addAll(new RowConstraints(700), new RowConstraints(340))		
				
				grid.add(map, 0, 0)

				var wikiButton = new Button("Wiki") {
					setMinHeight(110)
					setMinWidth(645)
					setFocusTraversable(false)
					getTransforms.add(new Translate() {setX(635)})
					setOnAction(_ => new Wiki(stage))
				}

				val translateX = new Translate() { setX(-1800) }
				for (_ <- 1 to (nodeMap.currentNode._1 / 10)) {
					map.getTransforms.add(translateX)
				}

				grid.add(wikiButton, 0, 1)
			}

			scene = new Scene(root, 1920, 1080)
			nodeGraph = initGraph._2
			nodeGraph(nodeMap.currentNode._1)(nodeMap.currentNode._2).setColor(Color.RED)
			stage.setScene(scene)
			updateScene()

			SaveLabel.setText("Sauvegarde chargée")
		})
	
        def updateScene() : Unit = {
            scene.setOnKeyPressed(e => {
				nodeGraph(nodeMap.currentNode._1)(nodeMap.currentNode._2).setColor(Color.BLACK)
				var previousNode = nodeMap.currentNode
				var direction : Direction.EnumVal = Direction.Up
				
				val translateX = new Translate()
				translateX.setX(-1800)

				e.getCode match {
					case KeyCode.RIGHT => {
						nodeMap.currentNode = (if (nodeMap.currentNode._1 < length + 1) nodeMap.currentNode._1 + 1 else nodeMap.currentNode._1, nodeMap.currentNode._2)
						direction = Direction.Right
					}
					case KeyCode.LEFT => {
						nodeMap.currentNode = (if (nodeMap.currentNode._1 > 0) nodeMap.currentNode._1 - 1 else nodeMap.currentNode._1, nodeMap.currentNode._2)
						direction = Direction.Left
					}
					case KeyCode.UP => {
						nodeMap.currentNode = (nodeMap.currentNode._1, 0)
						direction = Direction.Up
					}
					case KeyCode.DOWN => {
						nodeMap.currentNode = (nodeMap.currentNode._1, 1)
						direction = Direction.Down
					} 
					case KeyCode.SPACE | KeyCode.ENTER => {
						if (nodeMap.map(nodeMap.currentNode._1)(nodeMap.currentNode._2) == NodeType.FightNode) {
						restartFA(stage, heroes, Monsters.getThreeRandomMonsters(3))
						return
					}}
					case KeyCode.S => {
						SaveButton.fire()
					} 
					case KeyCode.L => {
						LoadButton.fire()
						previousNode = nodeMap.currentNode
					}
					case KeyCode.ESCAPE => {
						SaveLabel.setText("Menu de sauvegarde")
						SavesPane.setVisible(!SavesPane.isVisible())
					}
					case _ => ()
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