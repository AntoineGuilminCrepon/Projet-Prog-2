package attackeffectsentries

import javafx.scene.text._

import pages._

object AttackEffectPage extends Page("Effets d'attaques", new TextFlow()) {
	content.getChildren.add(new BulletList(Array(
		/* Oui c'est bien "a attaqué" et non "ait attaqué" car "après que" est suivi de l'indicatif */
		"Acide / Feu / Poison / Saignement : Inflige des dégâts à chaque tour après que le combattant affecté a attaqué\n",
		"Étourdissement : Empêche le combattant affecté d'attaqué pendant un nombre défini de tours en lui faisant rater toutes ses attaques\n",
		"Affaiblissement : Réduit les capacités de combat et de tir du combattant ciblé d'une quantité définie pendant un certain nombre de tours\n"
	)) {setStyle("-fx-font-size: 20")})
}