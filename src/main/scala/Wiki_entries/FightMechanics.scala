package fightmechanics

import javafx.scene.text._

import pages._

object FightMechanics extends Page("Mécaniques de combat", new TextFlow()) {
	content.getChildren.add(new Text("Le fonctionnement général est très inspiré du jeu de figurines Warhammer.\n\n") {setStyle("-fx-font-style: italic; -fx-font-size: 14")})
	content.getChildren.add(new Text("Combattants\n") {setStyle("-fx-underline: true; -fx-font-size: 30")})
	content.getChildren.add(new Text("Chaque combattant possède plusieurs caractéristiques :\n") {setStyle("-fx-font-size: 16")})
	content.getChildren.add(new BulletList(Array(
		"Points de vie : Représentent la vie des différents combattants qui meurent quand ceux-ci tombent à 0",
		"Capacité de combat / de tir : Traduisent l'habilité du combattant à toucher sa cible",
		"Force / Endurance : Utilisés pour le calcul des dégâts infigés",
		"Initiative : Classe les combattants pour l'ordre des attaque à chaque tour\n"
	)) {setStyle("-fx-font-size: 16")})
	content.getChildren.add(new Text("Attaques\n") {setStyle("-fx-underline: true; -fx-font-size: 30")})
	content.getChildren.add(new Text("Chaque attaque possède plusieurs caractéristiques également :\n") {setStyle("-fx-font-size: 16")})
	content.getChildren.add(new BulletList(Array(
		"Type d'attaque : Décrit s'il s'agit d'une attaque de corps à corps ou à distance",
		"Difficulté : Traduit la difficulté qu'a le combattant pour toucher son adversaire",
		"Force / Endurance : Utilisés pour le calcul des dégâts infigés",
		"Modificateur de dégats : Correspond à la puissance de l'attaque en tant que telle",
		"Effets : Certaines attaques possèdent un ou plusieurs effet(s) supplémentaire(s) qui peut affecter un ennemi et/ou un allié\n"
	)) {setStyle("-fx-font-size: 16")})
	content.getChildren.add(new Text("Combats\n") {setStyle("-fx-underline: true; -fx-font-size: 30;")})
	content.getChildren.add(new Text("Un combat est déterminé par un attaquant, un défenseur et une attaque. " + 
		"Par ailleurs, avant de déterminer les dégâts, il faut savoir si l'attaque touche sa cible ou non. " + 
		"Pour cela, on tire un nombre aléatoire entre 0 et 10, et on ajoute ce nombre à la capacité correspondante au type de l'attaque en question." + 
		"Si le résultat est supérieur à la difficulté de l'attaque, elle touche et on résout les dégats.\n")
		{setStyle("-fx-font-size: 16;")})
}