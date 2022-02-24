package battle

import attack._
import fighter._

import messagedisplay._

trait CurrentState {
    var currentFighterID = 0
}

/* Classe backend principale qui gère les combats et tout ce qui est lié */
class Battle(messagesDispayer : MessagesDisplay, allies : Array[Fighter], enemies : Array[Fighter]) extends CurrentState {
    /* Mettre ce booléen à true tuera tous les ennemis en un coup */
    val debugMode = false

    /* Tableau contenant l'ordre dans lequel les combattants vont attaquer */
    val fightOrder : Array[Fighter] = (allies ++ enemies).sortWith(_.initiative >= _.initiative)

    /* Convertit la position dans l'arène (entre 0 et 5) vers la position dans l'ordre d'attaque */
    def positionToFightOrder(position : Int) : Int = {
        var fighter = if (position % 2 == 0) (allies(position / 2)) else (enemies(position / 2))
        return fightOrder.indexOf(fighter)
    }

    def launchAttack(attackerID : Int, defenderID : Int, attackID : Int) = {
        var attacker = fightOrder(attackerID)
        var defender = fightOrder(defenderID)
        var attack = attacker.attacks(attackID)
        println(attacker + " -> " + defender)
        messagesDispayer.newMessage(attacker + " " + attack.description() + " " + defender + " avec " + attack + ".")
        
        val capacityNeeded = attack.attackType match {
            case AttackType.MeleeAttack => attacker.meleeCapacity
            case AttackType.RangeAttack => attacker.rangeCapacity
        }

        val random = new scala.util.Random
        val hit = random.nextInt(10)
        if ((hit + capacityNeeded >= attack.attackDifficulty && hit > 0) || debugMode) {
            var damages = attacker.fight(defender, attack)  + (if (debugMode) 100 else 0)
            defender.lifePoints -= damages
            messagesDispayer.continueMessage("Il reste " + defender.lifePoints.max(0) + " PV à " + defender + ".")

            if (attack.attackEffect.isDefined && random.nextDouble <= attack.attackEffect.get.probability) {
                messagesDispayer.continueMessage(defender + " est maintenant affecté par : " + attack.attackEffect.get + " !")
                attack.attackEffect.get.effectBeginning(defender)
                defender.effects = attack.attackEffect.get :: defender.effects
            }
        } else {
            messagesDispayer.continueMessage(attacker + " rate son attaque !")
        }

        if (!defender.isLiving()) {
            messagesDispayer.continueMessage(defender + " est mis hors de combat.")
        }   
    }

    /* Permet de définir un défenseur à partir d'un attaquant donné */
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