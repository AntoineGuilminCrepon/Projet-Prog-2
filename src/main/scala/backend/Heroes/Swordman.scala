package swordman

import attack._
import fighter._


object Punch extends Attack {
    override def toString = "Coup de poing"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}

object Slash extends Attack {
    override def toString = "Trancher"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}

object Thrust extends Attack {
    override def toString = "Coup d'estoc"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 2
}

object PommelHit extends Attack {
    override def toString = "Coup de pommeau"
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val damageModifier = 1
}

class Swordman(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Épéiste"

    val faction = FactionAlignment.Hero
    val maxLifePoints = 10
    var lifePoints = maxLifePoints
    val meleeCapacity = 1
    val rangeCapacity = 1
    val strength = 1
    val toughness = 1
    val initiative = 3

    override val visual = getClass.getResourceAsStream("/swordman.png")

    val attacks = Array(Punch, Slash, Thrust, PommelHit)
}