package window

import scalafx._
import scalafx.application._
import scalafx.scene._
import scalafx.scene.layout._
import scalafx.event._

import frames._
import hero._
import monster._

object Battle extends JFXApp3 {

    val allies = new Array[Hero](3)
    val enemies = new Array[Monster](3)

    for (i <- 0 to 2) {
        allies(i) = new Swordman
        enemies(i) = new Slime
    }

    override def start () : Unit = {
        stage = new JFXApp3.PrimaryStage {
            title = "Fight Arena"
            scene = new Scene(1290, 720) {
                root = new StackPane {
                    children.add(new AttackButtons(new Swordman))
                    children.add(new Arena(allies, enemies))
                }
            }
        }
        stage.setResizable(false)
    }
}