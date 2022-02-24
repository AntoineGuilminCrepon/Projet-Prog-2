package attackmenu

import javafx.application._
import javafx.stage._

import scalafx._
import scalafx.collections._
import scalafx.scene.image._
import scalafx.scene.layout._
import scalafx.scene.control._
import scalafx.geometry._
import scalafx.scene._
import scalafx.Includes._
import scalafx.event.ActionEvent

import initfightarena._
import battle._
import arena._
import messagedisplay._
import fighter._
import heroes._
import monsters._

/* Partie correspondant aux boutons du bas de la fenêtre permettant de choisir les attaques */
class AttackMenu(stage : Stage, battle : Battle, arena : Arena, messagesDispayer : MessagesDisplay) extends GridPane with InitFightArena {

    /*Contrôle de la taille et position*/
    val w = 645
    val h = 110

    columnConstraints = List(new ColumnConstraints(w), new ColumnConstraints(w))
    rowConstraints = List(new RowConstraints(h), new RowConstraints(h))

    gridLinesVisible = true

    alignment = Pos.BottomCenter

    def setFighterMenu(fighter : Fighter) : Unit = {
        messagesDispayer.continueMessage("\nC'est au tour de " + fighter + " d'attaquer.")

        fighter.effects.foreach{
            effect =>
                messagesDispayer.continueMessage(effect.effectEachTurn(fighter))
                effect.timer -= 1
                if (effect.timer == 0) {
                    effect.effectEnding(fighter)
                }
        }

        fighter.effects = fighter.effects.filter(
            _.timer > 0
        )

        fighter.faction match {
            case FactionAlignment.Hero =>
                messagesDispayer.continueMessage("Choisissez une cible puis une attaque.")
            
            case FactionAlignment.Monster =>
                messagesDispayer.continueMessage("Appuyez sur \"Continuer\" pour qu'il attaque.")
        }

        var attackButtons = new Array[Button](4)

        for (i <- 0 to 3) {
            var b = new Button(
                fighter.faction match {
                    case FactionAlignment.Hero => fighter.attacks(i).toString()
                    case FactionAlignment.Monster => "Continuer"
                }
            )

            attackButtons(i) = b

            b.setMinWidth(w)
            b.setMinHeight(h)

            b.onAction = _ => {
                battle.fightOrder(battle.currentFighterID).faction match {
                    case FactionAlignment.Hero =>
                        var choosenFighter = arena.getAFighter()
                        if (choosenFighter == -1) {
                            messagesDispayer.newMessage("Choisissez une cible avant d'attaquer")
                            return
                        } else if (battle.fightOrder(battle.positionToFightOrder(choosenFighter)).faction != battle.fightOrder(battle.currentFighterID).attacks(i).targetAlignment) {
                            messagesDispayer.newMessage("Vous ne pouvez pas cibler ce combattant pour cette action !")
                            return
                        }

                        battle.launchAttack(battle.currentFighterID, battle.positionToFightOrder(choosenFighter), i)
                    case FactionAlignment.Monster =>
                        val random = new scala.util.Random
                        battle.launchAttack(battle.currentFighterID, battle.defineDefender(battle.currentFighterID), random.nextInt(4))
                    
                }
                
                for (i <- 0 to 5) {
                    arena.updateLifePoints(i)
                }
                    
                var deadFighters = battle.deadFighters()
                deadFighters.foreach(i => arena.killFighter(i))

                var gettingNewFighter = battle.getNewFighter(battle.currentFighterID)
                var newFighter = gettingNewFighter._2
                battle.currentFighterID = gettingNewFighter._1

                var winner = battle.checkVictory()
                
                if (!winner.isDefined) {
                    setFighterMenu(newFighter)
                } else {
                    battle.endBattle(winner.get)
                    for (i <- 0 to 5) {
                        arena.disableFighter(i)
                    }

                    for (i <- 0 to 1) {
                        attackButtons(i).text = "Recommencer ?"
                        attackButtons(i).onAction = _ => {
                            messagesDispayer.newMessage("Veuillez choisir une réponse ci-dessous ^^")
                        }
                    }

                    attackButtons(2).text = "OUI"
                    attackButtons(3).text = "NON"

                    attackButtons(2).onAction = _ => {
                        val allies = Heroes.getThreeRandomUniqueHeroes(0)
                        val enemies = Monsters.getThreeRandomUniqueMonsters(3)
                        restart(stage, allies, enemies)
                    }

                    attackButtons(3).onAction = _ => {
                        stage.close()
                    }
                }
            }

            add(b, i%2, i/2)
        }
    }
    
    setFighterMenu(battle.fightOrder(0))
}