package skavens

import attack._
import attackeffect._
import fighter._


object Earthquake extends Attack {
    override def toString = "Séisme"
    override val targetAlignment = FactionAlignment.Hero                 /*Could later be relevant as a multi-target attack*/
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 7
    override val damageModifier = 4
}

object Blade extends Attack {
    override def toString = "Coup de lame"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 2
    override val damageModifier = 3

    enemyEffect = Some(new Poison(3, 0.8, 2))
}

object Bite extends Attack {
    override def toString = "Morsure"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 2
    override val damageModifier = 2

    enemyEffect = Some(new Poison(3, 0.8, 2))
}

object RockThrow extends Attack {
    override def toString = "Jeté de roches"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 1
    override val damageModifier = 2

    enemyEffect = Some(new Stun(3, 0.8))
}

class Skavens(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
	override val classIndice = 6
    override def toString = "Skavens"

    val faction = FactionAlignment.Monster
    var maxLifePoints = 8
    var lifePoints = maxLifePoints
    var meleeCapacity = 3
    var rangeCapacity = 7
    var strength = 3
    var toughness = 4
    var initiative = 8

    override val visual = getClass.getResourceAsStream("/Fighters/skavens.png")

    val attacks = Array(Earthquake, Blade, Bite, RockThrow)
}