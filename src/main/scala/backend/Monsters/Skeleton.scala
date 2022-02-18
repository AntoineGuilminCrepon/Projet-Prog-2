package skeleton

import attack._
import fighter._


object Fear extends Attack {
    override def toString = "Peur"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 1
    override val damageModifier = 1
}

object Slash extends Attack {
    override def toString = "Trancher"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 2
    override val damageModifier = 1
}

object ShieldHit extends Attack {
    override def toString = "Coup de bouclier"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 5
    override val damageModifier = 2
}

object BoneThrow extends Attack {
    override def toString = "Jet d'os"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 4
    override val damageModifier = 2
}

class Skeleton(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Squelette"

    val faction = FactionAlignment.Monster
    val maxLifePoints = 20
    var lifePoints = maxLifePoints
    val meleeCapacity = 5
    val rangeCapacity = 3
    val strength = 4
    val toughness = 2
    val initiative = 1

    override val visual = getClass.getResourceAsStream("/skeleton.png")

    val attacks = Array(Fear, Slash, ShieldHit, BoneThrow)
}