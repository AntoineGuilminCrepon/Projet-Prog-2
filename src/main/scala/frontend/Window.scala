package window

<<<<<<< HEAD
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
            scene = new Scene(width = 1920, height =1080) {
                root = new StackPane {
                    children += new Arena(width = 1920, height = 680)
                    children += new AttackButtons(width = 1920, height = 400)
                }
            }
        }
    }
=======
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
>>>>>>> ba44eb5 (Scission Graphics.scala)
}