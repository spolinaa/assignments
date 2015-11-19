package hw09

import javax.swing.*
import java.awt.event.*

public class MainForm : JFrame(), ActionListener {
    private val interpretButton = JButton("Interpret")
    private val textInField = JTextArea(20, 50)

    init {
        title = "An interpreter for Brainfuck"
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isResizable = true
        val southPanel = JPanel()
        val centerPanel = JPanel()
        add(southPanel, "South")
        add(centerPanel, "Center")
        centerPanel.add(JScrollPane(textInField))
        southPanel.add(interpretButton)
        interpretButton.addActionListener(this);
        setSize(650, 400)
        setLocationRelativeTo(null)
    }
    override fun actionPerformed(event : ActionEvent) {
        Interpreter().interpret(textInField.text)
    }
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            MainForm().isVisible = true
        }
    }
}