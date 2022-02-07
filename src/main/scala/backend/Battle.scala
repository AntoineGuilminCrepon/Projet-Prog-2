package battle

import attack._
import fighter._

import messagedisplay._

class Battle(messagesDispayer : MessagesDisplay, allies : Array[Fighter], enemies : Array[Fighter]) {
    val fightOrder : Array[Fighter] = Array(allies(0), enemies(0), allies(1), enemies(1), allies(2), enemies(2))

    def launchAttack(currentFighterID : Int) = {
        var attacking = fightOrder(currentFighterID)
        var defending = fightOrder((currentFighterID + 1) % 6)
        messagesDispayer.newMessage(attacking + " nb " + currentFighterID + " attaque " + defending)
        var damages = attacking.fight(defending, attacking.attacks(0))
        defending.lifePoints -= damages
        messagesDispayer.continueMessage("Il reste " + defending.lifePoints + " PV à " + defending + " nb " + ((currentFighterID + 1)) % 6)
    }

    def getNewFighter(currentFighterID : Int) : Fighter = {
        return fightOrder((currentFighterID + 2) % 6)
    }
}