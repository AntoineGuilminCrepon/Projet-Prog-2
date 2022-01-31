package window

import swing._
import swing.event._

import frames._

object MyApp extends SimpleSwingApplication {
    val allies = new Array[TextField](3)
    val enemies = new Array[TextField](3)
    for (i <- 1 to 3) {
        allies(i-1) = new TextField(s"Allié $i")
        enemies(i-1) = new TextField(s"Ennemi $i")
    }

    var b = new Array[Button](4)
    for (i <- 1 to 4) {
        b(i-1) = new Button {
            action = Action(s"Attaque $i") {
                println(s"Cliked $i")
            }
        }
    }
 
    def top = new MainFrame {
        title = "Arène"
        preferredSize = new Dimension(1920, 1080)
        contents =
            new BoxPanel(Orientation.Vertical) {
                contents += new Arena(allies, enemies)
                contents += new AttackButtons(b)
            }
    }
}