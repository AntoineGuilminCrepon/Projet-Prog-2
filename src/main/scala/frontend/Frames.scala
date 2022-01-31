package frames

import swing._
import swing.event._

class AttackButtons(b: Array[Button]) extends GridPanel(2, 2) {
    for (i <- 0 to 3) {
        contents += b(i)
    }
}

class Arena(allies: Array[TextField], enemies: Array[TextField]) extends GridPanel(2, 3) {
    preferredSize = new Dimension(1920, 800)

    for (i <- 0 to 2) {
        contents += new Label{
            icon = new javax.swing.ImageIcon("src/main/resources/red_square.png")
        }
    }
    
    for (i <- 0 to 2) {
        contents += allies(i)
    }
}