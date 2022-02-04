package frames

import scalafx._
import scalafx.collections._
import scalafx.scene.image._
import scalafx.scene.layout._
import scalafx.scene.control._
import scalafx.geometry._
import scalafx.scene._

class AttackButtons extends GridPane {

    /*Contrôle de la taille et position*/
    val w = 645
    val h = 110

    columnConstraints = List(new ColumnConstraints(w), new ColumnConstraints(w))
    rowConstraints = List(new RowConstraints(h), new RowConstraints(h))

    gridLinesVisible = true

    alignment = Pos.BottomCenter

    for (i <- 1 to 4) {
        var b = new Button(s"Attaque $i")
        b.setMinWidth(w)
        b.setMinHeight(h)
        add(b, (i-1)%2, (i-1)/2)
    }
}

class Arena extends GridPane {

    /*Contrôle de la taille et position*/

    val w = 430
    val h = 250

    columnConstraints = List(new ColumnConstraints(w), new ColumnConstraints(w), new ColumnConstraints(w))
    rowConstraints = List(new RowConstraints(h), new RowConstraints(h))

    gridLinesVisible = true

    alignment = Pos.TopCenter
}