package magician

import attack._
import fighter._


object Fireball extends Attack {
    override def toString = "Boule de feu"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 6
    override val damageModifier = 3
}

object Thunder extends Attack {
    override def toString = "Foudre"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 4
    override val damageModifier = 2
}

object Explosion extends Attack {
    override def toString = "Explosion !"
    override val targetAlignment = FactionAlignment.Monster
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 10
    override val damageModifier = 10
}

object Heal extends Attack {
    override def toString = "Soin"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 3
    override val damageModifier = -4
}

class Magician(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Magicien"

    val faction = FactionAlignment.Hero
    val maxLifePoints = 8
    var lifePoints = maxLifePoints
    val meleeCapacity = 2
    val rangeCapacity = 7
    val strength = 3
    val toughness = 3
    val initiative = 3

    override val visual = getClass.getResourceAsStream("/magician.png")

    val attacks = Array(Fireball, Thunder, Explosion, Heal)
}