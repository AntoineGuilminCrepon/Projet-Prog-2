package menus

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

class MenuThreeChoices(choiceOne : Node, choiceTwo : Node, choiceThree : Node) extends GridPane {
	this.getColumnConstraints.addAll(new ColumnConstraints(150), new ColumnConstraints(460), new ColumnConstraints(460), new ColumnConstraints(460), new ColumnConstraints(150))
	this.getRowConstraints.addAll(new RowConstraints(50), new RowConstraints(780), new RowConstraints(50))


	this.add(choiceOne, 1, 1)
	this.add(choiceTwo, 2, 1)
	this.add(choiceThree, 3, 1)
}

class MenuEightChoices(c1 : Node, c2 : Node, c3 : Node, c4 : Node, c5 : Node, c6 : Node, c7 : Node, c8 : Node) extends GridPane {
	this.getColumnConstraints.addAll(new ColumnConstraints(150), new ColumnConstraints(345), new ColumnConstraints(345), new ColumnConstraints(345), new ColumnConstraints(345), new ColumnConstraints(150))
	this.getRowConstraints.addAll(new RowConstraints(50), new RowConstraints(390), new RowConstraints(390), new RowConstraints(50))

	this.add(c1, 1, 1)
	this.add(c2, 2, 1)
	this.add(c3, 3, 1)
	this.add(c4, 4, 1)
	this.add(c5, 1, 2)
	this.add(c6, 2, 2)
	this.add(c7, 3, 2)
	this.add(c8, 4, 2)
}