package window

import javafx._
import javafx.application._
import javafx.stage._
import javafx.scene._
import javafx.scene.control._
import javafx.scene.layout._
import javafx.geometry._

import arena._
import attackmenu._
import messagedisplay._
import battle._
import fighter._
import heroes._
import monsters._

class FightArena extends Application {

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
        stage.setTitle("Fight Arena")
        var messagesDispayer = new MessagesDisplay
        var battle = new Battle(messagesDispayer, allies, enemies)
        var arena = new Arena(battle, messagesDispayer, allies, enemies)
        var attackMenu = new AttackMenu(stage, battle, arena, messagesDispayer)
        var root = new GridPane {
                var rowArena = new RowConstraints(640)
                var rowMD = new RowConstraints(180)
                var rowAM = new RowConstraints(220)
                this.getRowConstraints().addAll(rowArena, rowMD, rowAM)

                this.setAlignment(Pos.CENTER)
                this.add(arena, 0, 0)
                this.add(messagesDispayer, 0, 1)
                this.add(attackMenu, 0, 2)
            }

        var scene = new Scene(root, 1290, 1040)
        stage.setScene(scene)
        stage.setResizable(false)
        stage.show()
    }
}

object Main {
    def main(args : Array[String]) = {
        Application.launch(classOf[FightArena])
    }
}