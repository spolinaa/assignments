// Homework #1
// by Vladimir Yumatov
// SPBSU, group 271


// Tests
fun main(args: Array<String>) {
    // for task 1
    var arr = Array(11, {i -> 11 - i})
    print("Unsorted: "); arr.print()
    arr.heapsort(); print("\nSorted: ")
    arr.print(); println()

    arr = Array(1, {i -> 11 - i})
    print("Unsorted: "); arr.print()
    arr.heapsort(); print("\nSorted: ")
    arr.print(); println()

    arr = Array(0, {i -> 11 - i})
    print("Unsorted: "); arr.print()
    arr.heapsort(); print("\nSorted: ")
    arr.print(); println()

    //for tasks 2 & 3
    val t = genTree(0, 7)
    print("\n" + t.toText())
    print("Printed by fold: ")
    t.foldNew(0, {a: Int, b: Int -> print("$b "); 0})

    val tr = Node(5, Node(9, Leaf(7), Leaf(10)), Node(4, Leaf(100), Leaf(12)))
    println("\n\n" + tr.toText() + "Max path in this tree: ${tr.maxpath()}")

    // for task 4
    println("\n6 + 2 = ${pnSum(6.ToPn(), 2.ToPn()).ToInt()}")
    println("6 - 2 = ${pnSub(6.ToPn(), 2.ToPn()).ToInt()}")
    println("6 * 2 = ${pnMult(6.ToPn(), 2.ToPn()).ToInt()}")
    println("6 ^ 2 = ${pnPow(6.ToPn(), 2.ToPn()).ToInt()}")
}
