package slime

import attack._
import fighter._

object AcidShot extends Attack {
    override def toString = "Jet d'acide"
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 4
    override val damageModifier = 2
}

object Rush extends Attack {
    override def toString = "Charge"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 1
    override val damageModifier = 1
}

object Wrap extends Attack {
    override def toString = "Enveloppement"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 6
    override val damageModifier = 3
}

object SlimyPunch extends Attack {
    override def toString = "Coup gluant"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 2
    override val damageModifier = 1
}

class Slime(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Slime"

    val faction = FactionAlignment.Monster
    val maxLifePoints = 10
    var lifePoints = maxLifePoints
    val meleeCapacity = 4
    val rangeCapacity = 2
    val strength = 3
    val toughness = 4
    val initiative = 2

    val visual = getClass.getResourceAsStream("/slime.png")

    val attacks = Array(AcidShot, Rush, Wrap, SlimyPunch)
}