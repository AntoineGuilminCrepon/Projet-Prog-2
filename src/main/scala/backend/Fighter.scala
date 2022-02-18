package fighter

import java.io.InputStream

import attack._
import math.rint

object FactionAlignment {
    sealed trait EnumVal
    case object Hero extends EnumVal {override def toString() : String = {"Héros"}}
    case object Monster extends EnumVal {override def toString() : String = {"Monstres"}}
}

abstract class Fighter(id : Int) {
    val fighterID = id
    val faction : FactionAlignment.EnumVal

    val maxLifePoints : Int
    var lifePoints : Int

    val meleeCapacity : Int
    val rangeCapacity : Int
    val strength : Int
    val toughness : Int
    val initiative : Int

    val visual : InputStream
    val attacks : Array[Attack]

    var effects : List[AttackEffect] = List()

    /* Renvoie un booléen correspond pour savoir si le combattant est en vie */
    def isLiving() : Boolean = {
        return this.lifePoints > 0
    }

    /* Renvoie vrai si le combattant est un Héros */
    def isHero() : Boolean = {
        this.faction match {
            case FactionAlignment.Hero => true
            case FactionAlignment.Monster => false
        }
    }

    /* Renvoie le nombre de dégats infligés par l'attaque */
    def fight(enemy : Fighter, attack : Attack) : Int = {
        return (this.strength * attack.damageModifier / enemy.toughness.toFloat).toInt
    }
}