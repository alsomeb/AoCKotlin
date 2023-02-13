
// FÖRSTA UPPGIFTEN
// FÖRE, EGEN LÖSNING AoC Dag 2 År 2022
fun getAoCList() = FileToList().getListFromFileTrimmedWithoutBlankSpaces("C:\\Users\\46760\\Desktop\\AoCKotlin\\src\\main\\resources\\AoCDay2Year2022Actual.txt")

fun partOne22(list: List<String>): Int {
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

fun partTwo22(list: List<String>): Int {
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

// EFTER AoC Dag 2 År 2022, Todd Ginsberg

// den bästa jag hittade som jag tyckte var bra
// han har gjort nästan exakt samma som mig men istället använt sumOf() för att räkna ut resultat
// Då kan man sätta score variabel som "val" även pga den behöver ej initieras förens den summeras

// The sumOf method of List applies the provided selector function on each element and returns the sum of the values returned by the selector function for each element of the List.
// https://www.educative.io/answers/how-to-use-the-sumof-method-of-list-in-kotlin
fun partOneImproved22(list: List<String>): Int {
    val score: Int // Vi kan ha val, för den assignas när vi summerar nedan och sen blir immutable
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

    // it är ett keyword i kotlin
    // "Whenever you have a function literal with exactly one parameter
    // you don’t have to define the parameter explicitly but you can just use 'it'"
    score = list.sumOf { map[it]!!}
    return score
}

fun partTwoImproved22(list: List<String>): Int {
    val score: Int  // Vi kan ha val, för den assignas när vi summerar nedan och sen blir immutable
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

    score = list.sumOf { map[it]!!}
    return score
}


fun main() {
    // 1
    // FÖRE AoC Dag 2 År 2022
    val input = getAoCList()
    val result1 = partOne22(input)
    val result2 = partTwo22(input)
    println("Part One: $result1\nPart two: $result2")

    // EFTER AoC Dag 2 År 2022
    val result1Improved = partOneImproved22(input)
    val result2Improved = partTwoImproved22(input)
    println("Part One Imp: $result1Improved\nPart two Imp: $result2Improved")
}