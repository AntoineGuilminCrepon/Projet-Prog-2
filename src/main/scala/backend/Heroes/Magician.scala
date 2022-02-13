package magician

import attack._
import fighter._


object Fireball extends Attack {
    override def toString = "Boule de feu"
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val damageModifier = 2
}

object Thunder extends Attack {
    override def toString = "Foudre"
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val damageModifier = 1
}

object Explosion extends Attack {
    override def toString = "Explosion !"
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val damageModifier = 5
}

object FlashingLight extends Attack {
    override def toString = "Lumière aveuglante"
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val damageModifier = 1
}

class Magician(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Magicien"

    val faction = FactionAlignment.Hero
    val maxLifePoints = 8
    var lifePoints = maxLifePoints
    val meleeCapacity = 1
    val rangeCapacity = 1
    val strength = 1
    val toughness = 1
    val initiative = 1

    override val visual = getClass.getResourceAsStream("/magician.png")

    val attacks = Array(Fireball, Thunder, Explosion, FlashingLight)
}