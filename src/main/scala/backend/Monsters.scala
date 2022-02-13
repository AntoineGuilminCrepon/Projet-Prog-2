package monsters

import scalafx.scene.image._

import attack._
import fighter._

import slime._

class Monsters {
    val nbMonsterClasses = 1

    def getRandomMonster(id : Int) : Fighter = {
        val random = new scala.util.Random
        random.nextInt(nbMonsterClasses) match {
            case 0 => new Slime(id)
        }
    }
}