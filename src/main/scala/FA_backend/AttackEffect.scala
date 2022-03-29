package attackeffect

import fighter.Fighter

class AttackEffect(tmr : Int, pbty : Double) {
    override def toString () : String = {"Effet"} 

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
    
    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return "L'acide le brûle et il perd " + damage + " PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return "Il parvient à enlever l'acide."
    }
}

class Bleed(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Saignement"}
    
    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return "Il saigne et perd " + damage + " PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return "Le saignement s'arrête."
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

class Fire(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Enflammé"}
    
    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return "Il brûle et perd " + damage + " PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return "Le feu s'éteint."
    }
}

class Poison(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Empoisonné"}
    
    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return "Il est empoisonné et perd " + damage + " PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return "Il guérit du poison."
    }
}

class Curse(timer : Int, probability : Double, damage : Int) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Maudit"}
    
    override def effectAfterAttack(myself : Fighter) : String = {
        myself.lifePoints -= damage
        return "La malédiction frappe. - " + damage + " PV !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        return "La malédiction est levée."
    }
}

class Stun(timer : Int, probability : Double) extends AttackEffect(timer, probability) {
    override def toString() : String = {"Étourdi"}
    
    override def effectBeginning(myself : Fighter) : Unit = {
        savedValues = List(myself.meleeCapacity, myself.rangeCapacity)
        myself.meleeCapacity = 0
        myself.rangeCapacity = 0
    }

    override def effectAfterAttack(myself : Fighter) : String = {
        return "Il est sonné !"
    }
    
    override def effectEnding(myself : Fighter) : String = {
        myself.rangeCapacity = savedValues.last
        myself.meleeCapacity = savedValues.init.last
        return ""
    }
}