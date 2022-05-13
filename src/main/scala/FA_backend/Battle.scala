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
        messagesDispayer.newMessage(attacker + " attaque " + defender + " avec " + attack + ".")
        
        val capacityNeeded = attack.attackType match {
            case AttackType.MeleeAttack => attacker.meleeCapacity
            case AttackType.RangeAttack => attacker.rangeCapacity
			case AttackType.MagicAttack => attacker.magicCapacity
        }

        val random = new scala.util.Random
        val hit = random.nextInt(10)
        if ((hit + capacityNeeded >= attack.attackDifficulty && hit > 0) || debugMode) {
            var damages = attacker.fight(defender, attack)  + (if (debugMode) 100 else 0)
            defender.lifePoints -= damages
            defender.lifePoints = if (defender.lifePoints < 0) 0 else if (defender.lifePoints > defender.maxLifePoints) defender.maxLifePoints else defender.lifePoints
            messagesDispayer.continueMessage("Il reste " + defender.lifePoints.max(0) + " PV à " + defender + ".")

            if (defender.isLiving() && attack.enemyEffect.isDefined && random.nextDouble <= attack.enemyEffect.get.probability && !(defender.fighterTypes.foldLeft(false)(_ || attack.enemyEffect.get.immuneTypes.contains(_)))) {
                messagesDispayer.continueMessage(defender + " est maintenant affecté par : " + attack.enemyEffect.get + " !")
                attack.enemyEffect.get.effectBeginning(defender)

                defender.effects = attack.enemyEffect.get :: defender.effects
            }

            if (attack.allyEffect.isDefined && random.nextDouble <= attack.allyEffect.get.probability) {
                messagesDispayer.continueMessage(attacker + " est maintenant affecté par : " + attack.allyEffect.get + " !")
                attack.enemyEffect.get.effectBeginning(attacker)
                
                attacker.effects = attack.allyEffect.get :: attacker.effects
            }

        } else {
            messagesDispayer.continueMessage(attacker + " rate son attaque !")
        }

        if (!defender.isLiving()) {
            messagesDispayer.continueMessage(defender + " est mis hors de combat.")
        }   
    }

    /* Permet de définir un défenseur à partir d'un attaquant donné */
    def defineDefender(attackerID : Int) : (Int, Int) = {
        val attacker = fightOrder(attackerID)
        val defenderFaction = attacker.faction match {
            case FactionAlignment.Hero => return (-1, -1)
            case FactionAlignment.Monster =>
				
				/* On teste ici toutes les attaques et le monstre choisira l'attaque et le héros auquel il fera le plus de dommages */
				var defenderIDMax = 0
				var attackIDMax = 0
				var damagesMax = 0
				for (i <- 0 to 5) {
					if (fightOrder(i).isHero() && fightOrder(i).isLiving()) {
						for (j <- 0 to 3) {
							/* Si le monstre a la possibilité de tuer immédiatement un héros, il le fait. */
							if (attacker.fight(fightOrder(i), attacker.attacks(j)) >= fightOrder(i).lifePoints) {
								return (i, j)
							}

							if (attacker.fight(fightOrder(i), attacker.attacks(j)) 
								+ (if (attacker.attacks(j).enemyEffect.isDefined) attacker.attacks(j).enemyEffect.get.expectedDamages else 0) >= damagesMax) {
									damagesMax = attacker.fight(fightOrder(i), attacker.attacks(j)) 
										+ (if (attacker.attacks(j).enemyEffect.isDefined) attacker.attacks(j).enemyEffect.get.expectedDamages else 0)
									defenderIDMax = i
									attackIDMax = j
							}
						}
					}
				}
				return (defenderIDMax, attackIDMax)
        }
		return (-1, -1)
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