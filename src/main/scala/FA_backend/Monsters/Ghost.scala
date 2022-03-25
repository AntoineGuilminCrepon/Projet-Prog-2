package ghost

import attack._
import attackeffect._
import fighter._


object Fear extends Attack {
    override def toString = "Peur"
    override val targetAlignment = FactionAlignment.Hero
    override val attackType : AttackType.EnumVal = AttackType.RangeAttack
    override val attackDifficulty = 1
    override val damageModifier = 1

    enemyEffect = Some(new Stun(1, 0.8))
}
