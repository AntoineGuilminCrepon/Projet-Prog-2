package savesbackend

import java.io._
import scala.io._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._, io.circe.generic.semiauto._

import javafx.scene.layout._
import javafx.scene._

import nodeshapes._
import nodemap._
import fighter._

object Saves {
	implicit val fighterEncoder : Encoder[Fighter] = new Encoder[Fighter] {
		final def apply(fighter : Fighter) : Json = Json.obj(
			("fighterID", Json.fromInt(fighter.fighterID))
		)
	}

	implicit val arrayFighterEncoder : Encoder[Array[Fighter]] = new Encoder[Array[Fighter]] {
		final def apply(fighterArray : Array[Fighter]) : Json = Json.fromValues(fighterArray.map(fighter => fighter.asJson))
	}

	def makeSave(nodeMap : NodeMap, map : Pane, nodeGraph : Array[Array[Node with NodeShape]], heroes : Array[Fighter]) : Unit = {
		val fighterWriter = new PrintWriter(new File("src/main/resources/Save/fighters.json"))
		
		fighterWriter.write(heroes.asJson.toString)
		fighterWriter.close()
	}
}