package heroes

import fighter._

import swordman._
import magician._
import archer._

object Heroes {
    val nbHeroClasses = 3

    def indiceToClass(classIndice : Int, id : Int) : Fighter = {
        return classIndice match {
            case 0 => new Swordman(id)
            case 1 => new Magician(id)
            case 2 => new Archer(id)
        }
    }

    def getRandomHero(id : Int) : Fighter = {
        val random = new scala.util.Random
        return indiceToClass(random.nextInt(nbHeroClasses), id)
    }

	def getThreeRandomHeroes(firstID : Int) : Array[Fighter] = {
		return Array(getRandomHero(firstID), getRandomHero(firstID + 1), getRandomHero(firstID + 2))
	}

    def getThreeRandomUniqueHeroes(firstID : Int) : Array[Fighter] = {
        var classIndices : Array[Int] = scala.util.Random.shuffle(0 to nbHeroClasses - 1 toList).toArray.slice(0,3)
        return Array(indiceToClass(classIndices(0), firstID), indiceToClass(classIndices(1), firstID + 1), indiceToClass(classIndices(2), firstID + 2))
    }
}