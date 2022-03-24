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
import javafx.scene.paint._
import javafx.scene.input._
import javafx.scene.transform._
import javafx.geometry._

import worldmap._
import buttons._
import menus._

import heroesentries._
import monstersentries._
import fightmechanics._
import attackeffectsentries._

/* Classe principale du Wiki du jeu */
class Wiki(var stage : Stage) {
	/* Correspond à l'écran entier */
	var root = new GridPane() {
		this.getColumnConstraints.addAll(new ColumnConstraints(300), new ColumnConstraints(2), new ColumnConstraints(2), new ColumnConstraints(1616))
	}

	/* Correspond à la barre de naviagation à gauche */
	var navigationBar = new GridPane() {
		this.getColumnConstraints.addAll(new ColumnConstraints(300))
		this.getRowConstraints.addAll(new RowConstraints(539), new RowConstraints(2), new RowConstraints(539))
		this.add(new Separator(Orientation.HORIZONTAL) {setPrefWidth(300)}, 0, 1)
	}

	/* Préparation des différents boutons qui seront utilisés */
	val swordmanButton = new WikiButton(this, 460, 780, "ÉPÉISTE", "/Fighters/swordman.png", SwordmanPage)
	val magicianButton = new WikiButton(this, 460, 745, "MAGICIEN", "/Fighters/magician.png", MagicianPage)
	val archerButton = new WikiButton(this, 460, 780, "ARCHER", "/Fighters/archer.png", ArcherPage)
	val heroesMenu = new MenuThreeChoices(swordmanButton, magicianButton, archerButton)

	val slimeButton = new WikiButton(this, 380, 460, "SLIME", "/Fighters/slime.png", SlimePage)
	val goblinButton = new WikiButton(this, 360, 390, "GOBELIN", "/Fighters/goblin.png", GoblinPage)
	val skeletonButton = new WikiButton(this, 345, 390, "SQUELETTE", "/Fighters/skeleton.png", SkeletonPage)
	val monsterMenu = new MenuEightChoices(slimeButton, goblinButton, skeletonButton, new Pane(), new Pane(), new Pane(), new Pane(), new Pane())

	val fightMechanicsButton = new WikiButton(this, 460, 780, "MÉCANIQUES DE COMBAT", "/Items/crossed swords.png", FightMechanics)
	val attackEffectsButton = new WikiButton(this, 460, 780, "EFFETS D'ATTAQUES", "/Items/blood drop.png", AttackEffectPage)

	val otherMenu = new MenuThreeChoices(fightMechanicsButton, attackEffectsButton, new Pane())

	val heroesBarButton = new BarButton(this, "HÉROS", heroesMenu)
	val monstersBarButton = new BarButton(this, "MONSTRES", monsterMenu)
	val otherBarButton = new BarButton(this, "AUTRES", otherMenu)
	val aleaButton = new BarButton(this, "ALÉATOIRE", new Pane())
	
	/* Correspond à la partie supérieure dans la barre de navigation */
	val quickAccess = new GridPane() {
		this.getColumnConstraints.addAll(new ColumnConstraints(50), new ColumnConstraints(200), new ColumnConstraints(50))
		this.getRowConstraints.addAll(new RowConstraints(59), new RowConstraints(60), new RowConstraints(100), new RowConstraints(100), new RowConstraints(100), new RowConstraints(100), new RowConstraints(20))
		
		this.add(new TextFlow(new Text("   Bar d'accès rapide") {
			setFill(Color.RED)
			setStyle("-fx-font-size: 18")
		}), 1, 1)

		this.add(heroesBarButton, 1, 2)
		this.add(monstersBarButton, 1, 3)
		this.add(otherBarButton, 1, 4)
		this.add(aleaButton, 1, 5)
	}
	navigationBar.add(quickAccess, 0, 0)

	val heroButton = new WikiButton(this, 460, 780, "HÉROS", "/Fighters/swordman.png", heroesMenu)
	val monsterButton = new WikiButton(this, 460, 780, "MONSTRES", "/Fighters/goblin.png", monsterMenu)
	val otherButton = new WikiButton(this, 460, 780, "AUTRES", "/Items/chest.png", otherMenu)
	val mainMenu = new MenuThreeChoices(heroButton, monsterButton, otherButton)

	/* Fonction principale permettant de mettre à jour la partie centrale du wiki pour ne pas tout recharger */
	def updateWikiScene(centralNode : Node) : Unit = {
		root.getChildren.clear()
		root.add(navigationBar, 0, 0)
		root.add(new Separator(Orientation.VERTICAL) {setPrefHeight(1080)}, 1, 0)
		root.add(new Separator(Orientation.VERTICAL) {setPrefHeight(1080)}, 2, 0)
		
		var mainMenuButton = new Button("Retour au menu principal") {
			setPrefSize(300, 150)
			setFocusTraversable(false)
			setOnAction(_ => updateWikiScene(mainMenu))
		}
		
		var exitButton = new Button("Retour à la carte du monde") {
			setPrefSize(300, 150)
			setFocusTraversable(false)
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