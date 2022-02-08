package battle

import attack._
import fighter._

import messagedisplay._

class Battle(messagesDispayer : MessagesDisplay, allies : Array[Fighter], enemies : Array[Fighter]) {
    val fightOrder : Array[Fighter] = Array(allies(0), enemies(0), allies(1), enemies(1), allies(2), enemies(2))

    def launchAttack(attackerID : Int, defenderID : Int) = {
        var attacker = fightOrder(attackerID)
        var defender = fightOrder(defenderID)
        messagesDispayer.newMessage(attacker + " nb " + attackerID + " attaque " + defender)
        var damages = attacker.fight(defender, attacker.attacks(0))
        defender.lifePoints -= damages
        messagesDispayer.continueMessage("Il reste " + defender.lifePoints + " PV à " + defender + " nb " + defenderID)

        if (defender.isLiving()) {
            messagesDispayer.continueMessage("\n" + defender + " riposte et attque " + attacker)
            damages = defender.fight(attacker, defender.attacks(0))
            attacker.lifePoints -= damages
            messagesDispayer.continueMessage("Il lui inflige " + damages + " points de dégats")
            messagesDispayer.continueMessage("Il reste alors " + attacker.lifePoints + " PV à " + attacker + " nb " + attackerID)
        } else {
            messagesDispayer.continueMessage(defender + " est mis hors de combat")
        }
        
        if (!attacker.isLiving()) {
            messagesDispayer.continueMessage(attacker + " est mis hors de combat")
        }
    }

    def getNewFighter(currentFighterID : Int) : Fighter = {
        return fightOrder((currentFighterID + 2) % 6)
    }

    def livingFighters() : Array[Int] = {
        return Array(0, 1, 2, 3, 4, 5).filter(!fightOrder(_).isLiving())
    }
}