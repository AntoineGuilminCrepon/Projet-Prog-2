package window

import scalafx._
import scalafx.application._
import scalafx.scene._
import scalafx.scene.layout._
import scalafx.event._
import scalafx.geometry._

import arena._
import attackmenu._
import messagedisplay._
import battle._
import fighter._
import heroes._
import monsters._

object FightArena extends JFXApp3 {

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

    override def start () : Unit = {
        stage = new JFXApp3.PrimaryStage {
            title = "Fight Arena"
            var messagesDispayer = new MessagesDisplay
            var battle = new Battle(messagesDispayer, allies, enemies)
            var arena = new Arena(battle, messagesDispayer, allies, enemies)
            var attackMenu = new AttackMenu(battle, arena, messagesDispayer)
            scene = new Scene(1290, 1040) {
                root = new GridPane {
                    alignment = Pos.Center
                    rowConstraints = List(new RowConstraints(630), new RowConstraints(180), new RowConstraints(220))
                    columnConstraints = List(new ColumnConstraints(1290))

                    this.add(arena, 0, 0)
                    this.add(messagesDispayer, 0, 1)
                    this.add(attackMenu, 0, 2)
                }
            }
        }
        stage.setResizable(false)
        stage.show()
    }
}