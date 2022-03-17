package nodemap

import javafx.scene.text._
import javafx.scene.paint._

object NodeType {
    sealed trait EnumVal
    case object FightNode extends EnumVal {override def toString() = {"X"}}
    case object NeutralNode extends EnumVal {override def toString() = {"O"}}
    case object EmptyNode extends EnumVal {override def toString() = {" "}}
}

class NodeMap(length : Int) {
    var map = Array.ofDim[NodeType.EnumVal](length + 2, 2)
    var nodeBounds : List[((Int, Int), (Int, Int))] = List(((length + 1, 0), (length, 0)))

    var currentNode = (0,0)

    private def nbNodesOnColumn(i : Int) : Int = {
        return if (i >= 0 && i<= length + 1) map(i).filter(_ != NodeType.EmptyNode).length else 0
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
                    nodeBounds = ((i - 1, 1 - position), (i, 1 - position)) :: ((i, 1 - position), (i, position)) :: nodeBounds
                } else {
                    map(i)(1 - position) = NodeType.EmptyNode
                    nodeBounds = ((i - 1, position), (i, position)) :: nodeBounds
                }

            } else {
                if (nbNodesOnColumn(i - 1) == 2) {
                    map(i)(0) = NodeType.FightNode
                    map(i)(1) = NodeType.FightNode
                    nodeBounds = ((i - 1, 0), (i, 0)) :: ((i - 1, 1), (i, 1)) :: nodeBounds
                } else {
                    val position = 1 - map(i - 1).indexOf(NodeType.EmptyNode)
                    map(i)(position) = NodeType.NeutralNode
                    map(i)(1 - position) = NodeType.FightNode
                    nodeBounds = ((i - 1, position), (i, position)) :: ((i, position), (i, 1 - position)) :: nodeBounds
                }
            }
        }

		if (map(length)(0) == NodeType.EmptyNode) {
			map(length)(0) = NodeType.NeutralNode
			nodeBounds = ((length, 1), (length, 0)) :: nodeBounds
		}

        map(length + 1)(0) = NodeType.FightNode
        map(length + 1)(1) = NodeType.EmptyNode
 
    }

    this.generate()

	def isThereBound(coord1 : (Int, Int), coord2 : (Int, Int)) : Boolean = {
		return (nodeBounds.contains((coord1, coord2)) || nodeBounds.contains((coord2, coord1)))
	}

    override def toString() : String = {
        var stringMap = ""

        for (i <- 0 to (length + 1)) {
            stringMap += "-" + map(i)(0)
        }

        stringMap += "\n"

        for (i <- 0 to (length + 1)) {
            stringMap += "-" + map(i)(1)
        }

        return stringMap
    }

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