package nodeshapes

import javafx.scene._
import javafx.scene.shape._
import javafx.scene.paint._

trait NodeShape {
	def setColor(color : Color) : Unit = {}
}

class CrossNode(coord : (Int, Int)) extends Group with NodeShape {
	val x = coord._1
	val y = coord._2
	
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

class EllipseNode(coord : (Int, Int)) extends Group with NodeShape {
	val x = coord._1
	val y = coord._2

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

	override def setColor(color : Color) = {outerEllipse.setFill(color)}
}

class NoneNode extends Group with NodeShape {}

object Direction {
    sealed trait EnumVal
    case object Up extends EnumVal
    case object Right extends EnumVal
	case object Down extends EnumVal
	case object Left extends EnumVal
}

class PathBetweenNodes(coord1 : (Int, Int), coord2 : (Int, Int)) extends Group {
	var direction : Direction.EnumVal = if (coord1._1 == coord2._1) (if (coord1._2 <= coord2._2) Direction.Up else Direction.Down) else (if (coord1._1 <= coord2._1) Direction.Right else Direction.Left)

	direction match {
		case Direction.Up => this.getChildren.addAll(new Line(coord1._1 - 35, coord1._2 + 25, coord1._1 - 35, coord2._2 - 25), new Line(coord1._1 - 25, coord1._2 + 25, coord1._1 - 25, coord2._2 - 25))
		case Direction.Down => this.getChildren.addAll(new Line(coord1._1 - 35, coord1._2 - 25, coord1._1 - 35, coord2._2 + 25), new Line(coord1._1 - 25, coord1._2 - 25, coord1._1 - 25, coord2._2 + 25))
		case Direction.Right => this.getChildren.addAll(new Line(coord1._1 + 27, coord1._2 - 5, coord2._1 - 92, coord2._2 - 5), new Line(coord1._1 + 27, coord1._2 + 5, coord2._1 - 92, coord2._2 + 5))
		case Direction.Left => this.getChildren.addAll(new Line(coord1._1 - 92, coord1._2 - 5, coord2._1 + 27, coord2._2 - 5), new Line(coord1._1 - 92, coord1._2 + 5, coord2._1 + 27, coord2._2 + 5))

	}
}