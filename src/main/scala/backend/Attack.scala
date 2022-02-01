package attack

object AttackType {
    sealed trait EnumVal
    case object RangeAttack extends EnumVal
    case object MeleeAttack extends EnumVal
}

abstract class Attack {
    val name : String
    val attackType : AttackType.EnumVal
    
    val damageModifier : Int
}

class Punch extends Attack {
    override val name = "Punch"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}