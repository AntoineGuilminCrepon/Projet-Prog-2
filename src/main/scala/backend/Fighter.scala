package fighter

import attack.Attack
import math.rint

abstract class Fighter {
    val name : String

    var lifePoints : Int
    val meleeCapacity : Int
    val rangeCapacity : Int
    val strength : Int
    val toughness : Int

    /* Renvoie le nombre de dégats infligés par l'attaque */
    def fight(enemy : Fighter, attack : Attack) : Int = {
        val powerModifier = this.strength.toFloat / enemy.toughness
        return (this.strength * powerModifier * attack.damageModifier).toInt
    }
}