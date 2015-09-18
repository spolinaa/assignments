package hw02


/**
 * Created by Alexander on 17.09.2015.
 */
/*
open class Graph<A> (verges : Array<Pair<List<Int>, A>>) {
    //nodes are enumerated by ints
    val verges = verges
    fun Graph<A>.isWay(beg: Int, end: Int) : Boolean {
        return verges[beg].first.contains(end)
    }
    fun Graph<A>.valAt(ind : Int) : A {
        return verges[ind].second
    }
    fun Graph<A>.size() : Int {
        return verges.lastIndex
    }
}

class Net (verges : Array<Pair<List<Int>, Computer>>, OSs : Array<String>)
: Graph<Computer> (verges : Array<Pair<List<Int>, Computer>>) {
    val net = verges
    init
    {
        for (i in verges) {
            i.second =
        }
    }
}
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

class Net (verges : Array<List<Int>>, OSs : Array<String>)
: Graph<Computer> (verges : Array<List<Int>>, vals : Array<String>) {
    val net = verges
    init
    {
        for (i in verges) {
            i.second =
        }
    }
}
class Computer (os : String) {
    val name = os
    var infection = 0
    val diseaseChance =
            when (name) {
                "Windows" -> 0.6
                "OS X"    -> 0.4
                "Linux"   -> 0.1
                else      -> 0.5
            }
}

*/