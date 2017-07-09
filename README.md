# fuck-kotlin
Brainfuck interpreter in Kotlin

# How to compile the interpreter

    kotlinc src/fuck.kt -d fuck.jar

# How to run

The interpreter accepts either a complete program

    kotlin -classpath out/fuck.jar FuckKt ">++++[-<+>]"


or a file name

    echo ">++++[-<+>]" > program.bf
    kotlin -classpath out/fuck.jar FuckKt program.bf
