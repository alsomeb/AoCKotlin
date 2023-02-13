// ANDRA UPPGIFTEN
// FÖRE, EGEN LÖSNING AoC Dag 2 År 2021

fun getInput():List<String> {
    val reader = FileToList()
    return reader.getListFromFile("C:\\Users\\46760\\Desktop\\AoCKotlin\\src\\main\\resources\\AoCDay2Year21Actual.txt")
}

fun partOne() {
    val list = getInput()
    var totalDepth = 0
    var totalHorizontal = 0

    fun addCourseToTotal(line: List<String>) {
        val direction = line[0]
        val amount: Int = line[1].toInt()
        when (direction) {
            "forward" -> totalHorizontal += amount
            "down" -> totalDepth += amount
            "up" -> totalDepth -= amount
        }
    }


    for (line in list) addCourseToTotal(line.split(" "))

    println("Result1 = ${totalDepth * totalHorizontal}")
}

fun partTwo() {
    val list = getInput()

    var totalDepth = 0
    var totalHorizontal = 0
    var aim = 0

    fun addCourseToTotal(line: List<String>) {
        val direction = line[0]
        val amount: Int = line[1].toInt()
        when (direction) {
            "forward" -> {
                totalHorizontal += amount
                totalDepth += aim * amount
            }
            "down" -> aim += amount
            "up" -> aim -= amount
        }
    }

    for (line in list) addCourseToTotal(line.split(" "))

    println("Result2 =  ${totalDepth * totalHorizontal}")
}

// EFTER MHA https://github.com/asm0dey/aoc-2021/blob/main/src/Day02.kt
// Denna var intressant för man kunde korta ner koden och använda .map för att göra smålistor i en lista
// sedan loopa igenom direction, amount
// Det mest intressanta var funktionen check() som är som ett mini test, se impl längre ner i main

fun part1Improved(input: List<String>): Int {
    var horiz = 0
    var depth = 0
    val instructions = input.map { it.split(' ') }

    for((dir, amountString) in instructions) {
        val amount = amountString.toInt()
        when (dir) {
            "forward" -> horiz += amount
            "up" -> depth -= amount
            "down" -> depth += amount
        }
    }

    return horiz * depth
}

fun part2Improved(input: List<String>): Int {
    var horiz = 0
    var depth = 0
    var aim = 0
    val instructions = input.map { it.split(' ') }

    for((dir, amountString) in instructions) {
        val amount = amountString.toInt()
        when (dir) {
            "forward" -> {
                horiz += amount
                depth += (aim * amount)
            }
            "up" -> aim -= amount
            "down" -> aim += amount
        }
    }

    return horiz * depth
}


fun main() {
    // FÖRE
    partOne()
    partTwo()

    // EFTER
    val input = getInput()
    val res1 = part1Improved(input)
    val res2 = part2Improved(input)

    // Mini tests
    // Throws an IllegalStateException if the value is false
    check(res1 == 2039256)
    check(res2 == 1856459736)
}