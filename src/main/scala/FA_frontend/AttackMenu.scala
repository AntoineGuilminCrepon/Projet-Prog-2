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
import scalafx.animation._

import worldmap._
import initworldmap._

import battle._
import arena._
import messagedisplay._
import fighter._
import heroes._
import monsters._
import items._

object ItemsPane extends javafx.scene.layout.Pane {
	this.setPrefSize(1920, 300)
	this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px")
	this.setLayoutX(0)
	this.setLayoutY(400)
}

/* Partie correspondant aux boutons du bas de la fenêtre permettant de choisir les attaques */
class AttackMenu(stage : Stage, battle : Battle, arena : Arena, messagesDisplayer : MessagesDisplay, inventory : (Int, List[Item])) extends GridPane {

    /*Contrôle de la taille et position*/
    val w = 645
    val h = 110

    columnConstraints = List(new ColumnConstraints(w), new ColumnConstraints(w), new ColumnConstraints(100), new ColumnConstraints(300))
    rowConstraints = List(new RowConstraints(h), new RowConstraints(h))

    gridLinesVisible = true

    alignment = Pos.BottomCenter

	var attackButtons : Array[Button] = Array()

	var itemButton = new Button("Objets")
	itemButton.setMinWidth(300)
	itemButton.setMinHeight(150)
	itemButton.onAction = _ => {
		ItemsPane.setVisible(!ItemsPane.isVisible())
	}

	def endMenu(winner : FactionAlignment.EnumVal) : Unit = {
		for (i <- 0 to 5) {
			arena.disableFighter(i)
		}

		if (winner == FactionAlignment.Monster) {
			this.getChildren.clear()
			messagesDisplayer.continueMessage("Souhaitez vous recommencer l'aventure ?")
			attackButtons(2).text = "OUI"
			attackButtons(3).text = "NON"

			attackButtons(2).onAction = _ => {
				stage.close()
				InitWorldMap.isAlreadyDefined = false
				var worldMap = new WorldMap
				worldMap.start(stage)
			}

			attackButtons(3).onAction = _ => {
				stage.close()
			}

			this.add(attackButtons(2), 0, 1)
			this.add(attackButtons(3), 1, 1)
		} else {
			battle.fightOrder.filter(hero => hero.isHero() && hero.isLiving()).foreach(hero => {
				hero.exp += battle.fightOrder.filter(!_.isHero()).foldLeft(0)(_ + _.expRewarded())
				if (hero.checkLevelUp()) {
					messagesDisplayer.continueMessage(hero.toString() + " est désormais au niveau " + hero.level + " !")
				}
			})

			for (i <- 0 to 3) {
				attackButtons(i).text = "Continuer"
				attackButtons(i).onAction = _ => {
					var worldMap = new WorldMap
					worldMap.clearStage()
					worldMap.start(stage)
					return
				}
			}
		}
	}

    def setFighterMenu(fighter : Fighter) : Unit = {
		this.getChildren.clear()
		add(itemButton, 3, 0, 1, 2)
        messagesDisplayer.continueMessage("\nC'est au tour de " + fighter + " d'attaquer.")

        fighter.effects.foreach{
            effect =>
                messagesDisplayer.continueMessage(effect.effectBeforeAttack(fighter))
        }

        fighter.faction match {
            case FactionAlignment.Hero =>
                messagesDisplayer.continueMessage("Choisissez une cible puis une attaque.")
            
            case FactionAlignment.Monster =>
                messagesDisplayer.continueMessage("Appuyez sur \"Continuer\" pour qu'il attaque.")
        }

        attackButtons = new Array[Button](4)

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
                            messagesDisplayer.newMessage("Choisissez une cible avant d'attaquer ou d'utiliser un objet")
                            return
                        } else if (battle.fightOrder(battle.positionToFightOrder(choosenFighter)).faction != battle.fightOrder(battle.currentFighterID).attacks(i).targetAlignment) {
                            messagesDisplayer.newMessage("Vous ne pouvez pas cibler ce combattant pour cette action !")
                            return
                        }

                        battle.launchAttack(battle.currentFighterID, battle.positionToFightOrder(choosenFighter), i)
                    case FactionAlignment.Monster =>
						val definedAttack = battle.defineDefender(battle.currentFighterID)
                        battle.launchAttack(battle.currentFighterID, definedAttack._1, definedAttack._2)
                }
                
                messagesDisplayer.continueMessage("")
                fighter.effects.foreach{
                    effect =>
                        messagesDisplayer.continueMessage(if (effect.effectAfterAttack(fighter) != "") fighter.toString() + effect.effectAfterAttack(fighter) else "")
                        effect.timer -= 1
                        if (effect.timer <= 0) {
                            messagesDisplayer.continueMessage(if (effect.effectEnding(fighter) != "") fighter.toString() + effect.effectEnding(fighter) else "")
                        }
                }

                fighter.effects = fighter.effects.filter(
                    _.timer > 0
                )

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
					endMenu(winner.get)
                }
            }

            add(b, i%2, i/2)
        }
    }
    
    setFighterMenu(battle.fightOrder(0))
}