package items

import fighter._
import attackeffect._

abstract class Item(val name : String, val price : Int) {
	val effect : AttackEffect
	val imageURL : String
	val targetAlignment : FactionAlignment.EnumVal

	def description(user : Fighter, target : Fighter) : String
	override def toString() : String = { name }
}

class HealPotion(heal : Int) extends Item("Potion de soin", 2) {
	val effect = new InstantHeal(1.0, heal)
	val imageURL = "/Fighters/goblin.png"
	val targetAlignment = FactionAlignment.Hero
	
	def description(user : Fighter, target : Fighter) = {
		user + " utilise une potion de soin sur " + target + ".\nIl gagne " + heal + " points de vie."
	}
}