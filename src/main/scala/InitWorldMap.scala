package initworldmap

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

import fighter._
import heroes._
import monsters._

import nodemap._
import nodeshapes._

object InitWorldMap {
	var isAlreadyDefined = false
	var initNodeMap : NodeMap = new NodeMap(1)
	var initMap : Pane = new Pane()
	var initNodeGraph : Array[Array[Node with NodeShape]] = Array()
	var initHeroes : Array[Fighter] = Array()

	def coordToPosition(coord : (Int, Int)) : (Int, Int) = {(((coord._1.toFloat + 0.5) * (1290.toFloat / 7.toFloat)).toInt, if (coord._2  == 0) 175 else 525)}

	def createWM(length : Int) : (NodeMap, Pane, Array[Array[Node with NodeShape]], Array[Fighter]) = {
		if (isAlreadyDefined) {
			for (i <- 0 to 2) {
				val previousHero = initHeroes(i)
				initHeroes(i) = Heroes.indiceToClass(previousHero.classIndice, i)
				initHeroes(i).lifePoints = 1.max(previousHero.lifePoints)
			}

			return (initNodeMap, initMap, initNodeGraph, initHeroes)
		} else {
			var nodeMap = new NodeMap(length)

			var map = new Pane()
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

			for (i <- 0 to length + 1) {
				for (j <- 0 to 1) {
					nodeGraph(i)(j).setColor(Color.BLACK)
					map.getChildren.add(nodeGraph(i)(j))
				}
			}

			nodeMap.nodeBounds.foreach(nodeBound => map.getChildren.add(new PathBetweenNodes(coordToPosition(nodeBound.coord), nodeBound.direction)))

			isAlreadyDefined = true
			initNodeMap = nodeMap
			initMap = map
			initNodeGraph = nodeGraph
			initHeroes = Heroes.getThreeRandomUniqueHeroes(0)

			return (nodeMap, map, nodeGraph, initHeroes)
		}

	}
}