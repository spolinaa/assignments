/* AVL trees
(expectation: 3 h; reality: 10 h)
by Sokolova Polina */

package hw03

abstract class Tree {}
class Empty() : Tree() {}
class Leaf(val value: Int): Tree() {}
class Node(val value: Int, val l: Tree, val r: Tree): Tree() {}

fun Tree.height(acc : Int) : Int {
    when (this) {
        is Empty -> { return acc }
        is Node  -> {
            val lHeight = l.height(acc + 1)
            val rHeight = r.height(acc + 1)
            if (lHeight > rHeight) { return lHeight }
            else { return rHeight }
        }
    }
    return acc
}

fun Tree.balanceFactor() : Int {
    when (this) {
        is Empty -> { return 0 }
        is Node  -> { return (l.height(0) - r.height(0)) }
    }
    return 0
}

fun Tree.treeToString() : String {
    when (this) {
        is Empty -> { return "Empty" }
        is Node  -> {
            val lRes = l.treeToString()
            val rRes = r.treeToString()
            return "Node($value, $lRes, $rRes)"
        }
    }
    return "Empty"
}

fun Tree.smallLeftRotation() : Tree {
    var L : Tree = Empty()
    var cValue : Int = 0
    var rValue : Int = 0
    var lR : Tree = Empty()
    var rR : Tree = Empty()
    when (this) {
        is Node -> {
            cValue = value
            L = l
            when (r) {
                is Node -> {
                    rValue = r.value
                    lR = r.l
                    rR = r.r
                }
            }
        }
    }
    return Node(rValue, Node(cValue, L, lR), rR)
}

fun Tree.smallRightRotation() : Tree {
    var R : Tree = Empty()
    var cValue : Int = 0
    var lValue : Int = 0
    var lL : Tree = Empty()
    var rL : Tree = Empty()
    when (this) {
        is Node -> {
            R = r
            cValue = value
            when (l) {
                is Node -> {
                    lValue = l.value
                    lL = l.l
                    rL = l.r
                }
            }
        }
    }
    return Node(lValue, lL, Node(cValue, rL, R))
}

fun Tree.balance() : Tree {
    when (this) {
        is Empty -> { return Empty() }
        is Node  -> {
            val balance = this.balanceFactor()
            if (balance == -2) {
                if (this.r.balanceFactor() <= 0) {
                    return this.smallLeftRotation()
                }
                else {
                    val right = r.smallRightRotation()
                    val tree = Node(value, l, right)
                    return tree.smallLeftRotation()
                }
            }
            if (balance == 2) {
                if (this.l.balanceFactor() >= 0) {
                    return this.smallRightRotation()
                }
                else {
                    val left = l.smallLeftRotation()
                    val tree = Node(value, left, r)
                    return tree.smallRightRotation()
                }
            }
        }
    }
    return this
}

fun Tree.insert(a : Int) : Tree {
    fun Tree.add() : Tree {
        when (this) {
            is Empty -> { return Node(a, Empty(), Empty()) }
            is Node -> {
                if (a < value) { return Node(value, l.add(), r) }
                else if (a == value) { return this }
                else { return Node(value, l, r.add()) }
            }
        }
        return Empty()
    }
    val res = this.add()
    return res.balance()
}

fun Tree.minInRight() : Int {
    when (this) {
        is Node  -> {
            when (l) {
                is Empty -> { return value }
                is Node  -> { return l.minInRight() }
            }
        }
    }
    return 0
}

fun Tree.replace(a : Int, b : Int) : Tree {
    when (this) {
        is Empty -> { return Empty() }
        is Node  -> {
            if (a == value) {
                return Node(b, l, r) }
            else if (a > value) {
                return Node(value, l, r.replace(a, b))
            }
            else {
                return Node(value, l.replace(a, b), r)
            }
        }
    }
    return Empty()
}

fun Tree.remove(a : Int) : Tree {
    fun Tree.delete() : Tree {
        when (this) {
            is Empty -> { return Empty() }
            is Node -> {
                if (a < value) {
                    return Node(value, l.delete(), r).balance() }
                else if (a > value) {
                    return Node(value, l, r.delete()).balance() }
                else if (a == value) {
                    when (r) {
                        is Empty -> { return l }
                        is Node -> {
                            val minValue = r.minInRight()
                            val resTree = Node(value, l, r.remove(minValue))
                            return resTree.replace(a, minValue).balance()
                        }
                    }
                }
            }
        }
        return Empty()
    }
    return this.delete()
}

fun Tree.search(a : Int) : Boolean {
    when (this) {
        is Node  -> {
            if (a == value) { return true }
            else {
                if (l.search(a) || r.search(a)) { return true }
                else { return false }
            }
        }
    }
    return false
}

fun <B> Tree.fold(fEmpty: B, fNode: (Int, B, B) -> B) : B {
    when (this) {
        is Empty -> { return fEmpty }
        is Node  -> { return fNode(value, l.fold(fEmpty, fNode),
                r.fold(fEmpty, fNode)) }
    }
    return fEmpty
}

fun Tree.toText(): List<String> {
    return fold(listOf("-\n")) {(value, lRes, rRes) ->
        val lText = lRes.map { "| $it" }
        val rText = rRes.map { "| $it" }
        val vText = listOf("$value\n")
        lText + vText + rText
    }
}

fun main(args : Array<String>) {
}