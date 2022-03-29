package attackeffect

import fighter.Fighter
import fighterclasses._

abstract class AttackEffect(tmr : Int, pbty : Double) {
    override def toString () : String = {"Effet"} 
	val immuneTypes : Array[FighterType.EnumVal] = Array()
	val expectedDamages : Int = 0

    var timer = tmr
    val probability = pbty
    var savedValues : List[Int] = List()

    def effectBeginning(myself : Fighter) : Unit = {}
    def effectBeforeAttack(myself : Fighter) : String = {""}
    def effectAfterAttack(myself : Fighter) : String = {""}
    def effectEnding(myself : Fighter) : String = {""}
}

class Acid(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Corrodé"}
	override val immuneTypes = Array(FighterType.NonPhysical)
    override val expectedDamages = (damage * timer * probability).toInt

    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return " est brûle par l'acide et perd " + damage + " PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return " parvient à enlever l'acide."
    }
}

class Bleed(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Saignement"}
	override val immuneTypes = Array(FighterType.Undead, FighterType.Gelatinous, FighterType.NonPhysical)
	override val expectedDamages = (damage * timer * probability).toInt

    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return " saigne et perd " + damage + " PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return " ne saigne plus."
    }
}

class CapacitiesDebuf(timer : Int, probability : Double, ccDebuf : Int, ctDebuf : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Engourdi"}

    override def effectBeginning(myself : Fighter) = {
        myself.meleeCapacity -= ccDebuf
        myself.rangeCapacity -= ctDebuf
    }

    override def effectEnding(myself : Fighter) : String = {
        myself.meleeCapacity += ccDebuf
        myself.rangeCapacity += ctDebuf

        return ""
    }
}

class Curse(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Maudit"}
    override val expectedDamages = (damage * timer * probability).toInt

    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
		return " est maudi et perd " + damage + " PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return " voit sa malédiction levée."
    }
}

class Fire(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Enflammé"}
	override val immuneTypes = Array(FighterType.NonPhysical)
    override val expectedDamages = (damage * timer * probability).toInt

    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return " brûle et perd " + damage + " PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return " n'est plus brûlé."
    }
}

class Poison(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Empoisonné"}
	override val immuneTypes = Array(FighterType.NonPhysical)
    override val expectedDamages = (damage * timer * probability).toInt

    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return " est empoisonné et perd " + damage + " PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return " guérit du poison."
    }
}

class Stun(timer : Int, probability : Double) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Étourdi"}
    
    override def effectBeginning(myself : Fighter) : Unit = {
        savedValues = List(myself.meleeCapacity, myself.rangeCapacity)
        myself.meleeCapacity = -10
        myself.rangeCapacity = -10
    }

    override def effectAfterAttack(myself : Fighter) : String = {
        return " est sonné !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        myself.rangeCapacity = savedValues.last
        myself.meleeCapacity = savedValues.init.last
        return ""
    }
}