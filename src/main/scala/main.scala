package window

import scalafx._
import scalafx.application._
import scalafx.scene._
import scalafx.scene.layout._
import scalafx.event._

import arena._
import attackmenu._
import messagedisplay._
import battle._
import fighter._
import hero._
import monster._

object FightArena extends JFXApp3 {

    Thread.setDefaultUncaughtExceptionHandler((t, e) => System.err.println());

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
            var battle = new Battle(messagesDispayer, allies, enemies)
            var arena = new Arena(battle, messagesDispayer, allies, enemies)
            scene = new Scene(1290, 900) {
                root = new StackPane {
                    children.add(new AttackMenu(battle, arena, messagesDispayer))
                    children.add(messagesDispayer)
                    children.add(arena)
                }
            }
        }
        stage.setResizable(false)
        stage.show()
    }
}