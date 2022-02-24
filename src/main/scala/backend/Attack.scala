package attack

import fighter.Fighter

object AttackType {
    sealed trait EnumVal
    case object RangeAttack extends EnumVal
    case object MeleeAttack extends EnumVal
}

class AttackEffect {
    override def toString () : String = {"Effet"} 
    var timer : Int = 0
    val probability : Double = 0.0

    var savedValue : Int = 0

    def effectBeginning(myself : Fighter) : Unit = {}
    def effectEachTurn(myself : Fighter) : String = {""}
    def effectEnding(myself : Fighter) : String = {""}
}

abstract class Attack {
    val targetAlignment : fighter.FactionAlignment.EnumVal
    def description() : String = {"attaque"}

    val attackType : AttackType.EnumVal
    val attackDifficulty : Int
    val damageModifier : Int
    var attackEffect : Option[AttackEffect] = None
}