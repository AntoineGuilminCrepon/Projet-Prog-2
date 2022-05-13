package attack

import attackeffect._

object AttackType {
    sealed trait EnumVal
    case object RangeAttack extends EnumVal {override def toString() = {"Distance"}}
    case object MeleeAttack extends EnumVal {override def toString() = {"Corps à corps"}}
	case object MagicAttack extends EnumVal {override def toString() = {"Magie"}}
}

class Attack(
	val name : String,
	val targetAlignment : fighter.FactionAlignment.EnumVal,
	val attackType : AttackType.EnumVal,
	var attackDifficulty : Int,
	var damageModifier : Int) {

		override def toString() : String = {name}

   	 	var allyEffect : Option[AttackEffect] = None
   	 	var enemyEffect : Option[AttackEffect] = None
		
		def updateEffects() = {}
}