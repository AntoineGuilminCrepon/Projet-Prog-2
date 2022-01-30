package fighter

import attack.Attack
import math.rint

abstract class Fighter {
    val name : String

    var lifePoints : Int
    val meleeCapacity : Int
    val rangeCapacity : Int
    val strenght : Int
    val thoughness : Int

    def fight(enemy : Fighter, attack : Attack) : Int = {
        val powerModifier = this.strenght.toFloat / enemy.thoughness
        return (this.strenght * powerModifier * attack.damageModifier).toInt
    }
}