package hero

import scala.io._

import scalafx.scene.image._

import attack._
import fighter._

class Swordman(id : Int) extends Fighter(id : Int) {
    override def toString = "Swordman"

    val faction = FactionAlignment.Hero
    var lifePoints = 10
    val meleeCapacity = 1
    val rangeCapacity = 1
    val strength = 1
    val toughness = 1

    override val visual = getClass.getResourceAsStream("/green_square.png")

    val attacks = new Array[Attack](4)
    for (i <- 0 to 3) {
        attacks(i) = new Slash
    }
}