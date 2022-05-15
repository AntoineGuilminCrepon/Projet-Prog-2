package skeleton

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Fear extends Attack("Peur", FactionAlignment.Hero, AttackType.MagicAttack, attackDifficulty = 1, damageModifier = 1) {
	var time : Int = 1
	var prob : Double = 0.8

	override def updateEffects() = {
		enemyEffect = Some(new Stun(time, prob))
	}

	updateEffects()
}

object Slash extends Attack("Trancher", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 8, damageModifier = 3) {
	var time : Int = 2
	var prob : Double = 0.7
	var damage : Int = 3

	override def updateEffects() = {
		enemyEffect = Some(new Bleed(time, prob, damage))
	}

	updateEffects()
}

object ShieldHit extends Attack("Coup de bouclier", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 10, damageModifier = 4) {
	var time : Int = 2
	var prob : Double = 0.8

	override def updateEffects() = {
		enemyEffect = Some(new Stun(time, prob))
	}

	updateEffects()
}

object BoneThrow extends Attack("Jet d'os", FactionAlignment.Hero, AttackType.RangeAttack, attackDifficulty = 4, damageModifier = 2)

class Skeleton(fighterID : Int) extends Fighter(fighterID, 2, FactionAlignment.Monster, FighterClass.MeleeFighter) {
    override def toString = "Squelette"
	override val fighterTypes = Array(FighterType.Undead)

    var maxLifePoints = 20
    var lifePoints = maxLifePoints
    var meleeCapacity = 5
    var rangeCapacity = 3
    var strength = 4
    var toughness = 2
    var initiative = 1

    override val visual = getClass.getResourceAsStream("/Fighters/skeleton.png")

    val attacks = Array(Fear, Slash, ShieldHit, BoneThrow)

	def upgradeStats() = {
		this.maxLifePoints += 4
		
		if (this.level % 2 == 0) {
			this.meleeCapacity += 1
		}

		if (this.level % 4 == 0) {
			this.strength += 1
			this.toughness += 1
		}

		if (this.level % 9 == 0) {
			this.initiative += 1
		}
	}

	def upgradeAttacks() = {
		ShieldHit.prob = (ShieldHit.prob + 1.0) / 2.0
		Slash.prob = (Slash.prob + 1.0) / 2.0
		Fear.prob = 1

		if (this.level % 10 == 0) {
			ShieldHit.time += 1
			Slash.time += 1
			Fear.time += 1

			Slash.damage += 1
		}

		attacks.foreach(_.damageModifier += 1)
	}
}