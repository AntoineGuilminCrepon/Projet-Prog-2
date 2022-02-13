package skeleton

import attack._
import fighter._


object Fear extends Attack {
    override def toString = "Peur"
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val damageModifier = 1
}

object Slash extends Attack {
    override def toString = "Trancher"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}

object ShieldHit extends Attack {
    override def toString = "Coup de bouclier"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 2
}

object BoneThrow extends Attack {
    override def toString = "Jet d'os"
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val damageModifier = 1
}

class Skeleton(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Squelette"

    val faction = FactionAlignment.Monster
    val maxLifePoints = 15
    var lifePoints = maxLifePoints
    val meleeCapacity = 1
    val rangeCapacity = 1
    val strength = 1
    val toughness = 1
    val initiative = 0

    override val visual = getClass.getResourceAsStream("/skeleton.png")

    val attacks = Array(Fear, Slash, ShieldHit, BoneThrow)
}