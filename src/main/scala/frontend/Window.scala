package window

import scalafx._
import scalafx.application._
import scalafx.scene._
import scalafx.scene.layout._
import scalafx.event._

import frames._

object Battle extends JFXApp3 {

<<<<<<< HEAD
    override def start () : Unit = {
        stage = new JFXApp3.PrimaryStage {
            title = "Fight Arena"
            scene = new Scene {
                root = new StackPane {
                    children.add(new AttackButtons)
=======

    override def start () : Unit = {
        stage = new JFXApp3.PrimaryStage {
            title = "Fight Arena"
            scene = new Scene(width = 1920, height =1080) {
                root = new StackPane {
                    children += new Arena(width = 1920, height = 680)
                    children += new AttackButtons(width = 1920, height = 400)
>>>>>>> 4862da4d70279418dbf55d2978c0642d07040b02
                }
            }
        }
    }
}