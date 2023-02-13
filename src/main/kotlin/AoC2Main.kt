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


fun main() {
    partOne()
    partTwo()
}