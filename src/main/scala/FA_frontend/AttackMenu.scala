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

/* Panneau central utilisé pour les objets */
class ItemsPane(attackMenu : AttackMenu, var items : List[Item]) extends javafx.scene.layout.Pane {
	/* Boutons correspondant aux objets */
	class ItemButton(itemsPane : ItemsPane, item : Item) extends Button(item.toString(), new javafx.scene.image.ImageView(new javafx.scene.image.Image(item.imageURL, 300, 200, true, false)) { setPreserveRatio(true) } ) {
		this.setFocusTraversable(false)
		this.setStyle("-fx-text-fill: red; -fx-font-size: 14;")
		this.setContentDisplay(ContentDisplay.TOP)
		this.getTransforms.add(new javafx.scene.transform.Translate() { setY(35) })
		
		this.setOnAction(_ => {
			/* Cette partie ressemble beaucoup aux attaques usuelles */
			/* car les fonctionnements des objets et des attaques sont similaires */
			val currentFighter = attackMenu.battle.fightOrder(attackMenu.battle.currentFighterID)
			val chosenFighterPosition = attackMenu.arena.getAFighter()
			val chosenFighter = attackMenu.battle.fightOrder(attackMenu.battle.positionToFightOrder(chosenFighterPosition))
			if (chosenFighterPosition == -1) {
				attackMenu.messagesDisplayer.newMessage("Choisissez une cible avant d'attaquer ou d'utiliser un objet !")
			} else if (chosenFighter.faction != item.targetAlignment) {
				attackMenu.messagesDisplayer.newMessage("Vous ne pouvez pas choisir ce combattant pour cette action !")
			}

			attackMenu.messagesDisplayer.newMessage(item.use(currentFighter, chosenFighter))
			item.effect.effectBeginning(chosenFighter)
			chosenFighter.effects = item.effect :: chosenFighter.effects
			items = items.filter(_ != item)

			attackMenu.applyEffectsAfterAttacking(currentFighter)
			attackMenu.afterAttack()

			itemsPane.setVisible(false)
		})
	}

	this.setVisible(false)
	this.setPrefSize(1920, 300)
	this.setMinWidth(1920)
	this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px")
	this.setLayoutX(0)
	this.setLayoutY(400)

	var grid = new javafx.scene.layout.GridPane() {
		this.getColumnConstraints().addAll(new ColumnConstraints(60), new ColumnConstraints(250), new ColumnConstraints(100), new ColumnConstraints(300), new ColumnConstraints(100), new ColumnConstraints(300), new ColumnConstraints(100), new ColumnConstraints(300), new ColumnConstraints(100), new ColumnConstraints(250), new ColumnConstraints(60))
		this.setAlignment(Pos.CENTER)
	}

	def updateItemsButtons(currentPage : Int) : Unit = {
		grid.getChildren().clear()

		var leftArrow =
			new javafx.scene.control.Button("", new javafx.scene.image.ImageView(new javafx.scene.image.Image("left_arrow.png", 300, 200, true, false))) {
				this.setPrefSize(300, 200)
				this.setVisible(false)
				this.getTransforms.add(new javafx.scene.transform.Translate() { setY(35) })
				this.setOnAction(_ => updateItemsButtons(currentPage - 1))
			}
		grid.add(leftArrow, 1, 0)

		var rightArrow = 
			new javafx.scene.control.Button("", new javafx.scene.image.ImageView(new javafx.scene.image.Image("left_arrow.png", 300, 200, true, false)) { this.setRotate(180) }) {
				this.setPrefSize(300, 200)
				this.setVisible(false)
				this.getTransforms.add(new javafx.scene.transform.Translate() { setY(35) })
				this.setOnAction(_ => updateItemsButtons(currentPage + 1))
			}
		grid.add(rightArrow, 9, 0)

		val itemsShown = items.slice(currentPage * 3, currentPage * 3 + 3).toArray
		for (i <- 0 to itemsShown.length - 1) {
			grid.add(new ItemButton(this, itemsShown(i)), 3 + 2 * i, 0)
		}

		if (currentPage > 0) {
			leftArrow.setVisible(true)
		}
		if (items.length > (currentPage + 1) * 3) {
			rightArrow.setVisible(true)
		}
	}

	this.getChildren().add(grid)
}

/* Partie correspondant aux boutons du bas de la fenêtre permettant de choisir les attaques */
class AttackMenu(stage : Stage, var battle : Battle, var arena : Arena, var messagesDisplayer : MessagesDisplay, var itemsPane : Option[ItemsPane], var inventory : Inventory) extends GridPane {

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
			battle.fightOrder.filter(hero => hero.isHero()).foreach(hero => {
				val gainedExp = battle.fightOrder.filter(!_.isHero()).foldLeft(0)(_ + _.expRewarded())
				hero.exp += (if (hero.isLiving()) gainedExp else gainedExp / 2)
				if (hero.checkLevelUp()) {
					messagesDisplayer.continueMessage(hero.toString() + " est désormais au niveau " + hero.level + " !")
				}
			})
			val moneyWon = battle.fightOrder.filter(!_.isHero()).foldLeft(0)(_ + _.moneyRewarded())
			inventory.balance += moneyWon
			messagesDisplayer.continueMessage("\nVous avez gagné " + moneyWon + " pièces d'or !")
			messagesDisplayer.continueMessage("Votre compte s'élève désormais à : " + inventory.balance + " pièces d'or !")

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

	def afterAttack() = {
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

	def applyEffectsAfterAttacking(fighter : Fighter) = {
		fighter.effects.foreach({effect =>
			messagesDisplayer.continueMessage(if (effect.effectAfterAttack(fighter) != "") fighter.toString() + effect.effectAfterAttack(fighter) else "")
			effect.timer -= 1
			if (effect.timer <= 0) {
				messagesDisplayer.continueMessage(if (effect.effectEnding(fighter) != "") fighter.toString() + effect.effectEnding(fighter) else "")
			}
		})

		fighter.effects = fighter.effects.filter(
			_.timer > 0
		)
	}

    def setFighterMenu(fighter : Fighter) : Unit = {
		this.getChildren.clear()
		
		itemButton.onAction = _ => {
			itemsPane.get.setVisible(!itemsPane.get.isVisible() && fighter.faction == FactionAlignment.Hero)
			if (itemsPane.get.isVisible()) {
				itemsPane.get.updateItemsButtons(0)
			}
		}

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
				itemsPane.get.setVisible(false)
                battle.fightOrder(battle.currentFighterID).faction match {
                    case FactionAlignment.Hero =>
                        var chosenFighter = arena.getAFighter()
                        if (chosenFighter == -1) {
                            messagesDisplayer.newMessage("Choisissez une cible avant d'attaquer ou d'utiliser un objet")
                            return
                        } else if (battle.fightOrder(battle.positionToFightOrder(chosenFighter)).faction != battle.fightOrder(battle.currentFighterID).attacks(i).targetAlignment) {
                            messagesDisplayer.newMessage("Vous ne pouvez pas cibler ce combattant pour cette action !")
                            return
                        }

                        battle.launchAttack(battle.currentFighterID, battle.positionToFightOrder(chosenFighter), i)
                    case FactionAlignment.Monster =>
						val definedAttack = battle.defineDefender(battle.currentFighterID)
                        battle.launchAttack(battle.currentFighterID, definedAttack._1, definedAttack._2)
                }
                
                messagesDisplayer.continueMessage("")
                applyEffectsAfterAttacking(fighter)

				afterAttack()
            }

            add(b, i%2, i/2)
        }
    }
    
    setFighterMenu(battle.fightOrder(0))
}