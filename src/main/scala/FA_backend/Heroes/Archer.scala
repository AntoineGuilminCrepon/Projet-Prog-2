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
}