package battle

import attack._
import fighter._

import messagedisplay._

trait CurrentState {
    var currentFighterID = 0
}

/* Classe backend principale qui gère les combats et tout ce qui est lié */
class Battle(messagesDispayer : MessagesDisplay, allies : Array[Fighter], enemies : Array[Fighter]) extends CurrentState {
    val fightOrder : Array[Fighter] = (allies ++ enemies).sortWith(_.initiative >= _.initiative)

    def positionToFightOrder(position : Int) : Int = {
        var fighter = if (position % 2 == 0) (allies(position / 2)) else (enemies(position / 2))
        return fightOrder.indexOf(fighter)
    }

    def launchAttack(attackerID : Int, defenderID : Int) = {
        var attacker = fightOrder(attackerID)
        var defender = fightOrder(defenderID)
        println(attacker + " -> " + defender)
        messagesDispayer.newMessage(attacker + " nb " + attackerID + " attaque " + defender)
        var damages = attacker.fight(defender, attacker.attacks(0))
        defender.lifePoints -= damages
        messagesDispayer.continueMessage("Il reste " + defender.lifePoints + " PV à " + defender + " nb " + defenderID)

        if (!defender.isLiving()) {
            messagesDispayer.continueMessage(defender + " est mis hors de combat")
        }   
    }

    def defineDefender(attackerID : Int) : Int = {
        val attacker = fightOrder(attackerID)
        val defenderFaction = attacker.faction match {
            case FactionAlignment.Hero => 
                return Array(0, 1, 2, 3, 4, 5)
                    .filter(fightOrder(_).isLiving())
                    .filter(!fightOrder(_).isHero())
                    .sortWith(fightOrder(_).lifePoints <= fightOrder(_).lifePoints)(0)
            case FactionAlignment.Monster =>
                return Array(0, 1, 2, 3, 4, 5)
                    .filter(fightOrder(_).isLiving())
                    .filter(fightOrder(_).isHero())
                    .sortWith(fightOrder(_).lifePoints <= fightOrder(_).lifePoints)(0)
        }
        return 0
    }

    def getNewFighter(currentFighterID : Int) : (Int, Fighter) = {
        var newFigherID = (currentFighterID + 1) % 6
        while (!fightOrder(newFigherID).isLiving()) {
            newFigherID = (newFigherID + 1) % 6
        }
        return (newFigherID, fightOrder(newFigherID))
    }

    def deadFighters() : Array[Int] = {
        var deads : Array[Int] = Array()
        for (i <- 0 to 2) {
            if (!allies(i).isLiving()) {
                deads = deads :+ (2 * i) 
            }
            if (!enemies(i).isLiving()) {
                deads = deads :+ (2 * i + 1)
            }
        }
        return deads
    }

    def checkVictory() : Option[FactionAlignment.EnumVal] = {
        var deadHeroCounter = 0
        var deadMonsterCounter = 0
        fightOrder.foreach(
            fighter =>
            if (!fighter.isLiving()) {
                fighter.faction match {
                    case FactionAlignment.Hero => deadHeroCounter += 1
                    case FactionAlignment.Monster => deadMonsterCounter += 1
                }
            }
        )
        if (deadHeroCounter == 3) {
            return Some(FactionAlignment.Monster)
        }

        if (deadMonsterCounter == 3) {
            return Some(FactionAlignment.Hero)
        }

        return None
    }

    def endBattle(winner : FactionAlignment.EnumVal) : Unit = {
        println("Fin du combat")
        messagesDispayer.newMessage("Fin du combat : la victoire revient aux " + winner + " !")
    }
}