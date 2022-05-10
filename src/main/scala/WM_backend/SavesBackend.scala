package savesbackend

import java.io._
import scala.io._
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._, io.circe.generic.semiauto._

import javafx.scene.layout._
import javafx.scene._

import nodeshapes._
import nodemap._
import fighter._
import heroes._
import monsters._

object Saves {
	implicit val factionEncoder : Encoder[FactionAlignment.EnumVal] = new Encoder[FactionAlignment.EnumVal] {
		final def apply(faction : FactionAlignment.EnumVal) : Json = Json.fromString(faction.toString)
	}

	implicit val factionDecoder : Decoder[FactionAlignment.EnumVal] = new Decoder[FactionAlignment.EnumVal] {
		final def apply(c : HCursor) : Decoder.Result[FactionAlignment.EnumVal] = {
			c.as[String] match {
				case Right("Héros") => Right(FactionAlignment.Hero)
				case Right("Monstre") => Right(FactionAlignment.Monster)
				case x => println(x); throw new Exception("Erreur : Faction inconnue")
			}
		}
	}

	implicit val fighterEncoder : Encoder[Fighter] = new Encoder[Fighter] {
		final def apply(fighter : Fighter) : Json = Json.obj(
			("fighterID", Json.fromInt(fighter.fighterID)),
			("faction", fighter.faction.asJson),
			("classIndice", Json.fromInt(fighter.classIndice)),
			("maxLifePoints", Json.fromInt(fighter.maxLifePoints)),
			("lifePoints", Json.fromInt(fighter.lifePoints)),
			("meleeCapacity", Json.fromInt(fighter.meleeCapacity)),
			("rangeCapacity", Json.fromInt(fighter.rangeCapacity)),
			("magicCapacity", Json.fromInt(fighter.magicCapacity)),
			("strength", Json.fromInt(fighter.strength)),
			("toughness", Json.fromInt(fighter.toughness)),
			("initiative", Json.fromInt(fighter.initiative))
		)
	}

	implicit val fighterDecoder : Decoder[Fighter] = new Decoder[Fighter] {
		final def apply(c : HCursor) : Decoder.Result[Fighter] = {
			c.downField("faction").as[FactionAlignment.EnumVal] match {
				case Right(FactionAlignment.Hero) => println("TEST"); Right(Heroes.getRandomHero(0))
				case _ => println("TEST2"); Right(Heroes.getRandomHero(0))
			}
		}
	}

	implicit val arrayFighterEncoder : Encoder[Array[Fighter]] = new Encoder[Array[Fighter]] {
		final def apply(fighterArray : Array[Fighter]) : Json = Json.fromValues(fighterArray.map(fighter => fighter.asJson))
	}

	implicit val arrayFighterDecoder : Decoder[Array[Fighter]] = Decoder.decodeArray

	def makeSave(nodeMap : NodeMap, map : Pane, nodeGraph : Array[Array[Node with NodeShape]], heroes : Array[Fighter]) : Unit = {
		val fighterWriter = new PrintWriter(new File("src/main/resources/Save/fighters.json"))
		
		fighterWriter.write(heroes.asJson.toString)
		fighterWriter.close()
		
		val fighterSave = Source.fromFile("src/main/resources/Save/fighters.json").mkString
		val parseFighters = parse(fighterSave)
		parseFighters match {
			case Left(x) => println("ERROR")
			case Right(x) => println(decode[Array[Fighter]](fighterSave))
		}

	}
}