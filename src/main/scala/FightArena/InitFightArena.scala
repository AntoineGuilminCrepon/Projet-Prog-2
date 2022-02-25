package initfightarena

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

/* Permet d'appeler la fonction restart pour démarrer un combat à partir d'un stage, et d'alliés et d'ennemis */
trait InitFightArena {
    def restart(stage : Stage, allies : Array[Fighter], enemies : Array[Fighter]) = {
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
        stage.show()
    }
}