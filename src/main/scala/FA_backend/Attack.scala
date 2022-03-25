package attack

import attackeffect._

object AttackType {
    sealed trait EnumVal
    case object RangeAttack extends EnumVal {override def toString() = {"Distance"}}
    case object MeleeAttack extends EnumVal {override def toString() = {"Corps à corps"}}
	case object MagicAttack extends EnumVal {override def toString() = {"Magie"}}
}

abstract class Attack {
    val targetAlignment : fighter.FactionAlignment.EnumVal
    def description() : String = {"attaque"}

    val attackType : AttackType.EnumVal
    val attackDifficulty : Int
    val damageModifier : Int
    var allyEffect : Option[AttackEffect] = None
    var enemyEffect : Option[AttackEffect] = None
}