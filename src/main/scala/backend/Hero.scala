package hero

import scala.io._

import scalafx.scene.image._

import attack._
import fighter._

class Swordman(id : Int) extends Fighter(id : Int) {
    override val fighterID = id
    override def toString = "Swordman"

    val faction = FactionAlignment.Hero
    val maxLifePoints = 10
    var lifePoints = maxLifePoints
    val meleeCapacity = 1
    val rangeCapacity = 1
    val strength = 1
    val toughness = 1
    val initiative = 2

    override val visual = getClass.getResourceAsStream("/green_square.png")

    val attacks = Array(new Punch, new Slash, new Thrust, new PommelHit)
}