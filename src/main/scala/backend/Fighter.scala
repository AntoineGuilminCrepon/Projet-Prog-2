package fighter

import java.io.InputStream

import attack.Attack
import math.rint

object FactionAlignment {
    sealed trait EnumVal
    case object Hero extends EnumVal
    case object Monster extends EnumVal
}

abstract class Fighter(id : Int) {
    val fighterID = id
    val faction : FactionAlignment.EnumVal

    var lifePoints : Int
    val meleeCapacity : Int
    val rangeCapacity : Int
    val strength : Int
    val toughness : Int

    val visual : InputStream
    val attacks : Array[Attack]

    /* Renvoie le nombre de dégats infligés par l'attaque */
    def fight(enemy : Fighter, attack : Attack) : Int = {
        val powerModifier = this.strength.toFloat / enemy.toughness
        return (this.strength * powerModifier * attack.damageModifier).toInt
    }
}