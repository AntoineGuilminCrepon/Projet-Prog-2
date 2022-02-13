package swordman

import attack._
import fighter._


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

class Swordman(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Swordman"

    val faction = FactionAlignment.Hero
    val maxLifePoints = 10
    var lifePoints = maxLifePoints
    val meleeCapacity = 1
    val rangeCapacity = 1
    val strength = 1
    val toughness = 1
    val initiative = 2

    override val visual = getClass.getResourceAsStream("/swordman.png")

    val attacks = Array(new Punch, new Slash, new Thrust, new PommelHit)
}