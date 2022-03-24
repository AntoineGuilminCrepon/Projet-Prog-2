package pages

import javafx._
import javafx.stage._
import javafx.scene._
import javafx.scene.image._
import javafx.scene.control._
import javafx.scene.layout._
import javafx.scene.text._
import javafx.scene.paint._
import javafx.geometry._
import javafx.scene.shape._
import javafx.scene.input._
import javafx.scene.transform._
import javafx.geometry._

import fighter._
import attack._

/* Classe utile pour créer rapidement des listes à puces */
class BulletList(elements : Array[String], indentation : Int = 0) extends Text("") {
	this.setStyle("-fx-font-size: 14")
	for (el <- elements) {
		this.setText(this.getText() + ("\t" * indentation) + "• " + el + "\n")
	}
}

/* Permet d'afficher simplement du texte dans la partie centrale du Wiki */
class Page(val name : String, val content : TextFlow) extends GridPane {
	this.getColumnConstraints.addAll(new ColumnConstraints(150), new ColumnConstraints(700), new ColumnConstraints(450), new ColumnConstraints(170), new ColumnConstraints(150))
	this.getRowConstraints.addAll(new RowConstraints(100), new RowConstraints(100), new RowConstraints(50), new RowConstraints(510), new RowConstraints(100))

	val title = new FlowPane(
		new Text(name) {
			setFill(Color.RED)
			setTextAlignment(TextAlignment.CENTER)
			setStyle("-fx-font-size: 40")
		}
	) {setAlignment(Pos.CENTER)}

	this.add(title, 1, 1, 2, 1)
	this.add(content, 1, 3, 2, 1)
}

/* Permet d'afficher simplement toutes les caractéristiques d'un combattant */
class FighterPage(fighter : Fighter, quote : String) extends Page(fighter.toString(), new TextFlow()) {
	var fullText = new TextFlow()
	this.add(fullText, 1, 3)
	this.add(new Group(new ImageView(new Image(fighter.visual, 450, 510, true, false))), 2, 3)
	fullText.getChildren.add(new Text(quote + "\n\n") {setStyle("-fx-font-style: italic; -fx-font-size: 12")})

	fullText.getChildren.addAll(new BulletList(Array(
		"Points de vie : " + fighter.maxLifePoints,
		"Capacité de combat : " + fighter.meleeCapacity,
		"Capacité de tir : " + fighter.rangeCapacity,
		"Force : " + fighter.strength,
		"Endurance : " + fighter.toughness,
		"Initiative : " + fighter.initiative
	)), new Text("\n"))

	/* Partie décrivant les attaques du combattant */
	for (i <- 0 to 3) {
		fullText.getChildren.addAll(
			new Text("\n• " + fighter.attacks(i).toString() + "\n") {setStyle("-fx-font-size: 14")},
			new BulletList(Array(
				"Cible : " + fighter.attacks(i).targetAlignment,
				"Type d'attaque : " + fighter.attacks(i).attackType,
				"Difficulté : " + fighter.attacks(i).attackDifficulty,
				"Modifieur de dégats : " + fighter.attacks(i).damageModifier
			), 1),
			new Text((if (fighter.attacks(i).enemyEffect.isEmpty) "" 
				else ("\t• " + (fighter.attacks(i).enemyEffect.get.probability * 100) + "% d'infliger " + fighter.attacks(i).enemyEffect.get.toString() + " à l'ennemi pendant " + fighter.attacks(i).enemyEffect.get.timer + " tour(s)\n")) +
				(if (fighter.attacks(i).allyEffect.isEmpty) "" else ("\t• " + (fighter.attacks(i).allyEffect.get.probability * 100) + "% d'infliger " + fighter.attacks(i).allyEffect.get.toString() + " à soi-même pendant " + fighter.attacks(i).enemyEffect.get.timer + " tour(s)\n")))
				{setStyle("-fx-font-size: 14")}
		)
	}
}