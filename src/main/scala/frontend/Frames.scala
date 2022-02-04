package frames

import scalafx._
import scalafx.collections._
import scalafx.scene.image._
import scalafx.scene.layout._
import scalafx.scene.control._
<<<<<<< HEAD
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
=======

class AttackButtons(width: Int, height: Int) extends GridPane {

    prefWidth = width
    prefHeight = height

    /*Ajout des boutons*/

    for (i <- 1 to 4) {
        val b = new Button(s"Attaque $i")
        b.resize(width = 960, height = 200)
        add(b, i%2, i/2)
    }
}

class Arena(width: Int, height: Int) extends GridPane {

    prefWidth = width
    prefHeight = height

    /*Ajout des images*/

    for (i <- 0 to 5) {
        add(new ImageView(new Image("red_square.png")), i%3, i/3)
    }
}
>>>>>>> 4862da4d70279418dbf55d2978c0642d07040b02
