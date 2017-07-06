import java.io.File
import java.util.*
import kotlin.collections.HashMap
import kotlin.system.exitProcess

const val VALID_CHARS = "+-<>[],."

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Please provide a program or file name as the first parameter")
        exitProcess(1)
    }

    var program = args[0]

    // Check if
    val f = File(program)
    if (f.isFile && f.canRead()) {
        program = String(f.readBytes())
    }

    run(program.filter { VALID_CHARS.contains(it) })
}

fun run(program: String) {
    val jumps = matchJumps(program)
    val mem = MutableList(30000, init = { 0 })
    var pc = 0
    var cursor = 0

    while (pc < program.length) {
        when (program[pc]) {
            '+' -> mem[cursor] = (mem[cursor] + 1) % 255
            '-' -> mem[cursor] = (mem[cursor] - 1) % 255
            '>' -> cursor++
            '<' -> cursor--
            '[' -> {
                if (mem[cursor] == 0) {
                    pc = jumps[pc] as Int
                }
            }
            ']' -> {
                pc = jumps[pc] as Int
                pc--
            }
            '.' -> print("${mem[cursor].toChar()}")
            ',' -> mem[cursor] = System.`in`.read()
        }

        pc++
    }
}

private fun matchJumps(program: String): Map<Int, Int> {
    val stack = Stack<Int>()
    val jumpsMap = HashMap<Int, Int>()
    for ((i, c) in program.withIndex()) {
        when (c) {
            '[' -> stack.push(i)
            ']' -> {
                try {
                    val other = stack.pop()
                    jumpsMap.put(other, i)
                    jumpsMap.put(i, other)
                } catch (e: EmptyStackException) {
                    println("Program has unmatched brackets.")
                    exitProcess(1)
                }
            }
        }
    }

    if (!stack.isEmpty()) {
        println("Program has unmatched brackets.")
        exitProcess(1)
    }

    return jumpsMap
}
