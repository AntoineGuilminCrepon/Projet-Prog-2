package witch

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Doom extends Attack {
    override def toString = "Malédiction"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MagicAttack
    override val attackDifficulty = 7
    override val damageModifier = 0

    enemyEffect = Some(new Curse(3, 1, 4))                                                                                
}

object PoisonPotion extends Attack {
    override def toString = "Potion d'empoisonnement"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MagicAttack
    override val attackDifficulty = 1
    override val damageModifier = 0

    enemyEffect = Some(new Poison(5, 1, 2))
}

object BroomHit extends Attack {
    override def toString = "Coup de balai"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 4
    override val damageModifier = 2

    enemyEffect = Some(new Stun(1, 0.5))
}

object NightShade extends Attack {
    override def toString = "Ténèbres"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MagicAttack
    override val attackDifficulty = 7
    override val damageModifier = 4
}

class Witch(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
	override val classIndice = 3
    override def toString = "Sorcière"

    val faction = FactionAlignment.Monster
	val fighterClass = FighterClass.MagicFighter
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