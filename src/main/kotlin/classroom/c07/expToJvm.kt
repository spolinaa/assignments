package classroom.c07

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import java.nio.file.Files
import java.nio.file.Paths

internal sealed class Expr() {
    abstract public fun visit(mw: MethodVisitor)
    abstract public fun stackUsage(): Int

    public class Const(public val value: Byte): Expr() {
        override fun stackUsage(): Int = 1
        override fun visit(mw: MethodVisitor) {
            mw.visitIntInsn(BIPUSH, value.toInt())
        }
    }

    enum class Operation {
        Plus, Minus, Multiply, Divide
    }

    public class BinOp(public val op: Operation, public var l: Expr, public var r: Expr): Expr() {
        override fun stackUsage(): Int = Math.max(l.stackUsage(), 1 + r.stackUsage())
        override fun visit(mw: MethodVisitor) {
            l.visit(mw)
            r.visit(mw)
            when (op) {
                Operation.Plus     -> mw.visitInsn(IADD)
                Operation.Minus    -> mw.visitInsn(ISUB)
                Operation.Multiply -> mw.visitInsn(IMUL)
                Operation.Divide   -> mw.visitInsn(IDIV)
            }
        }
    }

    public fun generateCalculatorClass() {
        val name = "Expr"
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, name, null, "java/lang/Object", null)

        val mw = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null)
        mw.visitFieldInsn(GETSTATIC, "java/lang/System",
                "out", "Ljava/io/PrintStream;")
        visit(mw)
        mw.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream", "println",
                "(I)V")
        mw.visitInsn(RETURN)
        mw.visitMaxs(1 + stackUsage(), 2)
        mw.visitEnd()
        cw.visitEnd()

        val bytes = cw.toByteArray()
        val targetFile = Paths.get("$name.class")
        Files.write(targetFile, bytes)
    }
}

fun main(args: Array<String>) {
    val const10  = Expr.Const(10)
    val const239 = Expr.Const(107)
    val expr0  = Expr.BinOp(Expr.Operation.Minus, const10, const10)
    val expr10 = Expr.BinOp(Expr.Operation.Minus, const10, expr0)
    val expr   = Expr.BinOp(Expr.Operation.Minus, expr10, const239)
    expr.generateCalculatorClass()
}