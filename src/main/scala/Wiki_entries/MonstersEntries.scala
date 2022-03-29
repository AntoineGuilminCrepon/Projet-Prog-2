package monstersentries

import javafx.scene.text._

import pages._

import slime._
import goblin._
import skeleton._
import witch._
import dragon._
import skaven._
import ghost._
import bandit._

object SlimePage extends FighterPage(new Slime(0), "Je ne suis pas un mauvais gluant !")
object GoblinPage extends FighterPage(new Goblin(0), "...")
object SkeletonPage extends FighterPage(new Skeleton(0), "Kakakakakakaka...")
object WitchPage extends FighterPage(new Witch(0), "Ordinary Magician")
object DragonPage extends FighterPage(new Dragon(0), "Je veux me terrer dans ma tanière le temps que le règne des Hommes ne touche à sa fin.")
object SkavenPage extends FighterPage(new Skaven(0), "L'union fait la force !")
object GhostPage extends FighterPage(new Ghost(0), "Coucou, fais-moi peur")
object BanditPage extends FighterPage(new Bandit(0), "Ah ! Ce gars est toasté...") 