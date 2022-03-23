package buttons

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

import wiki._

class WikiButton(wiki : Wiki, width : Int, height : Int, text : String, imageURL : String, updatedNode : Node) 
	extends Button(text, new ImageView(new Image(imageURL, width, height, true, false)) {setPreserveRatio(true)}) {
		setPrefSize(width, height)
		setFocusTraversable(false)
		setStyle("-fx-text-fill: red; -fx-font-size: 18; -fx-alignment: bottom-left;")
		setContentDisplay(ContentDisplay.TOP)

		setOnAction(_ => wiki.updateWikiScene(updatedNode))
}

class BarButton(wiki : Wiki, text : String, updatedNode : Node) extends Button(text) {
	setPrefSize(200, 75)
	setFocusTraversable(false)
	setStyle("-fx-text-fill: red")
	
	setOnAction(_ => wiki.updateWikiScene(updatedNode))
}