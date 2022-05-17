package dragon

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object FireBlast extends Attack("Déflagration", FactionAlignment.Hero, AttackType.RangeAttack, attackDifficulty = 7, damageModifier = 4) {
	var time : Int = 2
	var prob : Double = 0.7
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Fire(time, prob, damage))
	}

	updateEffects()
}

object ThornThrow extends Attack("Lancé d'épine", FactionAlignment.Hero, AttackType.RangeAttack, attackDifficulty = 5, damageModifier = 3) {
	var time : Int = 2
	var prob : Double = 0.7
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Bleed(time, prob, damage))
	}

	updateEffects()
}

object Crushing extends Attack("Écrasement", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 4, damageModifier = 2) {
	var time : Int = 1
	var prob : Double = 0.6

	override def updateEffects() = {
		enemyEffect = Some(new Stun(time, prob))
	}

	updateEffects()
}

object Howling extends Attack("Hurlement", FactionAlignment.Hero, AttackType.MagicAttack, attackDifficulty = 1, damageModifier = 1) {
	var time : Int = 3
	var prob : Double = 0.9

	override def updateEffects() = {
		enemyEffect = Some(new Stun(time, prob))
	}

	updateEffects()
}

class Dragon(fighterID : Int) extends Fighter(fighterID, 7, FactionAlignment.Monster, FighterClass.RangeFighter) {
    override def toString = "Dragon"

    var maxLifePoints = 25
    var lifePoints = maxLifePoints
    var meleeCapacity = 7
    var rangeCapacity = 7
    var strength = 8
    var toughness = 8
    var initiative = 1

    override val visual = getClass.getResourceAsStream("/Fighters/dragon.png")

    val attacks = Array(FireBlast, ThornThrow, Crushing, Howling)

	def upgradeStats() = {
		this.maxLifePoints += 5
		
		if (this.level % 2 == 0) {
			this.meleeCapacity += 1
			this.rangeCapacity += 1
		}

		if (this.level % 4 == 0) {
			this.strength += 1
			this.toughness += 1
		}

		if (this.level % 6 == 0) {
			this.initiative += 1
		}
	}

	def upgradeAttacks() = {
		FireBlast.prob = (FireBlast.prob + 1.0) / 2.0
		ThornThrow.prob = (ThornThrow.prob + 1.0) / 2.0
		Crushing.prob = (Crushing.prob + 0.1).min(1.0)
		Howling.prob = 1

		if (this.level % 10 == 0) {
			FireBlast.time += 1
			ThornThrow.time += 1
			Crushing.time += 1
			Howling.time += 1

			FireBlast.damage += 1
			ThornThrow.damage += 1
		}

		attacks.foreach(_.damageModifier += 1)
	}
}