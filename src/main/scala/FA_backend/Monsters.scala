package monsters

import scalafx.scene.image._

import fighter._

import slime._
import goblin._
import skavens._
import bandit._
import dragon._
import ghost._
import skeleton._
import witch._

object Monsters {
    val nbMonsterClasses = 8

    def indiceToClass(classIndice : Int, id : Int) : Fighter = {
        return classIndice match {
            case 0 => new Slime(id)
            case 1 => new Goblin(id)
            case 2 => new Skeleton(id)
            case 3 => new Witch(id)
            case 4 => new Dragon(id)
            case 5 => new Skavens(id)
            case 6 => new Ghost(id)
            case 7 => new Bandit(id)
        }
    }

    def getRandomMonster(id : Int) : Fighter = {
        val random = new scala.util.Random
        return indiceToClass(random.nextInt(nbMonsterClasses), id)
    }

	def getThreeRandomMonsters(firstID : Int) : Array[Fighter] = {
		return Array(getRandomMonster(firstID), getRandomMonster(firstID + 1), getRandomMonster(firstID + 2))
	}

    def getThreeRandomUniqueMonsters(firstID : Int) : Array[Fighter] = {
        var classIndices : Array[Int] = scala.util.Random.shuffle(0 to nbMonsterClasses - 1 toList).toArray.slice(0, 3)
        return Array(indiceToClass(classIndices(0), firstID), indiceToClass(classIndices(1), firstID + 1), indiceToClass(classIndices(2), firstID + 2))
    }
}