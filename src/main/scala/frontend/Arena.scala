package arena

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

class Arena(battle : Battle, allies : Array[Fighter], enemies : Array[Fighter]) extends GridPane {

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

/* 
    On peut obtenir les enfants de Arena avec children(i), où i est :
    2   4   6
    1   3   5
    et 0 est la grille visible.
*/