import kotlin.test.Test
import kotlin.test.assertEquals

// TDD på test input man får
class AoCDay2Year2022Test {


    private val reader = FileToList()
    // Den fattar inte med kortare path, måste vara exakt ?
    private val list = reader.getListFromFIle("C:\\Users\\46760\\Desktop\\AoCKotlin\\src\\main\\resources\\AoCDay2Year2022Test.txt")


    @Test
    fun testReadFileReturnsCorrectFormat() {
        assertEquals(3, list.size)
        assertEquals("AY", list[0])
        assertEquals("BX", list[1])
        assertEquals("CZ", list[2])
    }

    // Här provar vi lösa fram en algoritm som vi sedan kan använda på stora filen!
    @Test
    fun testSolvingDay1WithSampleInputIsCorrect() {

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

        for (line in list) {
            score += mapOfResults[line]!! // returnerar key eller NULL, Men pga !! operator så kan det INTE bli null i vårat program, den kommer crasha isf med NullPointerException
        }
        println("Test Score is: $score")
        assertEquals(15, score)

    }

}