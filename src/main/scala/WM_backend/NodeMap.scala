package nodemap

import javafx.scene.text._
import javafx.scene.paint._

/* Classe énumérant les différents types de noeuds sur le monde extérieur */
object NodeType {
    sealed trait EnumVal { var isCleared = false }
    case object FightNode extends EnumVal { override def toString() = {"X"} }
    case object NeutralNode extends EnumVal { override def toString() = {"O"}; isCleared = true }
    case object EmptyNode extends EnumVal { override def toString() = {" "} }
}

object Direction {
    sealed trait EnumVal
    case object Up extends EnumVal { override def toString() = {"Up"} }
    case object Right extends EnumVal { override def toString() = {"Right"} }
	case object Down extends EnumVal { override def toString() = {"Down"} }
	case object Left extends EnumVal { override def toString() = {"Left"} }
}

/* Représentation interne de tout ce qui concerne le monde extérieur */
class NodeMap(val length : Int) {
    var map = Array.ofDim[NodeType.EnumVal](length + 2, 2)
    var bounds : Array[List[Direction.EnumVal]] = new Array[List[Direction.EnumVal]]((length + 2) * 2)
	for (i <- 0 to (length + 2) * 2 - 1) {
		bounds(i) = List()
	}

	bounds(length) = Direction.Right :: bounds(length)
	bounds(length + 1) = Direction.Left :: bounds(length + 1)

    var currentNode = (0,0)

    private def nbNodesOnColumn(i : Int) : Int = {
        return if (i >= 0 && i<= length + 1) map(i).filter(_ != NodeType.EmptyNode).length else 0
    }

	private def addToBounds(i : Int, col : Int, dir : Direction.EnumVal) = {
		bounds(i + (if (col == 1) length + 2 else 0)) = dir :: bounds(i + (if (col == 1) length + 2 else 0))
	}

    private def generate() : Unit = {
        map(0)(0) = NodeType.NeutralNode
        map(0)(1) = NodeType.EmptyNode

        var random = new scala.util.Random
        for (i <- 1 to length) {
            val nbNC = random.nextInt(2) + 1
            
            if (nbNC == 1) {
                val position = if (nbNodesOnColumn(i - 2) == 2 || i <= 2) random.nextInt(2) else 1 - map(i - 2).indexOf(NodeType.FightNode)
                map(i)(position) = NodeType.FightNode

                if (map(i - 1)(position) == NodeType.EmptyNode) {
                    map(i)(1 - position) = NodeType.NeutralNode
                    addToBounds(i - 1, 1 - position, Direction.Right)
					addToBounds(i, 1 - position, if (position == 0) Direction.Up else Direction.Down)
					addToBounds(i, 1 - position, Direction.Left)
					addToBounds(i, position, if (position == 0) Direction.Down else Direction.Up)
                } else {
                    map(i)(1 - position) = NodeType.EmptyNode
                    addToBounds(i - 1, position, Direction.Right)
					addToBounds(i, position, Direction.Left)
                }

            } else {
                if (nbNodesOnColumn(i - 1) == 2) {
                    map(i)(0) = NodeType.FightNode
                    map(i)(1) = NodeType.FightNode
                    addToBounds(i - 1, 0, Direction.Right)
					addToBounds(i - 1, 1, Direction.Right)
					addToBounds(i, 0, Direction.Left)
					addToBounds(i, 1, Direction.Left)
                } else {
                    val position = 1 - map(i - 1).indexOf(NodeType.EmptyNode)
                    map(i)(position) = NodeType.NeutralNode
                    map(i)(1 - position) = NodeType.FightNode
                    addToBounds(i - 1, position, Direction.Right)
					addToBounds(i, position, if (position == 0) Direction.Down else Direction.Up)
					addToBounds(i, position, Direction.Left)
					addToBounds(i, 1 - position, if (position == 0) Direction.Up else Direction.Down)
                }
            }
        }

		if (map(length)(0) == NodeType.EmptyNode) {
			map(length)(0) = NodeType.NeutralNode
			addToBounds(length, 1, Direction.Up)
			addToBounds(length, 0, Direction.Down)
		}

        map(length + 1)(0) = NodeType.FightNode
        map(length + 1)(1) = NodeType.EmptyNode
 
    }

    this.generate()

	def isThereBound(coord : (Int, Int), direction : Direction.EnumVal) : Boolean = {
		return bounds(coord._1 + (if (coord._2 == 1) length + 2 else 0)).contains(direction)
	}

    override def toString() : String = {
        var stringMap = ""

        for (i <- 0 to (length + 1)) {
            stringMap += map(i)(0) + " "
        }

        stringMap += "\n"

        for (i <- 0 to (length + 1)) {
            stringMap += map(i)(1) + " "
        }

        return stringMap
    }

	/* Fonction de test pour l'affichage du noeud courant */
    def toColoredString() : Array[Text] = {
        var stringMap = Array(new Text(""), new Text(map(currentNode._1)(currentNode._2).toString()) {setFill(Color.RED)}, new Text(""))
        var isCurrentNodePassed = false
        
        for (i <- 0 to (length + 1)) {
            if (currentNode._1 == i && currentNode._2 == 0) {isCurrentNodePassed = true; stringMap(0).setText(stringMap(0).getText() + "-")}
            if (!isCurrentNodePassed) {
                stringMap(0).setText(stringMap(0).getText() + "-" + map(i)(0).toString())
            } else if (!(currentNode._1 == i && currentNode._2 == 0)) {
                stringMap(2).setText(stringMap(2).getText() + "-" + map(i)(0).toString())
            }
        }

        if (!isCurrentNodePassed) {
            stringMap(0).setText(stringMap(0).getText() + "\n")
        } else {
            stringMap(2).setText(stringMap(2).getText() + "\n")
        }

        for (i <- 0 to (length + 1)) {
            if (currentNode._1 == i && currentNode._2 == 1) {isCurrentNodePassed = true; stringMap(0).setText(stringMap(0).getText() + "-")}
            if (!isCurrentNodePassed) {
                stringMap(0).setText(stringMap(0).getText() + "-" + map(i)(1).toString())
            } else if (!(currentNode._1 == i && currentNode._2 == 1)) {
                stringMap(2).setText(stringMap(2).getText() + "-" + map(i)(1).toString())
            }
        }
 
        return stringMap
    }
}