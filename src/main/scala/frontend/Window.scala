package window

import scalafx._
import scalafx.application._
import scalafx.scene._
import scalafx.scene.layout._
import scalafx.event._

import frames._

object Battle extends JFXApp3 {

    override def start () : Unit = {
        stage = new JFXApp3.PrimaryStage {
            title = "Fight Arena"
            scene = new Scene(1290, 720) {
                root = new StackPane {
                    children.add(new AttackButtons)
                    children.add(new Arena)
                }
            }
        }
        stage.setResizable(false)
    }
}