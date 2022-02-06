package battle

import attack._
import fighter._
import messagedisplay._

class Battle(messagesDispayer : MessagesDisplay, allies : Array[Fighter], enemies : Array[Fighter]) {
    def launchAttack(allyID : Int, enemyID : Int) = {
        messagesDispayer.newMessage(allyID + " attacks " + enemyID)
        var damages = allies(allyID).fight(enemies(enemyID), allies(allyID).attacks(0))
        enemies(enemyID).lifePoints -= damages
        messagesDispayer.continueMessage("Il reste " + enemies(enemyID).lifePoints + " PV à " + enemyID)
    }
}