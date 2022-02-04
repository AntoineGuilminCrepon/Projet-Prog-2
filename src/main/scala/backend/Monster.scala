package monster

import attack._
import fighter._

abstract class Monster extends Fighter {
    override def chooseAttack() : Attack = {
        val random = new scala.util.Random
        val attackID = random.nextInt(4)
        println(attackID)

        return this.attacks(attackID)
    }
}

class Slime extends Monster {
    override val name = "Slime"

    override var lifePoints = 10
    val meleeCapacity = 1
    val rangeCapacity = 1
    val strength = 1
    val toughness = 1

    val attacks = new Array[Attack](4)
    for (i <- 0 to 3) {
        attacks(i) = new Punch
    }
}