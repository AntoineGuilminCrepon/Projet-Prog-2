package nodemap

object NodeType {
    sealed trait EnumVal
    case object NodeFight extends EnumVal {override def toString() = {"X"}}
    case object NodeNeutral extends EnumVal {override def toString() = {"O"}}
}

class NodeMap(length : Int) {
    
}