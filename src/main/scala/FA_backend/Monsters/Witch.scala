package witch

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Doom extends Attack("Malédiction", FactionAlignment.Hero, AttackType.MagicAttack, attackDifficulty = 7, damageModifier = 0) {
	var time : Int = 3
	var prob : Double = 0.8
	var damage : Int = 4

	override def updateEffects() = {
		enemyEffect = Some(new Curse(time, prob, damage))
	}

	updateEffects()
}

object PoisonPotion extends Attack("Potion d'empoisonnement", FactionAlignment.Hero, AttackType.MagicAttack, attackDifficulty = 1, damageModifier = 0) {
	var time : Int = 4
	var prob : Double = 0.6
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Poison(time, prob, damage))
	}

	updateEffects()
}

object BroomHit extends Attack("Coup de balai", FactionAlignment.Hero, AttackType.MeleeAttack, attackDifficulty = 4, damageModifier = 2) {
	var time : Int = 1
	var prob : Double = 0.5

	override def updateEffects() = {
		enemyEffect = Some(new Stun(time, prob))
	}

	updateEffects()
}

object NightShade extends Attack("Ténèbres", FactionAlignment.Hero, AttackType.MagicAttack, attackDifficulty = 7, damageModifier = 4)

class Witch(fighterID : Int) extends Fighter(fighterID, 3, FactionAlignment.Monster, FighterClass.MagicFighter) {
    override def toString = "Sorcière"

    var maxLifePoints = 10
    var lifePoints = maxLifePoints
    var meleeCapacity = 2
    var rangeCapacity = 7
    var strength = 3
    var toughness = 3
    var initiative = 3

    override val visual = getClass.getResourceAsStream("/Fighters/witch.png")

    val attacks = Array(Doom, PoisonPotion, BroomHit, NightShade)
}