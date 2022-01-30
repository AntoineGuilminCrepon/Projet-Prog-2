package attack

abstract class AttackType extends Enumeration {
    type AttackType = Value
    val RangeAttack, MeleeAttack = Value
}

abstract class Attack {
    val name : String
    val attackType : AttackType
    
    val damageModifier : Int
}