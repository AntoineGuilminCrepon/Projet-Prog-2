package shop

import javafx._
import javafx.stage._
import javafx.scene._
import javafx.scene.image._
import javafx.scene.control._
import javafx.scene.layout._
import javafx.geometry._
import javafx.scene.transform._

import worldmap._

import items._

class ShopButton(item : Item, inventory : Inventory, balanceLabel : Label { def secondLine(str : String) : Unit }) extends Button(item.toString(), new ImageView(new Image(item.imageURL, 405, 225, true, false)) { setPreserveRatio(true) }) {
	setPrefSize(405, 225)
	setFocusTraversable(false)
	setStyle("-fx-text-fill: red; -fx-font-size: 18")
	setContentDisplay(ContentDisplay.TOP)

	setOnAction(_ => {
		if (inventory.balance >= item.price) {
			inventory.balance -= item.price
			inventory.items = item :: inventory.items
			balanceLabel.secondLine("Vous avez acheté " + item + " pour " + item.price + " pièces d'or.")
		} else {
			balanceLabel.secondLine("Vous n'avez pas assez de pièces d'or pour acheter cet objet.")
		}
	})
}

class Shop {
	def start(stage : Stage, inventory : Inventory) = {
		stage.setTitle("Magasin")

		var root = new GridPane {
			this.getRowConstraints.addAll(new RowConstraints(150), new RowConstraints(600), new RowConstraints(100), new RowConstraints(50), new RowConstraints(150), new RowConstraints(30))
			this.getColumnConstraints.addAll(new ColumnConstraints(300), new ColumnConstraints(1620), new ColumnConstraints(300))
			this.setAlignment(Pos.CENTER)

			var balanceLabel = new Label() {
				this.getTransforms().add(new Translate() { setX(650) })
				
				def update() = {
					this.setText("Vous possédez actuellement " + inventory.balance.toString() + " pièce(s) d'or.")
				}
				
				def secondLine(str : String) : Unit = {
					this.update()
					this.setText(this.getText + "\n" + str)
				}

				update()
			}

			var itemsDisplay = new GridPane {
				this.getRowConstraints.addAll(new RowConstraints(225), new RowConstraints(50), new RowConstraints(50), new RowConstraints(225), new RowConstraints(50))
				this.getColumnConstraints.addAll(new ColumnConstraints(405), new ColumnConstraints(405), new ColumnConstraints(405), new ColumnConstraints(405))
				this.setAlignment(Pos.CENTER)

				private var currentPlace = Array(0, 0)
				def addNewItem(item : Item) = {
					this.add(new ShopButton(item, inventory, balanceLabel), currentPlace(0), currentPlace(1))
					this.add(new Label("Prix : " + item.price + " pièces d'or") {
						this.getTransforms().add(new Translate() { setX(110); setY(10) })
						this.setStyle("-fx-font-size: 17")
					}, currentPlace(0), currentPlace(1) + 1)

					currentPlace(0) += 1
					if (currentPlace(0) == 4) {
						currentPlace(0) = 0
						currentPlace(1) = 3
					}
				}

				this.addNewItem(new MinorHealingPotion)
				this.addNewItem(new MajorHealingPotion)
				this.addNewItem(new ExaltedHealingPotion)
				this.addNewItem(new CurseScroll)
				this.addNewItem(new FireBomb)
				this.addNewItem(new PoisonDart)
				this.addNewItem(new StrengthPotion)
				this.addNewItem(new ToughnessPotion)
			}

			val titleLabel = new Label("MAGASIN") {
				this.getTransforms().add(new Translate() { setX(715) })
				setStyle("-fx-text-fill: red; -fx-font-size: 40")
			}

			var exitButton = new Button("Retour à la carte du monde") {
				this.getTransforms().add(new Translate() { setX(620) })
				this.setPrefSize(350, 150)
				this.setFocusTraversable(false)
				this.setOnAction(_ => (new WorldMap).start(stage))
			}

			this.add(titleLabel, 1, 0)
			this.add(itemsDisplay, 1, 1)
			this.add(balanceLabel, 1, 2)
			this.add(exitButton, 1, 4)
		}

		var scene = new Scene(root, 1920, 1080)
        stage.setScene(scene)
	}
}