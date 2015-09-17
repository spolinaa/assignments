package hw02

import org.junit.Test
import kotlin.test.assertEquals

public class heapsortTest {
    @Test fun generalTest1() {
        val ar = arrayOf(1, -64, 3, 14, 1, -2)
        val res = ar.heapsort()
        val correct = arrayOf(-64, -2, 1, 1, 3, 14)
        var i = 0;
        while (i < res.lastIndex) {
            assertEquals(res[i], correct[i])
            i++
        }
    }
    @Test fun generalTest2() {
        val ar = arrayOf(-10, 1)
        val res = ar.heapsort()
        val correct = arrayOf(-10, 1)
        var i = 0;
        while (i < res.lastIndex) {
            assertEquals(res[i], correct[i])
            i++
        }
    }
    @Test fun arrayLength1Test() {
        val ar = arrayOf(0)
        val res = ar.heapsort()
        val correct = arrayOf(0)
        var i = 0;
        while (i < res.lastIndex) {
            assertEquals(res[i], correct[i])
            i++
        }
    }
}