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

	def upgradeStats() = {
		this.maxLifePoints += 3
		
		if (this.level % 2 == 0) {
			this.meleeCapacity += 1
		}

		if (this.level % 4 == 0) {
			this.initiative += 1
			this.strength += 1
			this.toughness += 1
		}
	}

	def upgradeAttacks() = {
		BackStab.prob = (BackStab.prob + 1.0) / 2.0
		PoisonousDagger.prob = (PoisonousDagger.prob + 0.1).min(1.0)

		PoisonousDagger.time += 1
		BackStab.damage += 1
		if (this.level % 10 == 0) {
			BackStab.time += 1

			PoisonousDagger.damage += 1
		}

		attacks.foreach(_.damageModifier += 1)
	}
}