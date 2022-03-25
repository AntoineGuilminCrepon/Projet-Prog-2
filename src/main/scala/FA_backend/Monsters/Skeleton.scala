package skeleton

import attack._
import attackeffect._
import fighter._
import fighterclasses._

object Fear extends Attack {
    override def toString = "Peur"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 1
    override val damageModifier = 1

    enemyEffect = Some(new Stun(1, 0.8))
}

object Slash extends Attack {
    override def toString = "Trancher"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 8
    override val damageModifier = 3

    enemyEffect = Some(new Bleed(2, 0.7, 3))
}

object ShieldHit extends Attack {
    override def toString = "Coup de bouclier"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 10
    override val damageModifier = 4

    enemyEffect = Some(new Stun(2, 0.8))
}

object BoneThrow extends Attack {
    override def toString = "Jet d'os"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 4
    override val damageModifier = 2
}

class Skeleton(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
	override val classIndice = 3
    override def toString = "Squelette"

    val faction = FactionAlignment.Monster
	val fighterClass = FighterClass.MeleeFighter
    var maxLifePoints = 20
    var lifePoints = maxLifePoints
    var meleeCapacity = 5
    var rangeCapacity = 3
    var strength = 4
    var toughness = 2
    var initiative = 1

    override val visual = getClass.getResourceAsStream("/Fighters/skeleton.png")

    val attacks = Array(Fear, Slash, ShieldHit, BoneThrow)
}