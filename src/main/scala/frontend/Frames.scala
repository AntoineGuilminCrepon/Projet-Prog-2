package frames

import scalafx._
import scalafx.collections._
import scalafx.scene.image._
import scalafx.scene.layout._
import scalafx.scene.control._

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