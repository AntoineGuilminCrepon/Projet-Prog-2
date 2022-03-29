package ghost

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Fear extends Attack {
    override def toString = "Terreur"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MagicAttack
    override val attackDifficulty = 1
    override val damageModifier = 0

    enemyEffect = Some(new Stun(3, 1))
}

object BloodyCurse extends Attack {
    override def toString = "Saignée"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MagicAttack
    override val attackDifficulty = 3
    override val damageModifier = 1

    enemyEffect = Some(new Bleed(3, 1, 2))
}

object Intimidation extends Attack {
    override def toString = "Intimidation"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MagicAttack
    override val attackDifficulty = 3
    override val damageModifier = 1

    enemyEffect = Some(new CapacitiesDebuf(3, 1, 3, 3))
}

object Tornado extends Attack {
    override def toString = "Tornade"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MagicAttack
    override val attackDifficulty = 4
    override val damageModifier = 2
}

class Ghost(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
	override val classIndice = 6
    override def toString = "Fantôme"

    val faction = FactionAlignment.Monster
	val fighterClass = FighterClass.MagicFighter
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
