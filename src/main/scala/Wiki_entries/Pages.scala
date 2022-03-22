package pages

import javafx._
import javafx.stage._
import javafx.scene._
import javafx.scene.image._
import javafx.scene.control._
import javafx.scene.layout._
import javafx.scene.text._
import javafx.geometry._
import javafx.scene.shape._
import javafx.scene.input._
import javafx.scene.transform._
import javafx.geometry._

class Page(val name : String, val content : TextFlow) extends GridPane {
	this.getColumnConstraints.addAll(new ColumnConstraints(150), new ColumnConstraints(1620), new ColumnConstraints(150))
	this.getRowConstraints.addAll(new RowConstraints(100), new RowConstraints(660), new RowConstraints(100))

	this.add(content, 1, 0)
}