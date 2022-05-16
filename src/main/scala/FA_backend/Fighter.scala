package fighter

import java.io.InputStream
import io.circe._

import fighterclasses._
import attack._
import attackeffect._
import math.rint

object FactionAlignment {
    sealed trait EnumVal
    case object Hero extends EnumVal {override def toString() : String = {"Héros"}}
    case object Monster extends EnumVal {override def toString() : String = {"Monstre"}}
}

abstract class Fighter(val fighterID : Int, val classIndice : Int, val faction : FactionAlignment.EnumVal, val fighterClass : FighterClass.EnumVal) {
	val fighterTypes : Array[FighterType.EnumVal] = Array()

	var level : Int = 1
	var exp : Int = 0

    var maxLifePoints : Int
    var lifePoints : Int

    var meleeCapacity : Int
    var rangeCapacity : Int
	var magicCapacity : Int = 0

    var strength : Int
    var toughness : Int
    var initiative : Int

    val visual : InputStream
    val attacks : Array[Attack]

    var effects : List[AttackEffect] = List()

    /* Renvoie un booléen correspond pour savoir si le combattant est en vie */
    def isLiving() : Boolean = {
        return this.lifePoints > 0
    }

    /* Renvoie vrai si le combattant est un Héros */
    def isHero() : Boolean = {
        this.faction match {
            case FactionAlignment.Hero => true
            case FactionAlignment.Monster => false
        }
    }

    /* Renvoie le nombre de dégâts infligés par l'attaque */
    def fight(enemy : Fighter, attack : Attack) : Int = {
        return (enemy.fighterTypes.foldLeft(1.0)(_ * FighterType.checkTypeResistance(_, attack.attackType)) * (attack.attackType match {
					case AttackType.MagicAttack => attack.damageModifier * attack.damageModifier / enemy.toughness.toFloat
					case _ => FighterClass.compare(attack.attackType, enemy.fighterClass) * this.strength * attack.damageModifier / enemy.toughness.toFloat
				}).toFloat).toInt
    }

	def upgradeStats() : Unit
	def upgradeAttacks() : Unit
	
	/* Augmente d'un niveau */
	def levelUp() = {
		this.level += 1
		this.upgradeStats()
		this.lifePoints = this.maxLifePoints

		if (level % 5 == 0) {
			this.upgradeAttacks()
			this.attacks.foreach(_.updateEffects())
		}
	}

	/* Augmente d'autant de niveaux que nécessaire pour arriver à newLevel */
	def levelUp(newLevel : Int) : Unit = {
		while (this.level < newLevel) {
			this.levelUp()
		}
	}

	/* Défini la quantité d'XP donnée à sa mort (utile pour les monstres) */
	def expRewarded() : Int = {
		return ((this.strength + this.toughness + this.initiative) * this.level
			+ this.attacks.foldLeft(0)(((acc, attack) => acc + attack.damageModifier + (if (attack.enemyEffect.isDefined) attack.enemyEffect.get.expectedDamages else 0))))
	}

	/* Renvoie vrai si le combattant a augmenté de niveau */
	def checkLevelUp() : Boolean = {
		val log9 = (x : Double) => math.log10(x) / math.log10(9.0)
		val expNeeded = (x : Int) => 45 * x * (log9(x + 8) * log9(x + 8)).toInt
		val previousLevel = this.level

		while (this.exp >= expNeeded(this.level)) {
			this.exp -= expNeeded(this.level)
			this.levelUp()
		}

		return this.level != previousLevel
	}
}