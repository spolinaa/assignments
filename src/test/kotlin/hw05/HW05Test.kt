package hw05

import org.junit.Test
import kotlin.test.assertEquals

public class HW05Test {
    val a1 = arrayOf(1)
    @Test fun mergeSort1() {
        val res = mergeSort(a1, 1)
        assertEquals(res.arrayToString(), "1 ")
    }
    @Test fun mergeSort2() {
        val res = mergeSort(a1, 2)
        assertEquals(res.arrayToString(), "1 ")
    }
    @Test fun mergeSort3() {
        val res = mergeSort(a1, 3)
        assertEquals(res.arrayToString(), "1 ")
    }

    val a2 = arrayOf(4, 16, 7, 9, 10, 15, 93, 11, 1, 5, 19)
    @Test fun mergeSort4() {
        val res = mergeSort(a2, 1)
        assertEquals(res.arrayToString(), "1 4 5 7 9 10 11 15 16 19 93 ")
    }
    @Test fun mergeSort5() {
        val res = mergeSort(a2, 2)
        assertEquals(res.arrayToString(), "1 4 5 7 9 10 11 15 16 19 93 ")
    }
    @Test fun mergeSort6() {
        val res = mergeSort(a2, 3)
        assertEquals(res.arrayToString(), "1 4 5 7 9 10 11 15 16 19 93 ")
    }

    val a3 = Array(1000, { i -> 1000 - i })
    val a4 = Array(1000, { i -> i + 1 })
    @Test fun mergeSort7() {
        val res = mergeSort(a3, 1)
        assertEquals(res.arrayToString(), a4.arrayToString())
    }
    @Test fun mergeSort8() {
        val res = mergeSort(a3, 2)
        assertEquals(res.arrayToString(), a4.arrayToString())
    }
    @Test fun mergeSort9() {
        val res = mergeSort(a3, 3)
        assertEquals(res.arrayToString(), a4.arrayToString())
    }

    @Test fun mergeSort10() {
        val res = mergeSort(a4, 1)
        assertEquals(res.arrayToString(), a4.arrayToString())
    }
    @Test fun mergeSort11() {
        val res = mergeSort(a4, 2)
        assertEquals(res.arrayToString(), a4.arrayToString())
    }
    @Test fun mergeSort12() {
        val res = mergeSort(a4, 3)
        assertEquals(res.arrayToString(), a4.arrayToString())
    }
}

/*
     Size: 1000000
 Threads      Time(ms)
    1           333
    2           175
    3           176
    4           164
    5           167
    6           193
    7           219
    8           151
    9           157
    10          133
    15          132
    20          190
    30          144
    40          135
    50          151
    100         122
    1000        122
    10000       128
    100000      119
    1000000     112
 */