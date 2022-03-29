package bandit

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object ArrowShower extends Attack {
    override def toString = "Volée de flèches"
    override val targetAlignment = FactionAlignment.Hero                 /*Could later be relevant as a multi-target attack*/
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack                     
    override val attackDifficulty = 3
    override val damageModifier = 3
}

object Mania extends Attack {
    override def toString = "Furie"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack                      
    override val attackDifficulty = 7
    override val damageModifier = 4

    enemyEffect = Some(new Bleed(2, 0.3, 2))
}

object PoisonousArrow extends Attack {
    override def toString = "Flèche empoisonnée"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack                     
    override val attackDifficulty = 3
    override val damageModifier = 2

    enemyEffect = Some(new Poison(3, 0.7, 1))
}

object FireBomb extends Attack {
    override def toString = "Bombe explosive"
    override val targetAlignment = FactionAlignment.Hero                 /*Could later be relevant as a multi-target attack*/
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack                     
    override val attackDifficulty = 5
    override val damageModifier = 4

    enemyEffect = Some(new Stun(1, 0.4))
}

class Bandit(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
	override val classIndice = 7
    override def toString = "Bandit"

    val faction = FactionAlignment.Monster
    val fighterClass = FighterClass.RangeFighter
	var maxLifePoints = 10
	var lifePoints = maxLifePoints
    var meleeCapacity = 4
    var rangeCapacity = 8
    var strength = 3
    var toughness = 3
    var initiative = 3

    override val visual = getClass.getResourceAsStream("/Fighters/bandit.png")

    val attacks = Array(ArrowShower, Mania, PoisonousArrow, FireBomb)
}
