package monsters

import scalafx.scene.image._

import attack._
import fighter._

import slime._
import goblin._
import skeleton._

object Monsters {
    val nbMonsterClasses = 3

    def indiceToClass(classIndice : Int, id : Int) : Fighter = {
        return classIndice match {
            case 0 => new Slime(id)
            case 1 => new Goblin(id)
            case 2 => new Skeleton(id)
        }
    }

    def getRandomMonster(id : Int) : Fighter = {
        val random = new scala.util.Random
        return indiceToClass(random.nextInt(nbMonsterClasses), id)
    }

    def getThreeRandomUniqueMonsters(firstID : Int) : Array[Fighter] = {
        var classIndices : Array[Int] = scala.util.Random.shuffle(0 to nbMonsterClasses - 1 toList).toArray.slice(0, 3)
        return Array(indiceToClass(classIndices(0), firstID), indiceToClass(classIndices(1), firstID + 1), indiceToClass(classIndices(2), firstID + 2))
    }
}