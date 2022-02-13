package heroes

import fighter._

import swordman._
import magician._

class Heroes {
    val nbHeroClasses = 2

    def getRandomHero(id : Int) : Fighter = {
        val random = new scala.util.Random
        random.nextInt(nbHeroClasses) match {
            case 0 => new Swordman(id)
            case 1 => new Magician(id)
        }
    }
}