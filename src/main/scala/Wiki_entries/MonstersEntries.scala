package monstersentries

import javafx.scene.text._

import pages._

import slime._
import goblin._
import skeleton._

object SlimePage extends FighterPage(new Slime(0), "Je ne suis pas un mauvais gluant !")
object GoblinPage extends FighterPage(new Goblin(0), "...")
object SkeletonPage extends FighterPage(new Skeleton(0), "Kakakakakakaka...")