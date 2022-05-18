package nodeshapes

import javafx.scene._
import javafx.scene.shape._
import javafx.scene.paint._

import nodemap._

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

class TriangleNode(coord : (Int, Int)) extends Group with NodeShape {
	val (x, y) = coord
	
	var lines = Array(
		new Line(x - 60, y - 25, x - 60, y + 25),
		new Line(x + 60, y - 25, x + 60, y + 25),
		new Line(x - 60, y - 25, x + 60, y + 25),
		new Line(x - 60, y + 25, x + 60, y - 25)
	)

	lines.foreach(this.getChildren().add(_))

	override def setColor(color : Color) = { lines.foreach(_.setStroke(color)) }
}

class NoneNode extends Group with NodeShape {}

class PathBetweenNodes(coord : (Int, Int), direction : Direction.EnumVal) extends Group {
	direction match {
		case Direction.Up => this.getChildren.addAll(new Line(coord._1 - 5, coord._2 - 25, coord._1 - 5, coord._2 - 325), new Line(coord._1 + 5, coord._2 - 25, coord._1 + 5, coord._2 - 325))
	  	case Direction.Down => this.getChildren.addAll(new Line(coord._1 - 5, coord._2 + 25, coord._1 - 5, coord._2 + 325), new Line(coord._1 + 5, coord._2 + 25, coord._1 + 5, coord._2 + 325))
		case Direction.Right => this.getChildren.addAll(new Line(coord._1 + 58, coord._2 - 5, coord._1 + 124, coord._2 - 5), new Line(coord._1 + 58, coord._2 + 5, coord._1 + 124, coord._2 + 5))
 		case _ => ()
	}
}