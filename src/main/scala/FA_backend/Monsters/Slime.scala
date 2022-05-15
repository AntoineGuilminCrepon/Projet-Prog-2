package slime

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object AcidShot extends Attack("Jet d'acide", FactionAlignment.Hero, AttackType.RangeAttack, attackDifficulty = 8, damageModifier = 2) {
	var time : Int = 3
	var prob : Double = 0.9
	var damage : Int = 3

	override def updateEffects() = {
		enemyEffect = Some(new Acid(time, prob, damage))
	}

	updateEffects()
}

object Rush extends Attack("Charge", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 5, damageModifier = 2)

object Wrap extends Attack("Enveloppement", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 7, damageModifier = 3) {
	var time : Int = 1
	var prob : Double = 0.7

	override def updateEffects() = {
		enemyEffect = Some(new Stun(time, prob))
	}

	updateEffects()
}

object SlimyPunch extends Attack("Coup gluant", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 6, damageModifier = 1) {
	var time : Int = 2
	var prob : Double = 0.65
	var ccDebuf : Int = 4
	var ctDebuf : Int = 4

	override def updateEffects() = {
		enemyEffect = Some(new CapacitiesDebuf(time, prob, ccDebuf, ctDebuf))
	}

	updateEffects()
}

class Slime(fighterID : Int) extends Fighter(fighterID, 0, FactionAlignment.Monster, FighterClass.MeleeFighter) {
    override def toString = "Slime"
	override val fighterTypes = Array(FighterType.Gelatinous)

    var maxLifePoints = 10
    var lifePoints = maxLifePoints
    var meleeCapacity = 4
    var rangeCapacity = 2
    var strength = 3
    var toughness = 4
    var initiative = 2

    val visual = getClass.getResourceAsStream("/Fighters/slime.png")

    val attacks = Array(AcidShot, Rush, Wrap, SlimyPunch)

	def upgradeStats() = {
		this.maxLifePoints += 3
		
		if (this.level % 3 == 0) {
			this.meleeCapacity += 1
			this.rangeCapacity += 1
		}

		if (this.level % 5 == 0) {
			this.strength += 1
			this.toughness += 1
		}

		if (this.level % 6 == 0) {
			this.initiative += 1
		}
	}

	def upgradeAttacks() = {
		AcidShot.prob = 1
		Wrap.prob = (Wrap.prob + 1.0) / 2.0
		SlimyPunch.prob = (SlimyPunch.prob + 1.0) / 2.0

		if (this.level % 10 == 0) {
			AcidShot.time += 1
			Wrap.time += 1
			SlimyPunch.time += 1

			AcidShot.damage += 1
			SlimyPunch.ccDebuf += 3
			SlimyPunch.ctDebuf += 3
		}

		attacks.foreach(_.damageModifier += 1)
	}

}