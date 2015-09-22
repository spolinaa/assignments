package hw02

import java.util.*


/**
 * Created by Alexander on 17.09.2015.
 */
/*
open class Graph<A> (verges : Array<List<Int>>, vals : Array<A>) {
    //nodes are enumerated by ints
    val verges = verges
    val vals = vals
    fun Graph<A>.isWay(beg: Int, end: Int) : Boolean {
        return verges[beg].contains(end)
    }
    fun Graph<A>.valAt(ind : Int) : A {
        return vals[ind]
    }
    fun Graph<A>.size() : Int {
        return vals.lastIndex
    }
}
*/

internal class ManagableRandom() {
    private val a = Random()
    public fun getRandNum(randVal: Int): Double {
        when (randVal) {
            1 -> return 1.0
            0 -> return 0.0
            else -> return a.nextDouble().mod(1)
        }
    }
}

//fun vergesToAdjacencyList (verges : Array<Pair<Int, Int>>) : Array<List<Int>> {

class Net (verges : Array<List<Int>>, OSs : Array<String>) {
    val net = verges
    private var tempComps = linkedListOf(Computer(OSs[0]))
    init
    {
        var i = 1
        while (i <= OSs.lastIndex) {
            //does it make any sense to add to the beginning without 'push'?
            tempComps.add(Computer(OSs[i]))
            i++
        }
    }
    public val comps = tempComps.reversed().toTypedArray()

    private fun isWay (beg: Int, end : Int) : Boolean {
        return net[beg].contains(end)
    }
    public fun makeNextStep(randVal: Int) {
        val rand = ManagableRandom()

        for (i in comps.indices)
            if (comps[i].infection == 2)
                for (j in comps.indices)
                    if ((isWay (i, j)) && (comps[j].infection == 0)
                            &&(rand.getRandNum(randVal) < comps[j].infectionChance) )
                        comps[j].infection = 1

        for (i in comps)
            if (i.infection == 1) i.infection = 2
    }
    internal fun print() {
        println("""
        (0. Windows ${comps[0].infectionWord()}) --> (1. Linux   ${comps[1].infectionWord()}) --> (2. OS X ${comps[2].infectionWord()}) --> (3. Linux    ${comps[3].infectionWord()})
        |                  |                       |
        |                  |                       |
        \/                 \/                       \/
        (6. Windows     ${comps[6].infectionWord()}) --> (4. Unknown    ${comps[4].infectionWord()}) <---(5. Windows   ${comps[5].infectionWord()})
        |
        |
        \/
        (7. OS X ${comps[7].infectionWord()})
        \n\n""")
    }
    public fun infectionToList() : List<Int> {
        val a = linkedListOf() : LinkedList<Int>
        for (i in comps)
            a.add(i.infection)
        return a
    }
}
public class Computer (os : String) {
    public  val name            = os
    public  var infection       = 0
    public  var infectionChance = 1.0//temporary
    init {
        infectionChance =
                when (name) {
                "Windows" -> 0.6
                "OS X" -> 0.4
                "Linux" -> 0.1
                else -> 0.5
            }
    }
    fun infectionWord() : String {
        when(infection) {
            0 -> return "Healthy"
            1 -> return "Infected"
            else -> return "An error has occured"
        }
    }
}

fun main(args : Array<String>) {
    val vers = arrayOf(listOf(1), listOf(2, 6), listOf(3, 4),
            listOf(5), listOf(), listOf(4), listOf(4, 7))
    val names = arrayOf("Windows", "Linux", "OS X", "Linux", "Unknown", "Windows", "Windows", "OS X")
    val net = Net(vers, names)
    net.print()
}
