package classroom.c07

import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes.*
import java.nio.file.Files
import java.nio.file.Paths

internal sealed class Expr() {
    abstract public fun visit(mw: MethodVisitor)
    abstract public fun stackUsage(): Int

    public class Const(public val value: Short): Expr() {
        override fun stackUsage(): Int = 1
        override fun visit(mw: MethodVisitor) {
            val valueToInt = value.toInt()
            if (valueToInt >= Byte.MIN_VALUE && valueToInt <= Byte.MAX_VALUE) {
                mw.visitIntInsn(BIPUSH, valueToInt)
                return
            }
            mw.visitIntInsn(SIPUSH, valueToInt)
        }
    }

    public enum class Operation {
        Plus, Minus, Multiply, Divide
    }

    public class BinOp(public val op: Operation, public var l: Expr, public var r: Expr): Expr() {
        override fun stackUsage(): Int = Math.max(l.stackUsage(), 1 + r.stackUsage())
        override fun visit(mw: MethodVisitor) {
            l.visit(mw)
            r.visit(mw)
            mw.visitInsn(op.toCmd())
        }

        private fun Operation.toCmd(): Int =
                when (this) {
                    Operation.Plus     -> IADD
                    Operation.Minus    -> ISUB
                    Operation.Multiply -> IMUL
                    Operation.Divide   -> IDIV
                }
    }

    public fun getClassWriter(name: String): ClassWriter {
        val cw = ClassWriter(0)
        cw.visit(V1_7, ACC_PUBLIC, name, null, "java/lang/Object", null)

        val mw = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "calc",
                "()I", null, null)
        visit(mw)
        mw.visitInsn(IRETURN)
        mw.visitMaxs(stackUsage(), 1)
        mw.visitEnd()

        val mainMethodVisitor = cw.visitMethod(ACC_PUBLIC or ACC_STATIC, "main",
                "([Ljava/lang/String;)V", null, null)
        mainMethodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System",
                "out", "Ljava/io/PrintStream;")
        mainMethodVisitor.visitMethodInsn(INVOKESTATIC, name, "calc", "()I")
        mainMethodVisitor.visitMethodInsn(INVOKEVIRTUAL,
                "java/io/PrintStream", "println",
                "(I)V")
        mainMethodVisitor.visitInsn(RETURN)
        mainMethodVisitor.visitMaxs(2, 2)
        mainMethodVisitor.visitEnd()
        cw.visitEnd()

        return cw
    }
}

internal class ByteArrayClassLoader(): ClassLoader() {
    fun loadClass(name: String?, buf: ByteArray): Class<*>? {
        return super.defineClass(name, buf, 0, buf.size)
    }
}

internal fun expressionExample(): Expr {
    val const10  = Expr.Const(10)
    val const239 = Expr.Const(239)
    val expr0  = Expr.BinOp(Expr.Operation.Minus, const10, const10 )
    val expr10 = Expr.BinOp(Expr.Operation.Minus, const10, expr0   )
    val expr   = Expr.BinOp(Expr.Operation.Minus,  expr10, const239)
    return expr
}

internal fun saveToDisk(name: String, classByteArray: ByteArray) {
    val targetFile = Paths.get("$name.class")
    Files.write(targetFile, classByteArray)
}

internal fun generateClassByteArray(name: String, expr: Expr): ByteArray {
    val cw = expr.getClassWriter(name)
    return cw.toByteArray()
}

internal fun main(args: Array<String>) {
    val expr = expressionExample()
    val name = "Expr"

    val classByteArray = generateClassByteArray(name, expr)
    val cl = ByteArrayClassLoader()
    val exprClass = cl.loadClass(name, classByteArray)
    val methods = exprClass?.methods
    if (methods == null || methods.isEmpty()) { throw Exception() }
    for (method in methods) {
        if (method.name != "calc") { continue }
        val result = method.invoke(null)
        println("result: $result")
        break
    }

    saveToDisk(name, classByteArray)
}