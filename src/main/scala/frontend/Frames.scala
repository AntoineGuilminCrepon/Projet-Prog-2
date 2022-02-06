package frames

import scalafx._
import scalafx.collections._
import scalafx.scene.image._
import scalafx.scene.layout._
import scalafx.scene.control._
import scalafx.geometry._
import scalafx.scene._
import scalafx.Includes._
import scalafx.event.ActionEvent

import hero._
import monster._

class AttackButtons(avatar : Hero) extends GridPane {

    /*Contrôle de la taille et position*/
    val w = 645
    val h = 110

    columnConstraints = List(new ColumnConstraints(w), new ColumnConstraints(w))
    rowConstraints = List(new RowConstraints(h), new RowConstraints(h))

    gridLinesVisible = true

    alignment = Pos.BottomCenter

    for (i <- 0 to 3) {
        var b = new Button("0")
        b.setMinWidth(w)
        b.setMinHeight(h)

        b.onAction = handle {
            println("Button pressed : " + i)
            b.setText(b.getText+"1")
        }

        add(b, i%2, i/2)
    }
}

class Arena(allies : Array[Hero], enemies : Array[Monster]) extends GridPane {

    /*Contrôle de la taille et position*/

    val w = 430
    val h = 250

    columnConstraints = List(new ColumnConstraints(w), new ColumnConstraints(w), new ColumnConstraints(w))
    rowConstraints = List(new RowConstraints(h), new RowConstraints(h))

    gridLinesVisible = true

    alignment = Pos.TopCenter

    for (i <- 0 to 2) {
        var ivAllies = new ImageView(new Image(allies(i).visual, w, h, false, false))
        add(ivAllies, (i%3), 1)

        var ivEnemies = new ImageView(new Image(enemies(i).visual, w, h, false, false))
        ivEnemies.fitWidth = w
        ivEnemies.fitHeight = h
        add(ivEnemies, (i%3), 0)
    }
}