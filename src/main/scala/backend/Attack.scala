package attack

object AttackType {
    sealed trait EnumVal
    case object RangeAttack extends EnumVal
    case object MeleeAttack extends EnumVal
}

abstract class Attack {
    val attackType : AttackType.EnumVal
    
    val damageModifier : Int
}

class Punch extends Attack {
    override def toString = "Punch"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}

class Slash extends Attack {
    override def toString = "Slash"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}