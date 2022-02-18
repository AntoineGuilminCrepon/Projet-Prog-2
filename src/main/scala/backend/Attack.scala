package attack

object AttackType {
    sealed trait EnumVal
    case object RangeAttack extends EnumVal
    case object MeleeAttack extends EnumVal
}

class AttackEffect {
    val timer : Int = 0

    val LPModificationEachTurn : Option[Int] = None
    val meleeCapacity : Option[Int] = None
    val rangeCapacity : Option[Int] = None
    val strength : Option[Int] = None
    val toughness : Option[Int] = None
    val initiative : Option[Int] = None
}

abstract class Attack {
    val targetAlignment : fighter.FactionAlignment.EnumVal

    val attackType : AttackType.EnumVal
    val attackDifficulty : Int
    val damageModifier : Int
    var attackEffect : Option[AttackEffect] = None
}