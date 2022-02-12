package arena

import scalafx._
import scalafx.collections._
import scalafx.scene.image._
import scalafx.scene.layout._
import scalafx.scene.control._
import scalafx.scene.text._
import scalafx.geometry._
import scalafx.scene._
import scalafx.Includes._
import scalafx.event.ActionEvent

import battle._
import messagedisplay._
import fighter._

trait ChoosenFighter {
    var choosenFighter = -1
}

class FighterDescription(fighter : Fighter) extends Label {
    textAlignment = TextAlignment.Center
    translateX = 180

    this.text = fighter.toString() + "\nPV : " + fighter.lifePoints + "/" + fighter.maxLifePoints + "\n"

    def updateLifePoints() : Unit = {
        this.text = fighter.toString() + "\nPV : " + fighter.lifePoints + "/" + fighter.maxLifePoints + "\n"
    }

    def setStatus(status : String) : Unit = {
        this.text = fighter.toString() + "\nPV : " + fighter.lifePoints + "/" + fighter.maxLifePoints + "\n" + status
    }
}

/* Partie correspondant aux visuels des combattants */
class Arena(battle : Battle, messagesDispayer : MessagesDisplay, allies : Array[Fighter], enemies : Array[Fighter]) extends GridPane with ChoosenFighter {

    /*Contrôle de la taille et position*/
    val w = 430
    val h = 250
    val hText = 70

    columnConstraints = List(new ColumnConstraints(w), new ColumnConstraints(w), new ColumnConstraints(w))
    rowConstraints = List(new RowConstraints(h), new RowConstraints(hText), new RowConstraints(h), new RowConstraints(hText))

    gridLinesVisible = true

    alignment = Pos.TopCenter

    var fighterButtons = new Array[Button](6)
    var fighterDescriptions = new Array[FighterDescription](6)

    for (i <- 0 to 2) {
        var imageAllies = new Image(allies(i).visual, w, h-10, true, false)
        var ivAllies = new ImageView(imageAllies)
        var button = new Button(){
                graphic = ivAllies
                translateX = (w - imageAllies.width.toInt)/ 2
                this.onAction = _ => {
                    battle.fightOrder(battle.currentFighterID).faction match {
                        case FactionAlignment.Hero =>
                            choosenFighter = 2 * i
                            messagesDispayer.newMessage("Vous allez attaquer " + battle.fightOrder(battle.positionToFightOrder(2 * i)))
                            messagesDispayer.continueMessage("Choisissez maintenant une attaque.")
                        
                        case FactionAlignment.Monster => 
                    }
                }
            }
        add(button, (i%3), 2)
        fighterButtons(2 * i) = button

        var fighterDescription = new FighterDescription(allies(i))
        fighterDescriptions(2 * i) = fighterDescription
        add(fighterDescription, (i%3), 3)

        var imageEnemies = new Image(enemies(i).visual, w, h-10, true, false)
        var ivEnemies = new ImageView(imageEnemies)
        button = new Button(){
                graphic = ivEnemies
                translateX = (w - imageEnemies.width.toInt)/ 2                
                this.onAction = _ => {
                    battle.fightOrder(battle.currentFighterID).faction match {
                        case FactionAlignment.Hero =>
                            choosenFighter = 2 * i + 1
                            messagesDispayer.newMessage("Vous allez attaquer " + battle.fightOrder(battle.positionToFightOrder(2 * i + 1)))
                            messagesDispayer.continueMessage("Choisissez maintenant une attaque.")
                        
                        case FactionAlignment.Monster => 
                    }
                }
            }
        add(button, (i%3), 0)
        fighterButtons(2 * i + 1) = button

        fighterDescription = new FighterDescription(enemies(i))
        fighterDescriptions(2 * i + 1) = fighterDescription
        add(fighterDescription, (i%3), 1)

    }

    def getAFighter() : Int = {
        val raz = choosenFighter
        choosenFighter = -1
        return raz
    }

    def fighterIDToChildID(fighterID : Int) : Int = {
        return 2 * fighterID + 1        
    }

    def killFighter(fighterID : Int) : Unit = {
        this.fighterButtons(fighterID).graphic = null
        this.fighterDescriptions(fighterID).setStatus("MORT")
    }

    def updateLifePoints(fighterID : Int) : Unit = {
        this.fighterDescriptions(fighterID).updateLifePoints()
    }
}

/* 
    On peut obtenir les enfants de Arena avec children(i), où i est :
    2   4   6
    1   3   5
    et 0 est la grille visible.
*/