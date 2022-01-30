package fighter

import attack.Attack

abstract class Fighter {
    val name : String

    var lifePoints : Int
    val meleeCapacity : Int
    val rangeCapacity : Int
    val strenght : Int
    val thoughness : Int

    def fight(enemy : Fighter, attack : Attack) : Int = {
        val powerModifier = if (enemy.thoughness >= this.strenght) 1 else this.strenght / enemy.thoughness
        return this.strenght * powerModifier * attack.damageModifier
    }
}