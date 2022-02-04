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

    val w = Parent.maxwidth/2

    /*columnConstraints = List(new ColumnConstraints(w), new ColumnConstraints(width))
    rowConstraints = List(new RowConstraints(height), new RowConstraints(height))

    gridLinesVisible = true*/

    alignment = Pos.BottomCenter
}

/*class Arena extends GridPane {

    /*Contrôle de la taille et position*/

    columnConstraints = List(new ColumnConstraints(width), new ColumnConstraints(width), new ColumnConstraints(width))
    rowConstraints = List(new RowConstraints(height), new RowConstraints(height))

    gridLinesVisible = true

    alignment = Pos.TopCenter
}*/