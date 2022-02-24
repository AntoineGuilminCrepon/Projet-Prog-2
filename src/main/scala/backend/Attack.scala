package attack

import fighter.Fighter

object AttackType {
    sealed trait EnumVal
    case object RangeAttack extends EnumVal
    case object MeleeAttack extends EnumVal
}

class AttackEffect(tmr : Int, pbty : Double) {
    override def toString () : String = {"Effet"} 

    var timer = tmr
    val probability = pbty
    var savedValue : List[Int] = List()

    def effectBeginning(myself : Fighter) : Unit = {}
    def effectEachTurn(myself : Fighter) : String = {""}
    def effectEnding(myself : Fighter) : String = {""}
}

abstract class Attack {
    val targetAlignment : fighter.FactionAlignment.EnumVal
    def description() : String = {"attaque"}

    val attackType : AttackType.EnumVal
    val attackDifficulty : Int
    val damageModifier : Int
    var attackEffect : Option[AttackEffect] = None
}

class Bleed(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Saignement"}
    
    override def effectEachTurn(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return "Il saigne et perd 2 PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return "Le saignement s'arrête."
    }
}

class Stun(timer : Int, probability : Double) extends AttackEffect(timer + 1, probability) {
    override def toString() : String = {"Étourdi"}
    
    override def effectBeginning(myself : Fighter) : Unit = {
        savedValue = List(myself.meleeCapacity, myself.rangeCapacity)
        myself.meleeCapacity = 0
        myself.rangeCapacity = 0
    }

    override def effectEachTurn(myself : Fighter) : String = {
        return "Il est sonné !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        myself.rangeCapacity = savedValue.last
        myself.meleeCapacity = savedValue.init.last
        return ""
    }
}