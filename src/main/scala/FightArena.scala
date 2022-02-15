package window

import javafx._
import javafx.application._
import javafx.stage._
import javafx.scene._
import javafx.scene.control._
import javafx.scene.layout._
import javafx.geometry._

import initfightarena._
import fighter._
import heroes._
import monsters._

class FightArena extends Application with InitFightArena {

    Thread.setDefaultUncaughtExceptionHandler((t, e) => System.err.println());

    /* Décommenter cette partie pour la génération aléatoire de combattants (il faut alors commenter les deux lignes suivantes)

    val allies = new Array[Fighter](3)
    val enemies = new Array[Fighter](3)

    for (i <- 0 to 2) {
        allies(i) = Heroes.getRandomHero(i)
        enemies(i) = Monsters.getRandomMonster(i+3)
    }*/

    val allies = Heroes.getThreeRandomUniqueHeroes(0)
    val enemies = Monsters.getThreeRandomUniqueMonsters(3)

    override def start (stage : Stage) : Unit = {
        restart(stage, allies, enemies)
    }
}

object Main {
    def main(args : Array[String]) = {
        Application.launch(classOf[FightArena])
    }
}