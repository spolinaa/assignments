package classroom.c05.old

import java.util.*

public class Tree<A: Comparable<A>>(): Iterable<A> {
    private var root: Node<A>? = null

    private class Node<A: Comparable<A>>(
            val value: A, val l: Node<A>?, val r: Node<A>?
    ): Iterable<A> {
        public fun size(): Int = 1 + (l?.size() ?: 0) + (r?.size() ?: 0)
        public fun Node<A>.getMax(): A = r?.getMax() ?: value
        public fun Node<A>.getMin(): A = l?.getMin() ?: value

        override fun iterator(): Iterator<A> = LCRIterator(this)
    }

    private fun Node<A>?.insert(elem: A): Node<A> {
        this ?: return Node(elem, null, null)
        return elem.compareTo(value).let {
            when {
                it < 0 -> Node(value, l.insert(elem), r)
                it > 0 -> Node(value, l, r.insert(elem))
                else   -> this ?: Node(value, null, null)
            }
        }
    }

    private fun Node<A>?.remove(elem: A): Node<A>? {
        this ?: return null
        return elem.compareTo(value).let {
            when {
                it < 0 -> Node(value, l.remove(elem), r)
                it > 0 -> Node(value, l, r.remove(elem))
                else -> {
                    if (l == null) {
                        r?.getMin()?.let { Node(it, null, r.remove(it)) } ?: null
                    } else {
                        l.getMax().let { Node(it, l.remove(it), r) }
                    }
                }
            }
        }
    }

    private fun Node<A>?.contains(elem: A): Boolean {
        this ?: return false
        return value.equals(elem) || l.contains(elem) || r.contains(elem)
    }

    private class EmptyIterator<A>(): Iterator<A> {
        override fun hasNext(): Boolean = false
        override fun next(): A { throw NoSuchElementException() }
    }

    private abstract class NodeIterator<A: Comparable<A>>(node: Node<A>): Iterator<A> {
        protected val lIterator: Iterator<A> = node.l?.iterator() ?: EmptyIterator<A>()
        protected val rIterator: Iterator<A> = node.r?.iterator() ?: EmptyIterator<A>()
        protected val value: A = node.value
        protected var observedValue = false
        protected var lHasNext = true
        protected var rHasNext = true

        override fun hasNext(): Boolean {
            if (!observedValue) { return true }
            if (lHasNext) {
                lHasNext = lIterator.hasNext()
                if (lHasNext) { return true }
            }
            if (rHasNext) {
                rHasNext = rIterator.hasNext()
                if (rHasNext) { return true }
            }
            return false
        }
    }

    private class LCRIterator<A: Comparable<A>>(node: Node<A>): NodeIterator<A>(node) {
        override fun next(): A {
            if (lIterator.hasNext()) { return lIterator.next() }
            if (!observedValue)      { observedValue = true; return value }
            if (rIterator.hasNext()) { return rIterator.next() }
            throw NoSuchElementException()
        }
    }

    private class CLRIterator<A: Comparable<A>>(node: Node<A>): NodeIterator<A>(node) {
        override fun next(): A {
            if (!observedValue)      { observedValue = true; return value }
            if (lIterator.hasNext()) { return lIterator.next() }
            if (rIterator.hasNext()) { return rIterator.next() }
            throw NoSuchElementException()
        }
    }

    private class LRCIterator<A: Comparable<A>>(node: Node<A>): NodeIterator<A>(node) {
        override fun next(): A {
            if (lIterator.hasNext()) { return lIterator.next() }
            if (rIterator.hasNext()) { return rIterator.next() }
            if (!observedValue)      { observedValue = true; return value }
            throw NoSuchElementException()
        }
    }
    public fun insert(elem: A) { root = root.insert(elem) }
    public fun remove(elem: A) { root = root.remove(elem) }
    public fun contains(elem: A) = root.contains(elem)

    public fun size(): Int = root?.size() ?: 0
    override fun iterator(): Iterator<A> = root?.iterator() ?: EmptyIterator<A>()

    override fun toString(): String =
        StringBuilder().let { sb ->
            this.forEach { sb.append("$it ") }
            sb.append("\n")
        }.toString()
}

fun main(args: Array<String>) {
    val tree = Tree<Int>()
    (1..5).forEach { tree.insert(it) }
    println(tree)
    tree.remove(3)
    println(tree)
}
