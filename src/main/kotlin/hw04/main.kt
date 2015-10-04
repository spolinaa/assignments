/* A set interface class with 2 implementations
(expectation: 5 h; reality: 8 h)
by Sokolova Polina*/

package hw04

interface MySet {
    public fun insert(a : Int) : MySet
    public fun delete(a : Int) : MySet
    public fun search(a : Int) : Boolean
    //public fun union(t : MySet) : MySet
    //public fun intersection(t : MySet) : MySet
    public fun treeToString() : String
}

class Empty() : Tree() {}
class Node(val value: Int, val l: Tree, val r: Tree): Tree() {}
open class Tree() : MySet {
    internal fun height       () : Int = if (this is Node) (1 + Math.max(this.l.height(), this.r.height())) else 0
    internal fun balanceFactor() : Int = if (this is Node) (this.l.height() - this.r.height()) else 0
    override public fun treeToString() : String {
        when (this) {
            is Node  -> {
                val lRes = this.l.treeToString()
                val rRes = this.r.treeToString()
                return "Node(${this.value}, $lRes, $rRes)"
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
            cValue = this.value
            L = this.l
            if (this.r is Node) {
                rValue = this.r.value
                lR = this.r.l
                rR = this.r.r
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
            R = this.r
            cValue = this.value
            if (this.l is Node) {
                lValue = this.l.value
                lL = this.l.l
                rL = this.l.r
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
                        val right = this.r.smallRightRotation()
                        val tree = Node(this.value, this.l, right)
                        return tree.smallLeftRotation()
                    }
                    else -> { return this.smallLeftRotation() }
                }
            }
            if (balance == 2) {
                when {
                    this.l.balanceFactor() < 0 -> {
                        val left = this.l.smallLeftRotation()
                        val tree = Node(this.value, left, this.r)
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
                    a < this.value -> { return Node(this.value, this.l.insert(a), this.r).balance() }
                    a > this.value -> { return Node(this.value, this.l, this.r.insert(a)).balance() }
                    else -> { return this }
                }
            }
        }
        return Empty()
    }
    internal fun minInRight() : Int {
        if (this is Node) {
            when (this.l) {
                is Empty -> { return this.value }
                is Node  -> { return this.l.minInRight() }
            }
        }
        return 0
    }
    internal fun replace(a : Int, b : Int) : Tree {
        if (this is Node) {
            when {
                a < this.value -> { return Node(this.value, this.l.replace(a, b), this.r) }
                a > this.value -> { return Node(this.value, this.l, this.r.replace(a, b)) }
                else -> { return Node(b, this.l, this.r) }
            }
        }
        return Empty()
    }
    override fun delete(a : Int) : Tree {
        if (this is Node) {
            when {
                a < this.value -> { return Node(this.value, this.l.delete(a), this.r).balance() }
                a > this.value -> { return Node(this.value, this.l, this.r.delete(a)).balance() }
                else -> {
                    when (this.r) {
                        is Empty -> { return this.l }
                        is Node -> {
                            val minValue = this.r.minInRight()
                            val resTree = Node(this.value, this.l, this.r.delete(minValue))
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
            return (a == this.value) || this.l.search(a) || this.r.search(a)
        }
        return false
    }
    internal fun <B> fold(fEmpty: B, fNode: (Int, B, B) -> B) : B {
        when (this) {
            is Empty -> { return fEmpty }
            is Node  -> { return fNode(this.value, this.l.fold(fEmpty, fNode),
                    this.r.fold(fEmpty, fNode)) }
        }
        return fEmpty
    }
    public fun toText(): List<String> {
        return fold(listOf("-\n")) { value: Int, lRes: List<String>, rRes: List<String> ->
            val lText = lRes.map { "| $it" }
            val rText = rRes.map { "| $it" }
            val vText = listOf("$value\n")
            lText + vText + rText
        }
    }
    public fun union(t : Tree) : Tree {
        if (this is Node) {
            var res = t.insert(this.value)
            res = res.union(this.l)
            res = res.union(this.r)
            return res
        }
        return t
    }

    public fun intersection(t : Tree) : Tree {
        when (this) {
            is Empty -> { return t }
            is Node  -> {
                if (t is Node) {
                    val res = this.delete(this.value)
                    return res.intersection(t)
                }
            }
        }
        return this
    }
}

fun main(args : Array<String>) {}