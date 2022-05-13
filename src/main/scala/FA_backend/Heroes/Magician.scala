package magician

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Fireball extends Attack("Boule de feu", FactionAlignment.Monster, AttackType.MagicAttack, attackDifficulty = 7, damageModifier = 3) {
	var time : Int = 2
	var prob : Double = 0.7
	var damage : Int = 2

	override def updateEffects() = {
		enemyEffect = Some(new Fire(time, prob, damage))
	}

	updateEffects()
}

object Thunder extends Attack("Foudre", FactionAlignment.Monster, AttackType.MagicAttack, attackDifficulty = 6, damageModifier = 2) {
	var time : Int = 1
	var prob : Double = 0.5

	override def updateEffects() = {
		enemyEffect = Some(new Stun(time, prob))
	}

	updateEffects()
}

object Explosion extends Attack("Explosion !", FactionAlignment.Monster, AttackType.MagicAttack, attackDifficulty = 12, damageModifier = 8) {
	var stunTime : Int = 2
	var stunProb : Double = 1.0

	var fireTime : Int = 3
	var fireProb : Double = 0.8
	var fireDamage : Int = 3

	override def updateEffects() = {
		allyEffect = Some(new Stun(stunTime, stunProb))
    	enemyEffect = Some(new Fire(fireTime, fireProb, fireDamage))
	}

}

object Heal extends Attack("Soin", FactionAlignment.Monster, AttackType.MagicAttack, attackDifficulty = 4, damageModifier = -9)

class Magician(fighterID : Int) extends Fighter(fighterID, 1, FactionAlignment.Hero, FighterClass.MagicFighter) {
    override def toString = "Magicien"

    var maxLifePoints = 8
    var lifePoints = maxLifePoints
    var meleeCapacity = 2
    var rangeCapacity = 3
	magicCapacity = 7
    var strength = 3
    var toughness = 3
    var initiative = 3

    override val visual = getClass.getResourceAsStream("/Fighters/magician.png")

    val attacks = Array(Fireball, Thunder, Explosion, Heal)
}