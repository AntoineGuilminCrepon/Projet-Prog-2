package skaven

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Earthquake extends Attack("Séisme", FactionAlignment.Hero, AttackType.RangeAttack, attackDifficulty = 7, damageModifier = 4)

object Blade extends Attack("Coup de lame", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 2, damageModifier = 3) {
	var time : Int = 3
	var prob : Double = 0.85
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Poison(time, prob, damage))
	}

	updateEffects()
}

object Bite extends Attack("Morsure", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 2, damageModifier = 2) {
	var time : Int = 3
	var prob : Double = 0.8
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Bleed(time, prob, damage))
	}

	updateEffects()
}

object WarpstoneThrow extends Attack("Jet de malpierre", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 1, damageModifier = 2) {
	var time : Int = 3
	var prob : Double = 0.8
	var damage : Int = 1

	override def updateEffects() = {
		enemyEffect = Some(new Poison(time, prob, damage))
	}

	updateEffects()
}

class Skaven(fighterID : Int) extends Fighter(fighterID, 5, FactionAlignment.Monster, FighterClass.MeleeFighter) {
    override def toString = "Skaven"

    var maxLifePoints = 8
    var lifePoints = maxLifePoints
    var meleeCapacity = 3
    var rangeCapacity = 7
	/* magicCapacity = 13 */
    var strength = 3
    var toughness = 4
    var initiative = 8

    override val visual = getClass.getResourceAsStream("/Fighters/skaven.png")

    val attacks = Array(Earthquake, Blade, Bite, WarpstoneThrow)
	
	def upgradeStats() = {
		this.maxLifePoints += 3
		
		if (this.level % 2 == 0) {
			this.meleeCapacity += 1
			this.rangeCapacity += 1
		}

		if (this.level % 4 == 0) {
			this.initiative += 2
			this.strength += 1
			this.toughness += 1
		}
	}

	def upgradeAttacks() = {
		Blade.prob = (Blade.prob + 1.0) / 2.0
		Bite.prob = (Bite.prob + 1.0) / 2.0
		WarpstoneThrow.prob = (WarpstoneThrow.prob + 1.0) / 2.0

		if (this.level % 10 == 0) {
			Blade.time += 1
			Bite.time += 1
			WarpstoneThrow.time += 1

			Blade.damage += 1
			Bite.damage += 1
			WarpstoneThrow.damage += 1
		}

		attacks.foreach(_.damageModifier += 1)
	}
}