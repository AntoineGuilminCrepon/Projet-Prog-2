package fighterclasses

import attack._

/* Relation d'avantage : Melee -> Magic -> Range -> Melee */
object FighterClass {
	sealed trait EnumVal
	case object MeleeFighter extends EnumVal {override def toString() = {"Corps-à-corps"}}
	case object RangeFighter extends EnumVal {override def toString() = {"Distance"}}
	case object MagicFighter extends EnumVal {override def toString() = {"Magie"}}

	def compare(ally : AttackType.EnumVal, enemy : EnumVal) : Double = {
		(ally, enemy) match {
			case (AttackType.MeleeAttack, MeleeFighter) => 1.0
			case (AttackType.MeleeAttack, MagicFighter) => 1.5
			case (AttackType.MeleeAttack, RangeFighter) => 0.75
			case (AttackType.MagicAttack, MeleeFighter) => 0.75
			case (AttackType.MagicAttack, MagicFighter) => 1.0
			case (AttackType.MagicAttack, RangeFighter) => 1.5
			case (AttackType.RangeAttack, MeleeFighter) => 1.5
			case (AttackType.RangeAttack, MagicFighter) => 0.75
			case (AttackType.RangeAttack, RangeFighter) => 1.0
		}
	}
}

/* Types supplémentaires pour avoir des effets plus spécifiques */
object FighterType {
	sealed trait EnumVal
	case object Undead extends EnumVal {override def toString() = {"Mort-vivant"}}
	case object Gelatinous extends EnumVal {override def toString() = {"Gélatineux"}}

	def checkTypeResistance(fighterType : EnumVal, attackType : AttackType.EnumVal) : Double = {
		(fighterType, attackType) match {
			case (Gelatinous, AttackType.MeleeAttack) | (Gelatinous, AttackType.RangeAttack) => 0.5
			case (Gelatinous, AttackType.MagicAttack) => 2.0
			case _ => 1.0
		}
	}
}