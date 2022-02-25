package archer

import attack._
import attackeffect._
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

    enemyEffect = Some(new Fire(2, 0.7, 2))
}

object Dagger extends Attack {
    override def toString = "Dague"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.MeleeAttack
    override val attackDifficulty = 3
    override val damageModifier = 2

    enemyEffect = Some(new Bleed(2, 0.4, 2))
}

class Archer(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Archer"

    val faction = FactionAlignment.Hero
    var maxLifePoints = 12
    var lifePoints = maxLifePoints
    var meleeCapacity = 3
    var rangeCapacity = 6
    var strength = 5
    var toughness = 4
    var initiative = 9

    override val visual = getClass.getResourceAsStream("/archer.png")

    val attacks = Array(LongBow, ShortBow, FireArrow, Dagger)
}