package hw04

import org.junit.Test
import kotlin.test.assertEquals

public class HW04Test {
    val tree1 : Tree = Node(17, Node(12, Node(11, Empty(), Empty()), Node(14,
            Node(13, Empty(), Empty()), Node(15, Empty(), Empty()))),
            Node(21, Node(19, Empty(), Empty()), Node(23, Empty(), Empty())))

    Test fun balanceFactor1() {
        assertEquals(1, tree1.balanceFactor())
    }
    Test fun balanceFactor2() {
        val tree = Node(5, Node(3, Empty(), Empty()), Node(7, Empty(), Empty()))
        assertEquals(0, tree.balanceFactor())
    }
    Test fun balanceFactor3() {
        val tree = Node(4, Empty(), Node(2, Empty(), Empty()))
        assertEquals(-1, tree.balanceFactor())
    }

    Test fun insert3() {
        var tree = Empty()
        assertEquals("Node(3, Empty, Empty)", tree.insert(3).treeToString())
    }
    Test fun insert5() {
        val tree = Empty()
        assertEquals("Node(5, Empty, Empty)", tree.insert(5).treeToString())
    }
    Test fun insert7() {
        val tree = Node(5, Empty(), Empty())
        assertEquals("Node(5, Empty, Node(7, Empty, Empty))",
                tree.insert(7).treeToString())
    }
    Test fun insert9() {
        val tree = Node(15, Empty(), Empty())
        assertEquals("Node(15, Node(9, Empty, Empty), Empty)",
                tree.insert(9).treeToString())
    }
    Test fun insert11() {
        val tree = Node(8, Node(7, Empty(), Empty()), Node(9, Empty(), Empty()))
        assertEquals("Node(8, Node(7, Empty, Empty), Node(9, Empty, Node(11, Empty, Empty)))",
                tree.insert(11).treeToString())
    }
    Test fun insert13SmallRight() {
        val tree = Node(15, Node(14, Empty(), Empty()), Empty())
        assertEquals("Node(14, Node(13, Empty, Empty), Node(15, Empty, Empty))",
                tree.insert(13).treeToString())
    }
    Test fun insert15SmallLeft() {
        val tree = Node(11, Empty(), Node(13, Empty(), Empty()))
        assertEquals("Node(13, Node(11, Empty, Empty), Node(15, Empty, Empty))",
                tree.insert(15).treeToString())
    }
    Test fun insert17BigLeft() {
        val tree = Node(15, Node(13, Empty(), Empty()), Node(21,
                Node(19, Empty(), Empty()), Node(23, Empty(), Empty())))
        assertEquals("Node(19, Node(15, Node(13, Empty, Empty), " +
                "Node(17, Empty, Empty)), Node(21, Empty, Node(23, Empty, Empty)))",
                tree.insert(17).treeToString())
    }
    Test fun insert22BigRight() {
        val tree = Node(25, Node(21, Node(19, Empty(), Empty()),
                Node(23, Empty(), Empty())), Node(27, Empty(), Empty()))
        assertEquals("Node(23, Node(21, Node(19, Empty, Empty), Node(22, Empty, Empty)), " +
                "Node(25, Empty, Node(27, Empty, Empty)))",
                tree.insert(22).treeToString())
    }
    Test fun searchYes() {
        val tree = Node(5, Node(3, Empty(), Empty()), Node(7, Node(6,
                Empty(), Empty()), Node(8, Empty(), Node(9, Empty(), Empty()))))
        assertEquals(true, tree.search(9))
    }
    Test fun searchNo() {
        val tree = Node(5, Node(3, Empty(), Empty()), Node(7, Empty(), Empty()))
        assertEquals(false, tree.search(9))
    }
    Test fun searchNoInEmpty() {
        assertEquals(false, Empty(). search(7))
    }
    Test fun remove14() {
        assertEquals("Node(17, Node(12, Node(11, Empty, Empty), " +
                "Node(15, Node(13, Empty, Empty), Empty)), " +
                "Node(21, Node(19, Empty, Empty), Node(23, Empty, Empty)))",
                tree1.delete(14).treeToString())
    }
    Test fun remove21() {
        assertEquals("Node(17, Node(12, Node(11, Empty, Empty), " +
                "Node(14, Node(13, Empty, Empty), Node(15, Empty, Empty))), " +
                "Node(23, Node(19, Empty, Empty), Empty))",
                tree1.delete(21).treeToString())
    }
    Test fun remove11() {
        assertEquals("Node(17, Node(14, Node(12, Empty, Node(13, Empty, Empty)), " +
                "Node(15, Empty, Empty)), Node(21, Node(19, Empty, Empty), " +
                "Node(23, Empty, Empty)))",
                tree1.delete(11).treeToString())
    }
    Test fun remove20() {
        val tree = Node(17, Node(12, Node(11, Empty(), Empty()), Node(14,
                Node(13, Empty(), Empty()), Node(15, Empty(), Empty()))),
                Node(20, Node(19, Empty(), Empty()), Empty()))
        assertEquals("Node(14, Node(12, Node(11, Empty, Empty), Node(13, Empty, Empty)), " +
                "Node(17, Node(15, Empty, Empty), Node(19, Empty, Empty)))",
                tree.delete(20).treeToString())
    }
    Test fun unionOfEmpty() {
        val tree = Empty()
        assertEquals("Empty", tree.union(tree).treeToString())
    }
    Test fun union1() {
        val tree = Node(5, Empty(), Empty())
        assertEquals("Node(5, Empty, Empty)", tree.union(Empty()).treeToString())
    }
    Test fun union2() {
        val tree = Node(5, Empty(), Empty())
        assertEquals("Node(5, Empty, Empty)", Empty().union(tree).treeToString())
    }
    val tree2 = Node(5, Node(3, Empty(), Empty()), Node(7, Empty(), Empty()))
    val tree3 = Node(4, Node(2, Node(1, Empty(), Empty()), Node(3, Empty(), Empty())),
            Node(5, Node(6, Empty(), Empty()), Empty()))
    Test fun union3() {
        val s = "Node(4, Node(2, Node(1, Empty, Empty), Node(3, Empty, Empty)), " +
                "Node(5, Node(6, Empty, Empty), Node(7, Empty, Empty)))"
        assertEquals(s, tree2.union(tree3))
    }
    Test fun union4() {
        val s = "Node(5, Node(3, Node(2, Node(1, Empty, Empty), Empty), Node(4, Empty, Empty)), " +
                "Node(7, Node(6, Empty, Empty), Empty))"
        assertEquals(s, tree3.union(tree2))
    }
}