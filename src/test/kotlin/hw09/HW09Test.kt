package hw09

import org.junit.Test;
import kotlin.test.assertEquals

public class HW09Test : OutputTest() {
    @Test fun helloWorld() {
        val s = " ++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++" +
                ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++." +
                "------.--------.>+.>."
        val res = "Hello World!\n"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
    @Test fun noBalance1() {
        val s = "]]]"
        val res = "Not enough '['\n"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
    @Test fun noBalance2() {
        val s = "[[[[]]]"
        val res = "Not enough ']'\n"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
    @Test fun noBalance3() {
        val s = "[]]"
        val res = "Not enough '['\n"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
    @Test fun findEmpty() {
        val s = "+>+><<[>]>+++[<+++++++++++>-]<."
        val res = "!"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
    @Test fun mul8_8() {
        val s = ">++++++++[<++++++++++>-]<.+++."
        val res = "PS"
        Interpreter().interpret(s)
        assertEquals(res, output.toString())
    }
}