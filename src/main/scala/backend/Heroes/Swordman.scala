package swordman

import attack._
import fighter._


object Punch extends Attack {
    override def toString = "Coup de poing"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 5
    override val damageModifier = 2
}

object Slash extends Attack {
    override def toString = "Trancher"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 7
    override val damageModifier = 3
}

object Thrust extends Attack {
    override def toString = "Coup d'estoc"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 10
    override val damageModifier = 4
}

object PommelHit extends Attack {
    override def toString = "Coup de pommeau"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 2
    override val damageModifier = 1
}

class Swordman(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Épéiste"

    val faction = FactionAlignment.Hero
    val maxLifePoints = 15
    var lifePoints = maxLifePoints
    val meleeCapacity = 7
    val rangeCapacity = 2
    val strength = 6
    val toughness = 5
    val initiative = 7

    override val visual = getClass.getResourceAsStream("/swordman.png")

    val attacks = Array(Punch, Slash, Thrust, PommelHit)
}