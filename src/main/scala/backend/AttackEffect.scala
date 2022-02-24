package attackeffect

import fighter.Fighter

class AttackEffect(tmr : Int, pbty : Double) {
    override def toString () : String = {"Effet"} 

    var timer = tmr
    val probability = pbty
    var savedValue : List[Int] = List()

    def effectBeginning(myself : Fighter) : Unit = {}
    def effectBeforeAttack(myself : Fighter) : String = {""}
    def effectAfterAttack(myself : Fighter) : String = {""}
    def effectEnding(myself : Fighter) : String = {""}
}

class Bleed(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Saignement"}
    
    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return "Il saigne et perd 2 PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return "Le saignement s'arrête."
    }
}

class Stun(timer : Int, probability : Double) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Étourdi"}
    
    override def effectBeginning(myself : Fighter) : Unit = {
        savedValue = List(myself.meleeCapacity, myself.rangeCapacity)
        myself.meleeCapacity = 0
        myself.rangeCapacity = 0
    }

    override def effectAfterAttack(myself : Fighter) : String = {
        return "Il est sonné !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        myself.rangeCapacity = savedValue.last
        myself.meleeCapacity = savedValue.init.last
        return ""
    }
}