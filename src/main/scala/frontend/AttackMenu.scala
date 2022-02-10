package attackmenu

import scalafx._
import scalafx.collections._
import scalafx.scene.image._
import scalafx.scene.layout._
import scalafx.scene.control._
import scalafx.geometry._
import scalafx.scene._
import scalafx.Includes._
import scalafx.event.ActionEvent

import battle._
import arena._
import fighter._

trait CurrentState {
    var currentFighterID = 0
}

class AttackMenu(battle : Battle, arena : Arena) extends GridPane with CurrentState {

    /*Contrôle de la taille et position*/
    val w = 645
    val h = 110

    columnConstraints = List(new ColumnConstraints(w), new ColumnConstraints(w))
    rowConstraints = List(new RowConstraints(h), new RowConstraints(h))

    gridLinesVisible = true

    alignment = Pos.BottomCenter

    def setFighterMenu(fighter : Fighter) : Unit = {
        for (i <- 0 to 3) {
            var b = new Button(
                fighter.faction match {
                    case FactionAlignment.Hero => fighter.attacks(i).toString()
                    case FactionAlignment.Monster => "Continuer"
                }
            )
            b.setMinWidth(w)
            b.setMinHeight(h)

            b.onAction = handle {
                var newFighter = fighter
                var winner : Option[FactionAlignment.EnumVal] = None

                battle.launchAttack(currentFighterID, battle.defineDefender(currentFighterID))
                    
                var deadFighters = battle.deadFighters()
                deadFighters.foreach(i => arena.children(i+1) = new Label)

                var gettingNewFighter = battle.getNewFighter(currentFighterID)
                newFighter = gettingNewFighter._2
                currentFighterID = gettingNewFighter._1

                winner = battle.checkVictory()
                
                if (!winner.isDefined) {
                    setFighterMenu(newFighter)
                } else {
                    battle.endBattle(winner.get)
                }
            }

        add(b, i%2, i/2)
        }
    }
    
    val firstFighter = battle.getNewFighter(0)._2
    setFighterMenu(firstFighter)
}