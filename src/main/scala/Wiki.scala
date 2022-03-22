package wiki

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

import worldmap._

class WikiButton(wiki : Wiki, width : Int, height : Int, text : String, imageURL : String, updatedNode : Node) 
	extends Button(text, new ImageView(new Image(imageURL, width, height, true, false)) {setPreserveRatio(true)}) {
		setPrefSize(width, height)
		setFocusTraversable(false)
		setStyle("-fx-text-fill: red; -fx-font-size: 18; -fx-alignment: bottom-left;")
		setContentDisplay(ContentDisplay.TOP)

		setOnAction(_ => wiki.updateWikiScene(updatedNode))
}

class MenuThreeChoices(choiceOne : Node, choiceTwo : Node, choiceThree : Node) extends GridPane {
	this.getColumnConstraints.addAll(new ColumnConstraints(150), new ColumnConstraints(460), new ColumnConstraints(460), new ColumnConstraints(460), new ColumnConstraints(150))
	this.getRowConstraints.addAll(new RowConstraints(50), new RowConstraints(780), new RowConstraints(50))


	this.add(choiceOne, 1, 1)
	this.add(choiceTwo, 2, 1)
	this.add(choiceThree, 3, 1)
}

class Wiki(var stage : Stage) {
	var root = new GridPane() {
		this.getColumnConstraints.addAll(new ColumnConstraints(300), new ColumnConstraints(2), new ColumnConstraints(2), new ColumnConstraints(1616))
	}

	var quickSearchBar = new GridPane() {
		this.getColumnConstraints.addAll(new ColumnConstraints(300))
		this.getRowConstraints.addAll(new RowConstraints(539), new RowConstraints(2), new RowConstraints(539))
		this.add(new Separator(Orientation.HORIZONTAL) {setPrefWidth(300)}, 0, 1)
	}

	val swordmanButton = new WikiButton(this, 460, 780, "ÉPÉISTE", "/Fighters/swordman.png", new Pane())
	val magicianButton = new WikiButton(this, 460, 745, "MAGICIEN", "/Fighters/magician.png", new Pane())
	val archerButton = new WikiButton(this, 460, 780, "ARCHER", "/Fighters/archer.png", new Pane())

	val heroButton = new WikiButton(this, 460, 780, "HÉROS", "/Fighters/swordman.png", new MenuThreeChoices(swordmanButton, magicianButton, archerButton))
	val monsterButton = new WikiButton(this, 460, 780, "MONSTRES", "/Fighters/goblin.png", new Pane())
	val otherButton = new WikiButton(this, 460, 780, "AUTRES", "/Items/chest.png", new Pane())

	val mainMenu = new MenuThreeChoices(heroButton, monsterButton, otherButton)

	def updateWikiScene(centralNode : Node) : Unit = {
		root.getChildren.clear()
		root.add(quickSearchBar, 0, 0)
		root.add(new Separator(Orientation.VERTICAL) {setPrefHeight(1080)}, 1, 0)
		root.add(new Separator(Orientation.VERTICAL) {setPrefHeight(1080)}, 2, 0)
		
		var mainMenuButton = new Button("Retour au menu principal") {
			setPrefSize(300, 150)
			setOnAction(_ => updateWikiScene(mainMenu))
		}
		
		var exitButton = new Button("Retour à la carte du monde") {
			setPrefSize(300, 150)
			setOnAction(_ => (new WorldMap).start(stage))
		}

		var centralMenu = new GridPane() {
			this.getColumnConstraints.addAll(new ColumnConstraints(400), new ColumnConstraints(300), new ColumnConstraints(16), new ColumnConstraints(300), new ColumnConstraints(400))
			this.getRowConstraints.addAll(new RowConstraints(880), new RowConstraints(200))
			this.add(centralNode, 0, 0, 5, 1)
			this.add(mainMenuButton, 1, 1)
			this.add(exitButton, 3, 1)
		}
		root.add(centralMenu, 3, 0)
	}

	updateWikiScene(mainMenu)
	var scene = new Scene(root, 1920, 1080)

	stage.setScene(scene)
}