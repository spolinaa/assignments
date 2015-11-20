/* A converter from an ASCII text to a short Brainfuck program, which prints this text
(expectation: 5 h; reality: 5 h)
by Sokolova Polina */

package hw09

public class Converter(val s : String) {
    val length = s.length
    var partNumber  = Array(length, { arrayOf(0, 0, 0) })
    private fun parse(m1 : Int, m2 : Int) {
        for (i in 0..length - 1) {
            var num = s[i].toInt()
            var amountM1 = num / m1
            var modM1 = num mod m1
            var amountM2  =  modM1 / m2
            var rem = modM1 mod m2
            partNumber[i][2] = rem
            partNumber[i][1] = amountM2
            partNumber[i][0] = amountM1
        }
    }
    private fun makeLoop(start : String, j : Int) : String {
        var res  = start + "["
        for (i in 0..length - 1) {
            res += ">"
            for (k in 0..partNumber[i][j] - 1) {
                res += "+"
            }
        }
        for (i in 0..length - 1) {
            res += "<"
        }
        res += "-]"
        return res
    }
    private fun remainder() : String {
        var res = ""
        var col = 2
        for (i in 0..length - 1) {
            res += ">"
            for (k in 0..partNumber[i][col] - 1) {
                res += "+"
            }
            res += "."
        }
        return res
    }
    public fun convert() : String {
        var min = sum()
        var minProgram = ""
        for (i in 1..64) {
            for (j in 1..i) {
                parse(i, j)
                var p1 = ""
                var p2 = ""
                for (k in 1..i) { p1 += "+" }
                for (k in 1..j) { p2 += "+" }
                var res = makeLoop(p1, 0) + makeLoop(p2, 1) + remainder()
                if (res.length < min) {
                    min = res.length
                    minProgram = res
                }
            }
        }
        return minProgram
    }
    private fun sum() : Int {
        var sum = 2 * length
        for (i in 0..length - 1) {
            sum += s[i].toInt()
        }
        return sum
    }
}