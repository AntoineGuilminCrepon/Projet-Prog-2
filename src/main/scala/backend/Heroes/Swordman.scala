package swordman

import attack._
import attackeffect._
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
    override val attackDifficulty = 9
    override val damageModifier = 3

    enemyEffect = Some(new Bleed(3, 0.5, 3))
}

object Thrust extends Attack {
    override def toString = "Coup d'estoc"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 10
    override val damageModifier = 5
}

object PommelHit extends Attack {
    override def toString = "Coup de pommeau"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 4
    override val damageModifier = 1

    enemyEffect = Some(new Stun(1, 0.8))
}

class Swordman(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Épéiste"

    val faction = FactionAlignment.Hero
    var maxLifePoints = 15
    var lifePoints = maxLifePoints
    var meleeCapacity = 7
    var rangeCapacity = 2
    var strength = 6
    var toughness = 5
    var initiative = 7

    override val visual = getClass.getResourceAsStream("/Fighters/swordman.png")

    val attacks = Array(Punch, Slash, Thrust, PommelHit)
}