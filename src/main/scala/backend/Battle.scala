package battle

import attack._
import fighter._

import messagedisplay._

class Battle(messagesDispayer : MessagesDisplay, allies : Array[Fighter], enemies : Array[Fighter]) {
    var currentFighterID : Option[Int] = None

    val fightOrder : Array[Fighter] = Array(allies(0), enemies(0), allies(1), enemies(1), allies(2), enemies(2))

    def launchAttack(ally : Fighter, enemy : Fighter) = {
        messagesDispayer.newMessage(ally + " nb " + currentFighterID.get + " attacks " + enemy)
        var damages = ally.fight(enemy, ally.attacks(0))
        enemy.lifePoints -= damages
        messagesDispayer.continueMessage("Il reste " + enemy.lifePoints + " PV à " + enemy)
    }

    def getNewFighter() : Fighter = {
        if (!currentFighterID.isDefined) {
            currentFighterID = Some(0)
        } else {
            currentFighterID = Some((currentFighterID.get + 2) % 6)
        }
        return fightOrder(currentFighterID.get)
    }
}