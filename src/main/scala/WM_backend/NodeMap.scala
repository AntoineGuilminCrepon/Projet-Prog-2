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
    var nodeMap = Array.ofDim[NodeType.EnumVal](length + 2, 2)
    var nodeBounds : List[((Int, Int), (Int, Int))] = List()

    var currentNode = (0,0)

    private def nbNodesOnColumn(i : Int) : Int = {
        return if (i >= 0 && i<= length + 1) nodeMap(i).filter(_ != NodeType.EmptyNode).length else 0
    }

    private def generate() : Unit = {
        nodeMap(0)(0) = NodeType.NeutralNode
        nodeMap(0)(1) = NodeType.EmptyNode

        var random = new scala.util.Random
        for (i <- 1 to length) {
            val nbNC = random.nextInt(2) + 1
            
            if (nbNC == 1) {
                val position = if (nbNodesOnColumn(i - 2) == 2 || i <= 2) random.nextInt(2) else 1 - nodeMap(i - 2).indexOf(NodeType.FightNode)
                nodeMap(i)(position) = NodeType.FightNode

                if (nodeMap(i - 1)(position) == NodeType.EmptyNode) {
                    nodeMap(i)(1 - position) = NodeType.NeutralNode
                    nodeBounds = ((i - 1, 1 - position), (i, 1 - position)) :: ((i, 1 - position), (i, position)) :: nodeBounds
                } else {
                    nodeMap(i)(1 - position) = NodeType.EmptyNode
                    nodeBounds = ((i - 1, position), (i, position)) :: nodeBounds
                }

            } else {
                if (nbNodesOnColumn(i - 1) == 2) {
                    nodeMap(i)(0) = NodeType.FightNode
                    nodeMap(i)(1) = NodeType.FightNode
                    nodeBounds = ((i - 1, 0), (i, 0)) :: ((i - 1, 1), (i, 1)) :: nodeBounds
                } else {
                    val position = 1 - nodeMap(i - 1).indexOf(NodeType.EmptyNode)
                    nodeMap(i)(position) = NodeType.NeutralNode
                    nodeMap(i)(1 - position) = NodeType.FightNode
                    nodeBounds = ((i - 1, position), (i, position)) :: ((i, position), (i, 1 - position)) :: nodeBounds
                }
            }
        }
        
        nodeMap(length + 1)(0) = NodeType.FightNode
        nodeMap(length + 1)(1) = NodeType.EmptyNode
 
		println(nodeBounds)
    }

    this.generate()

    override def toString() : String = {
        var stringMap = ""

        for (i <- 0 to (length + 1)) {
            stringMap += "-" + nodeMap(i)(0)
        }

        stringMap += "\n"

        for (i <- 0 to (length + 1)) {
            stringMap += "-" + nodeMap(i)(1)
        }

        return stringMap
    }

    def toColoredString() : Array[Text] = {
        var stringMap = Array(new Text(""), new Text(nodeMap(currentNode._1)(currentNode._2).toString()) {setFill(Color.RED)}, new Text(""))
        var isCurrentNodePassed = false
        
        for (i <- 0 to (length + 1)) {
            if (currentNode._1 == i && currentNode._2 == 0) {isCurrentNodePassed = true; stringMap(0).setText(stringMap(0).getText() + "-")}
            if (!isCurrentNodePassed) {
                stringMap(0).setText(stringMap(0).getText() + "-" + nodeMap(i)(0).toString())
            } else if (!(currentNode._1 == i && currentNode._2 == 0)) {
                stringMap(2).setText(stringMap(2).getText() + "-" + nodeMap(i)(0).toString())
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
                stringMap(0).setText(stringMap(0).getText() + "-" + nodeMap(i)(1).toString())
            } else if (!(currentNode._1 == i && currentNode._2 == 1)) {
                stringMap(2).setText(stringMap(2).getText() + "-" + nodeMap(i)(1).toString())
            }
        }
 
        return stringMap
    }
}