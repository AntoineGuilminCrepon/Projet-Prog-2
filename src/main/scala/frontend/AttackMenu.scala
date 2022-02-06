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
import fighter._

class AttackMenu(battle : Battle) extends GridPane {

    /*Contrôle de la taille et position*/
    val w = 645
    val h = 110

    columnConstraints = List(new ColumnConstraints(w), new ColumnConstraints(w))
    rowConstraints = List(new RowConstraints(h), new RowConstraints(h))

    gridLinesVisible = true

    alignment = Pos.BottomCenter

    def setFighterMenu(fighter : Fighter) : Unit = {
        for (i <- 0 to 3) {
            var b = new Button(fighter.attacks(i).toString())
            b.setMinWidth(w)
            b.setMinHeight(h)

            b.onAction = handle {
                battle.launchAttack(fighter, battle.fightOrder(1))
                var newFighter = battle.getNewFighter()
                setFighterMenu(newFighter)
            }

        add(b, i%2, i/2)
        }
    }
    
    val firstFighter = battle.getNewFighter()
    setFighterMenu(firstFighter)
}