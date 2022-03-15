package nodeshapes

import javafx.scene._
import javafx.scene.shape._
import javafx.scene.paint._

class CrossNode(x : Int, y : Int) extends Group {
	this.getChildren.add(new Rectangle(x - 60, y - 25, 120, 50) {setFill(Color.BLACK)})
	this.getChildren.add(new Rectangle(x - 59, y - 24, 118, 48) {setFill(Color.WHITE)})
	this.getChildren.add(new Line(x - 60, y - 25, x + 60, y + 25))
	this.getChildren.add(new Line(x - 60, y + 25, x + 60, y - 25))
}

class EllipseNode(x : Int, y : Int) extends Ellipse {
	this.setCenterX(x)
	this.setCenterY(y)
	this.setRadiusX(60)
	this.setRadiusY(25)
}