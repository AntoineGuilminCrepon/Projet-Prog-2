package messagedisplay

import scalafx._
import scalafx.collections._
import scalafx.scene.image._
import scalafx.scene.layout._
import scalafx.scene.text._
import scalafx.scene.control._
import scalafx.geometry._
import scalafx.scene._
import scalafx.Includes._
import scalafx.event.ActionEvent

/* Correspond à la partie centrale où le texte est affiché */
class MessagesDisplay extends Label {
    translateX = 1050 / 2

    textAlignment = TextAlignment.Center

    this.setWrapText(true)
    this.setText("")

    def newMessage(message : String) : Unit = {
        this.setText(message)
    }

    def continueMessage(message : String) : Unit = {
        this.setText(this.getText() + "\n" + message)
    }
}