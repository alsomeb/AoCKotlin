import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

// HAR SATT @TEST PÅ VISSA FUNKTIONER FÖR ATT KÖRA DEM I DENNA CLASS
// LÖSNINGAR LÄNGRE NER

class AoCDay2Year2022Test {

    private val reader = FileToList()
    // Den fattar inte med kortare path, måste vara exakt ?
    private val listActual = reader.getListFromFileTrimmedWithoutBlankSpaces("C:\\Users\\alex\\Desktop\\Nackademin\\Funktionell Programmering\\AoCKotlin\\src\\main\\resources\\AoCDay2Year2022Actual.txt")
    private val testList = reader.getListFromFileTrimmedWithoutBlankSpaces("C:\\Users\\alex\\Desktop\\Nackademin\\Funktionell Programmering\\AoCKotlin\\src\\main\\resources\\AoCDay2Year2022Test.txt")


    @Test
    fun testReadFileReturnsCorrectFormat() {

        assertEquals(3, testList.size)
        assertEquals("AY", testList[0])
        assertEquals("BX", testList[1])
        assertEquals("CZ", testList[2])
    }

    // Här provar vi lösa fram en algoritm som vi sedan kan använda på stora filen!
    @Test
    fun testSolvingDay2WithSampleInputIsCorrect() {

        // Finns mapOf i Kotlin också men detta var enklare för mig att läsa med alla mappningar
        val mapOfResults = HashMap<String, Int>()
        mapOfResults["AY"] = 8 // ROCK, PAPER, WIN
        mapOfResults["AX"] = 4 // DRAW
        mapOfResults["AZ"] = 3 // ROCK, SCISSOR, LOSS
        mapOfResults["BX"] = 1 // .... osv
        mapOfResults["BY"] = 5
        mapOfResults["BZ"] = 9
        mapOfResults["CX"] = 7
        mapOfResults["CY"] = 2
        mapOfResults["CZ"] = 6

        var score = 0

        for (line in testList) {
            score += mapOfResults[line]!! // returnerar key eller NULL, Men pga !! operator så kan det INTE bli null i vårat program, den kommer crasha isf med NullPointerException
        }
        println("Test Score is: $score")
        assertEquals(15, score)
    }

    @Test
    fun testMySolutionIsSameAsImproved() {
        val scorePartOne = partOne22(listActual)
        val scorePartTwo = partTwo22(listActual)
        val scorePartOneImproved = partOneImproved22(listActual)
        val scorePartTwoImproved = partTwoImproved22(listActual)

        assertEquals(scorePartOne, scorePartOneImproved)
        assertEquals(scorePartTwo, scorePartTwoImproved)
        assertNotEquals(scorePartOne, scorePartTwoImproved)
        assertNotEquals(scorePartTwo, scorePartOneImproved)
    }





    // FÖRSTA UPPGIFTEN
    // FÖRE, EGEN LÖSNING AoC Dag 2 År 2022
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

}