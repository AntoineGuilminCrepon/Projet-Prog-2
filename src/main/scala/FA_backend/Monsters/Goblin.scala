package goblin

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Rush extends Attack("Charge", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 6, damageModifier = 2)

object BackStab extends Attack("Poignarder dans le dos", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 11, damageModifier = 5) {
	var time : Int = 3
	var prob : Double = 0.7
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Bleed(time, prob, damage))
	}

	updateEffects()
}

object PoisonousDagger extends Attack("Dague empoisonnée", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 4, damageModifier = 2) {
	var time : Int = 3
	var prob : Double = 0.6
	var damage : Int = 3

	override def updateEffects() = {
		enemyEffect = Some(new Poison(time, prob, damage))
	}

	updateEffects()
}

object SurpriseAttack extends Attack("Attaque surprise", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 8, damageModifier = 3)

class Goblin(fighterID : Int) extends Fighter(fighterID, 1, FactionAlignment.Monster, FighterClass.MeleeFighter) {
    override def toString = "Gobelin"

    var maxLifePoints = 8
    var lifePoints = maxLifePoints
    var meleeCapacity = 4
    var rangeCapacity = 2
    var strength = 5
    var toughness = 3
    var initiative = 6

    override val visual = getClass.getResourceAsStream("/Fighters/goblin.png")

    val attacks = Array(Rush, BackStab, PoisonousDagger, SurpriseAttack)
}