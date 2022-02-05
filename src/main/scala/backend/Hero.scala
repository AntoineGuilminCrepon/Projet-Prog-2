package hero

import scala.io._

import scalafx.scene.image._

import attack._
import fighter._

abstract class Hero extends Fighter {

}

class Swordman extends Hero {
    override val id = 2
    override val visual = getClass.getResourceAsStream("src/main/resources/red_square.png")
    override def toString = "Swordman"

    override var lifePoints = 10
    val meleeCapacity = 1
    val rangeCapacity = 1
    val strength = 1
    val toughness = 1

    val attacks = new Array[Attack](4)
    for (i <- 0 to 3) {
        attacks(i) = new Slash
    }
}