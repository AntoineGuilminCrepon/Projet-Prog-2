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
import items._

/* Permet d'appeler la fonction restart pour démarrer un combat à partir d'un stage, et d'alliés et d'ennemis */
trait InitFightArena {
    def restartFA(stage : Stage, allies : Array[Fighter], enemies : Array[Fighter], inventory : Inventory) = {
        stage.setTitle("Fight Arena")
		var messagesDisplayer = new MessagesDisplay
        var battle = new Battle(messagesDisplayer, allies, enemies)
        var arena = new Arena(battle, messagesDisplayer, allies, enemies)
        var attackMenu = new AttackMenu(stage, battle, arena, messagesDisplayer, None, inventory)
		var itemsPane = new ItemsPane(attackMenu, inventory.items)
		attackMenu.itemsPane = Some(itemsPane)
        var root = new Pane { this.getChildren.addAll(new GridPane {
                var rowArena = new RowConstraints(640)
                var rowMD = new RowConstraints(180)
                var rowAM = new RowConstraints(220)
                this.getRowConstraints().addAll(rowArena, rowMD, rowAM)
				this.getColumnConstraints().addAll(new ColumnConstraints(150), new ColumnConstraints(1620), new ColumnConstraints(150))

                this.setAlignment(Pos.CENTER)
                this.add(arena, 1, 0)
                this.add(messagesDisplayer, 1, 1)
                this.add(attackMenu, 1, 2)
            }, itemsPane) }

        var scene = new Scene(root, 1920, 1080)
        stage.setScene(scene)
    }
}