package frames

import swing._
import swing.event._

class AttackButtons(b: Array[Button]) extends GridPanel(2, 2) {
    for (i <- 0 to 3) {
        contents += b(i)
    }
}

class Arena(allies: Array[Label], enemies: Array[Label]) extends GridPanel(2, 3) {
    preferredSize = new Dimension(1920, 800)

    for (i <- 0 to 2) {
        contents += enemies(i)
    }

    for (i <- 0 to 2) {
        contents += allies(i)
    }
}