package battle

import attack._
import fighter._

import messagedisplay._

class Battle(messagesDispayer : MessagesDisplay, allies : Array[Fighter], enemies : Array[Fighter]) {
    val fightOrder : Array[Fighter] = (allies ++ enemies).sortWith(_.initiative >= _.initiative)

    def launchAttack(attackerID : Int, defenderID : Int) = {
        var attacker = fightOrder(attackerID)
        var defender = fightOrder(defenderID)
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
            case FactionAlignment.Hero => FactionAlignment.Monster
            case FactionAlignment.Monster => FactionAlignment.Hero
        }
        return Array(0, 1, 2, 3, 4, 5).filter(fightOrder(_).isLiving()).sortWith(fightOrder(_).lifePoints <= fightOrder(_).lifePoints)(0)
    }

    def getNewFighter(currentFighterID : Int) : (Int, Fighter) = {
        return ((currentFighterID + 1) % 6, fightOrder((currentFighterID + 1) % 6))
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