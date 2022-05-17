package items

import fighter._
import attackeffect._

abstract class Item(val name : String, val price : Int) {
	val effect : AttackEffect
	val imageURL : String
	val targetAlignment : FactionAlignment.EnumVal


	override def toString() : String = { name }
	def description() : String
	def use(user : Fighter, target : Fighter) : String
}

class MinorHealingPotion extends Item("Potion de soin mineure", 10) {
	val effect = new InstantHeal(1.0, 15)
	val imageURL = "/Items/minor_healing_potion.png"
	val targetAlignment = FactionAlignment.Hero
	
	def use(user : Fighter, target : Fighter) = {
		user + " utilise une potion de soin mineure sur " + target + ".\nIl gagne 15 points de vie."
	}

	def description() = { "Rend 15 points de vie à un allié" }
}

class MajorHealingPotion extends Item("Potion de soin majeure", 20) {
	val effect = new InstantHeal(1.0, 30)
	val imageURL = "/Items/major_healing_potion.png"
	val targetAlignment = FactionAlignment.Hero
	
	def use(user : Fighter, target : Fighter) = {
		user + " utilise une potion de soin majeure sur " + target + ".\nIl gagne 30 points de vie."
	}

	def description() = { "Rend 30 points de vie à un allié" }
}

class ExaltedHealingPotion extends Item("Potion de soin exaltée", 50) {
	val effect = new InstantHeal(1.0, 70)
	val imageURL = "/Items/exalted_healing_potion.png"
	val targetAlignment = FactionAlignment.Hero
	
	def use(user : Fighter, target : Fighter) = {
		user + " utilise une potion de soin exaltée sur " + target + ".\nIl gagne 70 points de vie."
	}

	def description() = { "Rend 70 points de vie à un allié" }
}

class CurseScroll extends Item("Parchemin de malédiction", 150) {
	val effect = new Curse(5, 1, 5)
	val imageURL = "/Items/curse_scroll.png"
	val targetAlignment = FactionAlignment.Monster

	def use(user : Fighter, target : Fighter) = {
		user + " jette une malédiction sur " + target + ".\nIl est désormais maudit !"
	}

	def description() = { "Maudit un ennemi et lui inflige des dégâts pendant quelques tours" }
}

class FireBomb extends Item("Bombe incendiaire", 100) {
	val effect = new Fire(3, 1, 3) {
		override def effectBeginning(myself : Fighter) = {
			myself.lifePoints = 0.max(myself.lifePoints - 5)
		}
	}
	val imageURL = "/Items/fire_bomb.png"
	val targetAlignment = FactionAlignment.Monster

	def use(user : Fighter, target : Fighter) = {
		user + " lance une bombe incendiaire sur " + target + ".\nIl perd 5 points de vie et est désormais enflammé !"
	}

	def description() = { "Inflige des dégâts à un ennemi et l'enflamme" }
}

class PoisonDart extends Item("Dards empoisonnés", 20) {
	val effect = new Poison(5, 1, 2)
	val imageURL = "/Items/poison_dart.png"
	val targetAlignment = FactionAlignment.Monster

	def use(user : Fighter, target : Fighter) = {
		user + " lance des dards empoisonnés sur " + target + ".\nIl est désormais empoisonné !"
	}

	def description() = { "Empoisonne un ennemi" }
}

class StrengthPotion extends Item("Potion de force", 50) {
	val effect = new Boost(5, 1, 0.2, 0)
	val imageURL = "/Items/strength_potion.png"
	val targetAlignment = FactionAlignment.Hero
	
	def use(user : Fighter, target : Fighter) = {
		user + " utilise une potion de force sur " + target + ".\nIl se sent désormais plus fort."
	}

	def description() = { "Augmente la force d'un allié de 20% pendant 5 tours" }
}

class ToughnessPotion extends Item("Potion d'endurance", 50) {
	val effect = new Boost(5, 1, 0, 0.3)
	val imageURL = "/Items/toughness_potion.png"
	val targetAlignment = FactionAlignment.Hero
	
	def use(user : Fighter, target : Fighter) = {
		user + " utilise une potion d'endurance sur " + target + ".\nIl se sent désormais plus résistant."
	}

	def description() = { "Augmente l'endurance d'un allié de 30% pendant 5 tours" }
}