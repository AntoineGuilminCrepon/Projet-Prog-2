package heroesentries

import javafx.scene.text._

import pages._

import swordman._
import magician._
import archer._

object SwordmanPage extends FighterPage(new Swordman(0), "Je massacrerai tous les gobelins...")
object MagicianPage extends FighterPage(new Magician(0), "Vous ne passerez pas !")
object ArcherPage extends FighterPage(new Archer(0), "Et hop, sans les mains !")