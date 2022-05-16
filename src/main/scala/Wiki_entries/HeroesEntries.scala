package heroesentries

import javafx.scene.text._

import pages._

import fighter._

class SwordmanPage(hero : Fighter) extends FighterPage(hero, "Je massacrerai tous les gobelins...")
class MagicianPage(hero : Fighter) extends FighterPage(hero, "Vous ne passerez pas !")
class ArcherPage(hero : Fighter) extends FighterPage(hero, "Et hop, sans les mains !")