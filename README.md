# fuck-kotlin
Brainfuck compiler in Kotlin

# How to compile the compiler

    kotlinc src/fuck.kt -d fuck.jar

# How to run

To compile 

The compiler accepts either a complete program

    kotlin -classpath out/fuck.jar FuckKt ">++++[-<+>]"


or a file name

    echo ">++++[-<+>]" > program.bf
    kotlin -classpath out/fuck.jar FuckKt program.bf
