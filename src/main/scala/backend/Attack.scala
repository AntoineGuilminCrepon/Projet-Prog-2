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