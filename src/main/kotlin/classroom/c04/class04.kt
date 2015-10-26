package classroom.c04.kotlin

data class Pair<T1 : Comparable<T1>, T2 : Comparable<T2>>(val first: T1, val second: T2): Comparable<Pair<T1, T2>>
//    where T1 : Comparable<T1>,
//          T2 : Comparable<T2>
{
    override fun compareTo(other: Pair<T1, T2>): Int {
        //if (pair == null) { return 1; }
        val firstCompare = first.compareTo(other.first);
        if (firstCompare > 0) { return  1; }
        if (firstCompare < 0) { return -1; }
        val secondCompare = second.compareTo(other.second);
        if (secondCompare > 0) { return  1; }
        if (secondCompare < 0) { return -1; }
        return 0;
    }
}
