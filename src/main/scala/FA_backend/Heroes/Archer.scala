package archer

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object LongBow extends Attack("Arc long", FactionAlignment.Monster, AttackType.RangeAttack, attackDifficulty = 7, damageModifier = 3)

object ShortBow extends Attack("Arc court", FactionAlignment.Monster, AttackType.RangeAttack, attackDifficulty = 4, damageModifier = 2)

object FireArrow extends Attack("Flèche enflammée", FactionAlignment.Monster, AttackType.RangeAttack, attackDifficulty = 2, damageModifier = 1) {
    var time : Int = 2
	var prob : Double = 0.7
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Fire(time, prob, damage))
	}

	updateEffects()
}

object Dagger extends Attack("Dague", FactionAlignment.Monster, AttackType.MeleeAttack, attackDifficulty = 3, damageModifier = 2) {
var time : Int = 2
	var prob : Double = 0.4
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Bleed(time, prob, damage))
	}

	updateEffects()
}

class Archer(fighterID : Int) extends Fighter(fighterID, 2, FactionAlignment.Hero, FighterClass.RangeFighter) {
    override def toString = "Archer"

    var maxLifePoints = 12
    var lifePoints = maxLifePoints
    var meleeCapacity = 3
    var rangeCapacity = 6
    var strength = 5
    var toughness = 4
    var initiative = 9

    override val visual = getClass.getResourceAsStream("/Fighters/archer.png")

    val attacks = Array(LongBow, ShortBow, FireArrow, Dagger)

	def upgradeStats() = {
		this.maxLifePoints += 3
		
		if (this.level % 2 == 0) {
			this.rangeCapacity += 1
			this.initiative += 1
		}

		if (this.level % 4 == 0) {
			this.strength += 1
			this.toughness += 1
		}
	}

	def upgradeAttacks() = {
		Dagger.prob = (Dagger.prob + 0.1).min(1.0)
		Dagger.damage += 1

		FireArrow.prob = (FireArrow.prob + 1.0) / 2
		if (this.level % 10 == 0) {
			Dagger.time += 1
			FireArrow.time += 1
		}

		attacks.foreach(_.damageModifier += 1)
	}
}