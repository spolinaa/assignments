package patterns.State_AC

/**
 * Created by Alexander Chebykin
 * */

private abstract class State(private val person: Person) {
    abstract fun act()
    abstract fun nextState(state : String)
}

private class AliveState(private val person: Person) : State(person) {
    override fun act() {
        println("I'm alive!\n")
        nextState("dead")
    }

    override fun nextState(state : String) {
        person.changeState(state)
    }
}

private class DeadState(private val person: Person) : State(person) {
    override fun act() {
        println("I'm dead!\n")
        nextState("zombie")
    }

    override fun nextState(state : String) {
        person.changeState(state)
    }
}

private class ZombieState(private val person: Person) : State(person) {
    var count = 0
    override fun act() {
        println("BRAAAAAINS")
        if (++count == 3) {
            nextState("alive")
            count = 0
            return
        }
        nextState("zombie")
    }

    override fun nextState(state : String) {
        println("Trying to become alive\n")
        person.changeState(state)
    }
}
public class Person() {

    private val states = hashMapOf(Pair("alive", AliveState(this)),
            Pair("dead", DeadState(this)), Pair("zombie", ZombieState(this)))

    private var currentState : State = states.getOrDefault("alive", AliveState(this))

    public fun changeState(newState : String){
        currentState = states.getOrDefault(newState, AliveState(this))
    }

    public fun act(){
        currentState.act()
    }
}

public fun main(args : Array<String>) {
    val human = Person()
    for (i in 0..9) human.act()
}