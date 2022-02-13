package heroes

import fighter._

import swordman._

class Heroes {
    val nbHeroClasses = 1

    def getRandomHero(id : Int) : Fighter = {
        val random = new scala.util.Random
        random.nextInt(nbHeroClasses) match {
            case 0 => new Swordman(id)
        }
    }
}