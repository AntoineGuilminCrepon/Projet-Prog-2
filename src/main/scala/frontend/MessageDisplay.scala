package messagedisplay

import scalafx._
import scalafx.collections._
import scalafx.scene.image._
import scalafx.scene.layout._
import scalafx.scene.control._
import scalafx.geometry._
import scalafx.scene._
import scalafx.Includes._
import scalafx.event.ActionEvent

class MessagesDisplay extends Label {
    val w = 900
    val h = 50

    alignment = Pos.Center

    this.setWrapText(true)
    this.setText("\n\n\n")

    def newMessage(message : String) : Unit = {
        this.setText("\n\n\n" + message)
    }

    def continueMessage(message : String) : Unit = {
        this.setText(this.getText() + "\n" + message)
    }
}