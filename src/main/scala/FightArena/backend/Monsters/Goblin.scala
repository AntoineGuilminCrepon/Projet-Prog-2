package goblin

import attack._
import attackeffect._
import fighter._


object Rush extends Attack {
    override def toString = "Charge"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 6
    override val damageModifier = 2
}

object Backstab extends Attack {
    override def toString = "Poignarder dans le dos"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 11
    override val damageModifier = 5

    enemyEffect = Some(new Bleed(3, 0.7, 2))
}

object PoisonousDagger extends Attack {
    override def toString = "Dague empoisonnée"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 4
    override val damageModifier = 2

    enemyEffect = Some(new Poison(3, 0.6, 3))
}

object SurpriseAttack extends Attack {
    override def toString = "Attaque surprise"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 8
    override val damageModifier = 3
}

class Goblin(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Gobelin"

    val faction = FactionAlignment.Monster
    var maxLifePoints = 8
    var lifePoints = maxLifePoints
    var meleeCapacity = 4
    var rangeCapacity = 2
    var strength = 5
    var toughness = 3
    var initiative = 6

    override val visual = getClass.getResourceAsStream("/goblin.png")

    val attacks = Array(Rush, Backstab, PoisonousDagger, SurpriseAttack)
}