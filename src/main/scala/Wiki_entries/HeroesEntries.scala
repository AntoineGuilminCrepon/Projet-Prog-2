package heroesentries

import javafx.scene.text._

import pages._

import swordman._
import magician._
import archer._

object swordmanPage extends FighterPage(new Swordman(0), "Je massacrerai tous les gobelins...")
object magicianPage extends FighterPage(new Magician(0), "Vous ne passerez pas !")
object archerPage extends FighterPage(new Archer(0), "Et hop, sans les mains !")