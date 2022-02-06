package battle

import attack._
import fighter._

class Battle(allies : Array[Fighter], enemies : Array[Fighter]) {
    def launchAttack(allyID : Int, enemyID : Int) = {
        println(allyID + " attacks " + enemyID)
        var damages = allies(allyID).fight(enemies(enemyID), allies(allyID).attacks(0))
        enemies(enemyID).lifePoints -= damages
        println("Il reste " + enemies(enemyID).lifePoints + " PV à " + enemyID)
    }
}