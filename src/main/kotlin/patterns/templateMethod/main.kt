/* Implementation of the Template method pattern
by Sokolova Polina */

package patterns.templateMethod

abstract class Guitar() {
    abstract public val amplification : String
    abstract public val strings : Int
    abstract public val role    : String
    abstract public fun tuner()

}

class GibsonLesPaul() : Guitar() {
    override val amplification = "Electric"
    override val strings = 6
    override val role = "Solo"
    override public fun tuner() {
        //tuning a guitar
    }
}

class FenderTelecaster() : Guitar() {
    override val amplification = "Electric"
    override val strings = 6
    override val role = "Rhythm"
    override public fun tuner() {
        //tuning a guitar
    }
}

class YamahaAPX700() : Guitar() {
    override val amplification = "Acoustic-electric"
    override val strings = 12
    override val role = "Rhythm"
    override public fun tuner() {
        //tuning a guitar
    }
}
