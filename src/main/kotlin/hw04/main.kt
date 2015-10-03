/* A set interface class with 2 implementations
(expectation: 5 h; reality: 8 h)
by Sokolova Polina*/

package hw04

interface MySet {
    public fun insert(a : Int) : MySet
    public fun delete(a : Int) : MySet
    public fun search(a : Int) : Boolean
    public fun union(t : MySet) : MySet
    public fun intersection(t : MySet) : MySet
    public fun treeToString() : String
}

class Empty() : Tree() {}
class Node(val value: Int, val l: Tree, val r: Tree): Tree() {}
open class Tree() : MySet {
    internal fun height() : Int = if (this is Node) (1 + Math.max(l.height(), r.height())) else 0
    internal fun balanceFactor() : Int = if (this is Node) (l.height() - r.height()) else 0
    override public fun treeToString() : String {
        when (this) {
            is Node  -> {
                val lRes = l.treeToString()
                val rRes = r.treeToString()
                return "Node($value, $lRes, $rRes)"
            }
            else -> { return "Empty" }
        }
    }
    internal fun smallLeftRotation() : Tree {
        var L : Tree = Empty()
        var cValue : Int = 0
        var rValue : Int = 0
        var lR : Tree = Empty()
        var rR : Tree = Empty()
        if (this is Node) {
            cValue = value
            L = l
            if (r is Node) {
                rValue = r.value
                lR = r.l
                rR = r.r
            }
        }
        return Node(rValue, Node(cValue, L, lR), rR)
    }
    internal fun smallRightRotation() : Tree {
        var R : Tree = Empty()
        var cValue : Int = 0
        var lValue : Int = 0
        var lL : Tree = Empty()
        var rL : Tree = Empty()
        if (this is Node) {
            R = r
            cValue = value
            if (l is Node) {
                lValue = l.value
                lL = l.l
                rL = l.r
            }
        }
        return Node(lValue, lL, Node(cValue, rL, R))
    }
    internal fun balance() : Tree {
        if (this is Node) {
            val balance = this.balanceFactor()
            if (balance == -2) {
                when {
                    this.r.balanceFactor() > 0 -> {
                        val right = r.smallRightRotation()
                        val tree = Node(value, l, right)
                        return tree.smallLeftRotation()
                    }
                    else -> { return this.smallLeftRotation() }
                }
            }
            if (balance == 2) {
                when {
                    this.l.balanceFactor() < 0 -> {
                        val left = l.smallLeftRotation()
                        val tree = Node(value, left, r)
                        return tree.smallRightRotation()
                    }
                    else -> { return this.smallRightRotation() }
                }
            }
        }
        return this
    }
    override fun insert(a : Int) : Tree {
        when (this) {
            is Empty -> { return Node(a, Empty(), Empty()) }
            is Node -> {
                when {
                    a < value -> { return Node(value, l.insert(a), r).balance() }
                    a > value -> { return Node(value, l, r.insert(a)). balance() }
                    else -> { return this }
                }
            }
        }
        return Empty()
    }
    internal fun minInRight() : Int {
        if (this is Node) {
            when (l) {
                is Empty -> { return value }
                is Node  -> { return l.minInRight() }
            }
        }
        return 0
    }
    internal fun replace(a : Int, b : Int) : Tree {
        if (this is Node) {
            when {
                a < value -> { return Node(value, l.replace(a, b), r) }
                a > value -> { return Node(value, l, r.replace(a, b)) }
                else -> { return Node(b, l, r) }
            }
        }
        return Empty()
    }
    override fun delete(a : Int) : Tree {
        if (this is Node) {
            when {
                a < value -> { return Node(value, l.delete(a), r).balance() }
                a > value -> { return Node(value, l, r.delete(a)).balance() }
                else -> {
                    when (r) {
                        is Empty -> { return l }
                        is Node -> {
                            val minValue = r.minInRight()
                            val resTree = Node(value, l, r.delete(minValue))
                            return resTree.replace(a, minValue).balance()
                        }
                    }
                }
            }
        }
        return Empty()
    }
    override fun search(a : Int) : Boolean {
        if (this is Node) {
            return (a == value) || l.search(a) || r.search(a)
        }
        return false
    }
    internal fun <B> fold(fEmpty: B, fNode: (Int, B, B) -> B) : B {
        when (this) {
            is Empty -> { return fEmpty }
            is Node  -> { return fNode(value, l.fold(fEmpty, fNode),
                    r.fold(fEmpty, fNode)) }
        }
        return fEmpty
    }
    public fun toText(): List<String> {
        return fold(listOf("-\n")) {(value, lRes, rRes) ->
            val lText = lRes.map { "| $it" }
            val rText = rRes.map { "| $it" }
            val vText = listOf("$value\n")
            lText + vText + rText
        }
    }
    override public fun union(t : MySet) : Tree {
        if (this is Node) {
            var res = t.insert(this.value)
            res = res.union(l)
            res = res.union(r)
            return res as Tree
        }
        return t as Tree
    }

    override public fun intersection(t : MySet) : Tree {
        when (this) {
            is Empty -> { return t as Tree }
            is Node  -> {
                if (t is Node) {
                    val res = this.delete(value)
                    return res.intersection(t)
                }
            }
        }
        return this
    }
}

fun main(args : Array<String>) {
    var tree : Tree = Empty()
    println(tree.height())
}