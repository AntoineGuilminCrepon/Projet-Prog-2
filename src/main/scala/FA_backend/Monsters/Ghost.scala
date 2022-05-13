package ghost

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Fear extends Attack("Terreur", FactionAlignment.Hero, AttackType.MagicAttack, attackDifficulty = 1, damageModifier = 0) {
	var time : Int = 3
	var prob : Double = 1

	override def updateEffects() = {
		enemyEffect = Some(new Stun(time, prob))
	}

	updateEffects()
}

object BloodyCurse extends Attack("Malédiction de sang", FactionAlignment.Hero, AttackType.MagicAttack, attackDifficulty = 3, damageModifier = 1) {
	var time : Int = 3
	var prob : Double = 1
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Bleed(time, prob, damage))
	}

	updateEffects()
}

object Intimidation extends Attack("Intimidation", FactionAlignment.Hero, AttackType.MagicAttack, attackDifficulty = 3, damageModifier = 1) {
	var time : Int = 3
	var prob : Double = 1
	var ccDebuf : Int = 3
	var ctDebuf : Int = 3

	override def updateEffects() = {
		enemyEffect = Some(new CapacitiesDebuf(time, prob, ccDebuf, ctDebuf))
	}

	updateEffects()
}

object Tornado extends Attack("Tornade", FactionAlignment.Hero, AttackType.MagicAttack, attackDifficulty = 4, damageModifier = 2)

class Ghost(fighterID : Int) extends Fighter(fighterID, 6, FactionAlignment.Monster, FighterClass.MagicFighter) {
    override def toString = "Fantôme"
	override val fighterTypes = Array(FighterType.NonPhysical)

    var maxLifePoints = 7
    var lifePoints = maxLifePoints
    var meleeCapacity = 2
    var rangeCapacity = 6
    var strength = 3
    var toughness = 3
    var initiative = 3

    override val visual = getClass.getResourceAsStream("/Fighters/ghost.png")

    val attacks = Array(Fear, BloodyCurse, Intimidation, Tornado)
}
