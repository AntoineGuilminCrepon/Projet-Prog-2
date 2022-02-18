package archer

import attack._
import fighter._


object LongBow extends Attack {
    override def toString = "Arc long"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 7
    override val damageModifier = 3
}

object ShortBow extends Attack {
    override def toString = "Arc court"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 4
    override val damageModifier = 2
}

object FireArrow extends Attack {
    override def toString = "Flèche enflammée"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 2
    override val damageModifier = 1
}

object Dagger extends Attack {
    override def toString = "Dague"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 3
    override val damageModifier = 1
}

class Archer(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Archer"

    val faction = FactionAlignment.Hero
    val maxLifePoints = 12
    var lifePoints = maxLifePoints
    val meleeCapacity = 3
    val rangeCapacity = 6
    val strength = 5
    val toughness = 4
    val initiative = 9

    override val visual = getClass.getResourceAsStream("/archer.png")

    val attacks = Array(LongBow, ShortBow, FireArrow, Dagger)
}