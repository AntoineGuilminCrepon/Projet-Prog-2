package savesfrontend

import javafx._
import javafx.scene.control._
import javafx.scene.layout._

object SaveButton extends Button("Sauvegarder") {
	this.setPrefSize(300, 150)	
	this.setLayoutX(450)
	this.setLayoutY(100)
	setFocusTraversable(false)
}

object LoadButton extends Button("Recharger la sauvegarde") {
	this.setPrefSize(300, 150)	
	this.setLayoutX(1120)
	this.setLayoutY(100)
	setFocusTraversable(false)
}

object SavesPane extends Pane {
	this.setPrefSize(1920, 300)
	this.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px")
	this.setLayoutX(0)
	this.setLayoutY(400)

	this.getChildren().addAll(
		new Label("Menu de sauvegarde") {
			setStyle("-fx-text-fill: red; -fx-font-size: 18")
			setLayoutX(850)
			setLayoutY(50)
		},
		SaveButton,
		LoadButton)
}