package itemspane

import javafx._
import javafx.stage._
import javafx.scene._
import javafx.scene.image._
import javafx.scene.control._
import javafx.scene.layout._
import javafx.geometry._
import javafx.scene.transform._

import items._

class ItemsPane(items : List[Item]) extends Pane {
	/* Boutons correspondant aux objets */
	class ItemImage(item : Item) extends ImageView(new Image(item.imageURL, 300, 200, true, false)) {
		this.setPreserveRatio(true)
		this.getTransforms.add(new Translate() { setY(35) })
	}

	this.setVisible(false)
	this.setPrefSize(1920, 300)
	this.setMinWidth(1920)
	this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px")
	this.setLayoutX(0)
	this.setLayoutY(400)

	var grid = new GridPane() {
		this.getColumnConstraints().addAll(new ColumnConstraints(60), new ColumnConstraints(250), new ColumnConstraints(100), new ColumnConstraints(300), new ColumnConstraints(100), new ColumnConstraints(300), new ColumnConstraints(100), new ColumnConstraints(300), new ColumnConstraints(100), new ColumnConstraints(250), new ColumnConstraints(60))
		this.setAlignment(Pos.CENTER)
	}

	def updateItemsButtons(currentPage : Int) : Unit = {
		grid.getChildren().clear()

		var leftArrow =
			new javafx.scene.control.Button("", new ImageView(new Image("left_arrow.png", 300, 200, true, false))) {
				this.setPrefSize(300, 200)
				this.setVisible(false)
				this.getTransforms.add(new Translate() { setY(35) })
				this.setOnAction(_ => updateItemsButtons(currentPage - 1))
			}
		grid.add(leftArrow, 1, 0)

		var rightArrow = 
			new Button("", new ImageView(new Image("left_arrow.png", 300, 200, true, false)) { this.setRotate(180) }) {
				this.setPrefSize(300, 200)
				this.setVisible(false)
				this.getTransforms.add(new Translate() { setY(35) })
				this.setOnAction(_ => updateItemsButtons(currentPage + 1))
			}
		grid.add(rightArrow, 9, 0)

		val itemsShown = items.slice(currentPage * 3, currentPage * 3 + 3).toArray
		for (i <- 0 to itemsShown.length - 1) {
			grid.add(new ItemImage(itemsShown(i)), 3 + 2 * i, 0)
		}

		if (currentPage > 0) {
			leftArrow.setVisible(true)
		}
		if (items.length > (currentPage + 1) * 3) {
			rightArrow.setVisible(true)
		}
	}

	this.getChildren().add(grid)
}