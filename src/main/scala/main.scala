package window

import scalafx._
import scalafx.application._
import scalafx.scene._
import scalafx.scene.layout._
import scalafx.event._

import frames._
import messagedisplay._
import battle._
import fighter._
import hero._
import monster._

object FightArena extends JFXApp3 {

    val allies = new Array[Fighter](3)
    val enemies = new Array[Fighter](3)

    for (i <- 0 to 2) {
        allies(i) = new Swordman(i)
        enemies(i) = new Slime(i+3)
    }

    override def start () : Unit = {
        stage = new JFXApp3.PrimaryStage {
            title = "Fight Arena"
            var messagesDispayer = new MessagesDisplay
            scene = new Scene(1290, 770) {
                root = new StackPane {
                    children.add(new AttackButtons(new Battle(messagesDispayer, allies, enemies)))
                    children.add(messagesDispayer)
                    children.add(new Arena(allies, enemies))
                }
            }
        }
        stage.setResizable(false)
        stage.show()
    }
}