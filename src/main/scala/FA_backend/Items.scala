package items

import attackeffect._

abstract class Item(val name : String, val price : Int) {
	val effect : AttackEffect

	override def toString() : String = { name }
}

class HealPotion(heal : Int) extends Item("Potion de soin", 2) {
	val effect = new InstantHeal(1.0, heal)
}