package bandit

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object ArrowShower extends Attack("Volée de flèches", FactionAlignment.Hero, AttackType.RangeAttack, attackDifficulty = 3, damageModifier = 3)

object Mania extends Attack("Furie", FactionAlignment.Hero, AttackType.RangeAttack, attackDifficulty = 7, damageModifier = 4) {
	var time : Int = 2
	var prob : Double = 0.3
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Bleed(time, prob, damage))
	}

	updateEffects()
}

object PoisonousArrow extends Attack("Flèche empoisonnée", FactionAlignment.Hero, AttackType.RangeAttack, attackDifficulty = 3, damageModifier = 2) {
	var time : Int = 3
	var prob : Double = 0.7
	var damage : Int = 1

	override def updateEffects() = {
		enemyEffect = Some(new Poison(time, prob, damage))
	}

	updateEffects()
}

object Bomb extends Attack("Bombe !", FactionAlignment.Hero, AttackType.RangeAttack, attackDifficulty = 5, damageModifier = 4) {
	var time : Int = 1
	var prob : Double = 0.4

	override def updateEffects() = {
		enemyEffect = Some(new Stun(time, prob))
	}

	updateEffects()
}

class Bandit(fighterID : Int) extends Fighter(fighterID, 7, FactionAlignment.Monster, FighterClass.RangeFighter) {
    override def toString = "Bandit"

	var maxLifePoints = 10
	var lifePoints = maxLifePoints
    var meleeCapacity = 4
    var rangeCapacity = 8
    var strength = 3
    var toughness = 3
    var initiative = 3

    override val visual = getClass.getResourceAsStream("/Fighters/bandit.png")

    val attacks = Array(ArrowShower, Mania, PoisonousArrow, Bomb)
}
