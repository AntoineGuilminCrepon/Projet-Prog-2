package dragon

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Fireblast extends Attack {
    override def toString = "Déflagration"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 7
    override val damageModifier = 4

    enemyEffect = Some(new Fire(2, 0.7, 2))
}

object ThorneThrow extends Attack {
    override def toString = "Lancé d'épine"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack                        
    override val attackDifficulty = 5
    override val damageModifier = 3

	enemyEffect = Some(new Bleed(2, 0.7, 2))
}

object Crushing extends Attack {
    override def toString = "Écrasement"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 4
    override val damageModifier = 2

    enemyEffect = Some(new Stun(1, 0.6))
}

object Howling extends Attack {
    override def toString = "Hurlement"
    override val targetAlignment = FactionAlignment.Hero                 /*Could later be relevant as a multi-target attack*/
    override val attackType : AttackType.EnumVal = AttackType.MagicAttack
    override val attackDifficulty = 1
    override val damageModifier = 1

    enemyEffect = Some(new Stun(3, 0.9))
}

class Dragon(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
	override val classIndice = 4
    override def toString = "Dragon"

    val faction = FactionAlignment.Monster
	val fighterClass = FighterClass.RangeFighter
    var maxLifePoints = 25
    var lifePoints = maxLifePoints
    var meleeCapacity = 7
    var rangeCapacity = 7
    var strength = 8
    var toughness = 8
    var initiative = 1

    override val visual = getClass.getResourceAsStream("/Fighters/dragon.png")

    val attacks = Array(Fireblast, ThorneThrow, Crushing, Howling)
}