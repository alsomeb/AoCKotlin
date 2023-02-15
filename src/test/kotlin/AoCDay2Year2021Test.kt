import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

// HAR SATT @TEST PÅ VISSA FUNKTIONER FÖR ATT KÖRA DEM I DENNA CLASS
// LÖSNINGAR LÄNGRE NER

class AoCDay2Year2021Test {


    private val reader = FileToList()
    private val listTest = reader.getListFromFile("C:\\Users\\alex\\Desktop\\Nackademin\\Funktionell Programmering\\AoCKotlin\\src\\main\\resources\\AoCDay2Year2021Test.txt")
    private val listActual = reader.getListFromFile("C:\\Users\\alex\\Desktop\\Nackademin\\Funktionell Programmering\\AoCKotlin\\src\\main\\resources\\AoCDay2Year21Actual.txt")


    @Test
    fun testReadFileReturnsCorrectFormat() {
        assertEquals(6, listTest.size)
        assertEquals("forward 5", listTest[0])
        assertEquals("down 5", listTest[1])
        assertEquals("forward 8", listTest[2])
        assertEquals("up 3", listTest[3])
        assertEquals("down 8", listTest[4])
        assertEquals("forward 2", listTest[5])
        assertNotEquals(5, listTest.size)
        assertNotEquals(7, listTest.size)
    }


    @Test
    fun testSolvingDay2WithSampleInputIsCorrect() {
        var totalDepth = 0
        var totalHorizontal= 0

        fun addCourseToTotal(line: List<String>) {
            val direction = line[0]
            val amount: Int = line[1].toInt()
            when(direction) {
                "forward" -> totalHorizontal += amount
                "down" -> totalDepth += amount
                "up" -> totalDepth -= amount
            }
        }


        for (line in listTest) addCourseToTotal(line.split(" "))


        assertEquals(15, totalHorizontal)
        assertEquals(10, totalDepth)
        assertEquals(150, totalDepth * totalHorizontal)
    }


    @Test
    fun testMySolutionIsSameAsImproved() {
        val scorePartOne = partOne()
        val scorePartTwo = partTwo()
        val scorePartOneImproved = part1Improved(listActual)
        val scorePartTwoImproved = part2Improved(listActual)

        assertEquals(scorePartOne, scorePartOneImproved)
        assertEquals(scorePartTwo, scorePartTwoImproved)
        assertNotEquals(scorePartOne, scorePartTwoImproved)
        assertNotEquals(scorePartTwo, scorePartOneImproved)
    }

    // FÖRE, EGEN LÖSNING AoC Dag 2 År 2021

    fun partOne(): Int {
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


        for (line in listActual) addCourseToTotal(line.split(" "))

        return totalDepth * totalHorizontal
    }

    fun partTwo(): Int {
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

        for (line in listActual) addCourseToTotal(line.split(" "))

        return totalDepth * totalHorizontal
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




}