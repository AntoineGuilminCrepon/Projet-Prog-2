package attack

import attackeffect._

object AttackType {
    sealed trait EnumVal
    case object RangeAttack extends EnumVal
    case object MeleeAttack extends EnumVal
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