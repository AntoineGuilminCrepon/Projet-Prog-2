package monster

import scalafx.scene.image._

import attack._
import fighter._

class Slime(id : Int) extends Fighter(id : Int) {
    override def toString = "Slime"

    val faction = FactionAlignment.Monster
    var lifePoints = 10
    val meleeCapacity = 1
    val rangeCapacity = 1
    val strength = 1
    val toughness = 1
    
    val visual = getClass.getResourceAsStream("/red_square.png")

    val attacks = new Array[Attack](4)
    for (i <- 0 to 3) {
        attacks(i) = new Punch
    }
}