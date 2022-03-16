package nodeshapes

import javafx.scene._
import javafx.scene.shape._
import javafx.scene.paint._

trait NodeShape {
	def setColor(color : Color) : Unit = {}
}

class CrossNode(x : Int, y : Int) extends Group with NodeShape {
	var lines = Array(
		new Rectangle(x - 60, y - 25, 120, 50) {setFill(Color.BLACK)}, 
		new Line(x - 60, y - 25, x + 60, y + 25),
		new Line(x - 60, y + 25, x + 60, y - 25))

	this.getChildren.addAll(lines(0), new Rectangle(x - 59, y - 24, 118, 48) {setFill(Color.WHITE)}, lines(1), lines(2))

	override def setColor(color : Color) = {
		lines(0).setFill(color)
		lines(1).setStroke(color)
		lines(2).setStroke(color)
	}
}

class EllipseNode(x : Int, y : Int) extends Group with NodeShape {
	var outerEllipse = new Ellipse()
	var innerEllipse = new Ellipse()

	outerEllipse.setCenterX(x)
	outerEllipse.setCenterY(y)
	outerEllipse.setRadiusX(60)
	outerEllipse.setRadiusY(25)

	innerEllipse.setCenterX(x)
	innerEllipse.setCenterY(y)
	innerEllipse.setRadiusX(59)
	innerEllipse.setRadiusY(24)

	outerEllipse.setFill(Color.BLACK)
	innerEllipse.setFill(Color.WHITE)

	this.getChildren.addAll(outerEllipse, innerEllipse)

	override def setColor(color : Color) = {outerEllipse.setStroke(color)}
}

class NoneNode extends Group with NodeShape {}