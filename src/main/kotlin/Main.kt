fun getAoCList() = FileToList().getListFromFIle("C:\\Users\\46760\\Desktop\\AoCKotlin\\src\\main\\resources\\AoC2Actual.txt")

fun partOne(list: List<String>): Int {
    var score = 0
    val map = mapOf(
        "AY" to 8,
        "AX" to 4,
        "AZ" to 3,
        "BX" to 1,
        "BY" to 5,
        "BZ" to 9,
        "CX" to 7,
        "CY" to 2,
        "CZ" to 6)

    list.forEach { result -> score += map[result]!! } // måste ha !! Pga kan va null, isf crash, kommer ej släppa in null i program
    return score
}

fun partTwo(list: List<String>): Int {
    var score = 0
    val map = mapOf(
        "AY" to 4,
        "AX" to 3,
        "AZ" to 8,
        "BX" to 1,
        "BY" to 5,
        "BZ" to 9,
        "CX" to 2,
        "CY" to 6,
        "CZ" to 7)

    list.forEach { result -> score += map[result]!! } // måste ha !! Pga kan va null, isf crash, kommer ej släppa in null i program
    return score
}


fun main() {
    val input = getAoCList()
    val result1 = partOne(input)
    val result2 = partTwo(input)
    println("Part One: $result1\nPart two: $result2")
}