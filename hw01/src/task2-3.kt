abstract class Tree {}
open class Empty() : Tree() {}
open class Leaf(val value : Int) : Tree() {}
open class Node(val value : Int, val l : Tree, val r : Tree) : Tree() {}

fun genTree(lValue: Int, rValue: Int) : Tree {
    if (lValue > rValue) { return Empty() }
    if (lValue == rValue - 1) { return Leaf(lValue) }

    val middle = lValue + (rValue - lValue) / 2
    return Node(middle, genTree(lValue, middle - 1), genTree(middle + 1, rValue))
}

fun Tree.toText() : String {
    fun Tree.toText_() : List<String> {
        when (this) {
            is Empty -> return listOf("_\n")
            is Leaf  -> return listOf("${this.value}\n")
            is Node  -> {
                val lText = l.toText_().map { "| $it"}
                val rText = r.toText_().map { "| $it"}
                val vText = listOf("$value\n")
                return lText + vText + rText
            }
            else -> throw Exception("Unknown class")
        }
    }
    val builder = StringBuilder()
    val lines = this.toText_()
    lines.forEach { builder.append(it) }
    return builder.toString()
}

fun Tree.foldNew(acc: Int, f: (Int, Int) -> Int): Int {
    return when(this) {
        is Empty -> acc
        is Leaf -> f(acc, this.value)
        is Node -> this.l.foldNew(
                this.r.foldNew(
                        f(acc, this.value), f), f)
        else -> throw Exception("Wrong class")
    }
}

fun Tree.fold(fEmpty : Int, fLeaf : (Int) -> Int, fNode : (Int, Int, Int) -> Int) : Int {
    when (this) {
        is Empty -> return fEmpty
        is Leaf  -> return fLeaf(value)
        is Node  -> return fNode(value,
                l.fold(fEmpty, fLeaf, fNode),
                r.fold(fEmpty, fLeaf, fNode))
        else -> throw Exception("Unknown class")
    }
}

fun Tree.maxpath(): Int {
    //fun max { return if(a > b) (c + a) else (c + b)}
    return this.fold(0, {it}, {
        c: Int, a: Int, b: Int ->
        if(a > b)
            (c + a)
        else
            (c + b)
    })
}

