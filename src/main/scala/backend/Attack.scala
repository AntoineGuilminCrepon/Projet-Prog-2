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
    override def toString = "Coup de poing"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}

class Slash extends Attack {
    override def toString = "Trancher"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}

class Thrust extends Attack {
    override def toString = "Coup d'estoc"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 2
}

class PommelHit extends Attack {
    override def toString = "Coup de pommeau"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}

class AcidShot extends Attack {
    override def toString = "Jet d'acide"
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val damageModifier = 2
}

class Rush extends Attack {
    override def toString = "Charge"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}

class Wrap extends Attack {
    override def toString = "Enveloppement"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}

class SlimyPunch extends Attack {
    override def toString = "Coup gluant"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}
