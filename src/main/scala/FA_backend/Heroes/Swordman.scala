package swordman

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Punch extends Attack("Coup de poing", FactionAlignment.Monster, AttackType.MeleeAttack, attackDifficulty = 5, damageModifier = 2)

object Slash extends Attack("Trancher", FactionAlignment.Monster, AttackType.MeleeAttack, attackDifficulty = 9, damageModifier = 3) {
	var time : Int = 3
	var prob : Double = 0.5
	var damage : Int = 3

	override def updateEffects() = {
		enemyEffect = Some(new Bleed(time, prob, damage))
	}

	updateEffects()
}

object Thrust extends Attack("Coup d'estoc", FactionAlignment.Monster, AttackType.MeleeAttack, attackDifficulty = 10, damageModifier = 5)

object PommelHit extends Attack("Coup de pommeau", FactionAlignment.Monster, AttackType.MeleeAttack, attackDifficulty = 4, damageModifier = 1) {
	var time : Int = 1
	var prob : Double = 0.8

	override def updateEffects() = {
		enemyEffect = Some(new Stun(time, prob))
	}

	updateEffects()
}

class Swordman(fighterID : Int) extends Fighter(fighterID, 0, FactionAlignment.Hero, FighterClass.MeleeFighter) {
    override def toString = "Épéiste"

    var maxLifePoints = 15
    var lifePoints = maxLifePoints
    var meleeCapacity = 7
    var rangeCapacity = 2
    var strength = 6
    var toughness = 5
    var initiative = 7

    override val visual = getClass.getResourceAsStream("/Fighters/swordman.png")

    val attacks = Array(Punch, Slash, Thrust, PommelHit)

	def upgradeStats() = {
		this.maxLifePoints += 3
		
		if (this.level % 2 == 0) {
			this.meleeCapacity += 1
			this.initiative += 1
		}

		if (this.level % 4 == 0) {
			this.strength += 1
			this.toughness += 1
		}
	}

	def upgradeAttacks() = {
		Slash.prob = (Slash.prob + 1.0) / 2.0
		Slash.damage += 1

		PommelHit.prob = 1
		if (this.level % 10 == 0) {
			PommelHit.time += 1
		}

		attacks.foreach(_.damageModifier += 1)
	}
}