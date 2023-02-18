import kotlin.test.*

class AoCDay2Year2020Test {

    /*
    Each line gives the password policy and then the password.
    The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid.
    For example, 1-3 a means that the password must contain a at least 1 time and at most 3 times.
     */

    private val reader = FileToList()
    private val listActual = reader.getListFromFile("C:\\Users\\46760\\Desktop\\Java\\AoCKotlin\\src\\main\\resources\\AoCDay2Year2020Actual.txt")
    private val listTest = reader.getListFromFile("C:\\Users\\46760\\Desktop\\Java\\AoCKotlin\\src\\main\\resources\\AoCDay2Year2020Test.txt")

    // FÖRE, MIN EGNA LÖSNING

    @Test
    fun testWeGetCorrectFormatFromInput() {
        assertEquals(3, listTest.size)
        assertEquals(listOf("2-9 c: ccccccccc"), listTest.takeLast(1))
        assertEquals(listOf("1-3 a: abcde"), listTest.take(1))
    }

    @Test
    fun checkValidTestPartOne() {
        val line = "1-3 a: abcde"
        val line2 = "1-3 b: cdefg"
        assertTrue { checkValidPartOne(line) }
        assertFalse { checkValidPartOne(line2) }
    }


    @Test
    fun partOneTest() {
        var validPasswords = 0

        for (line in listTest) {
            if(checkValidPartOne(line)) validPasswords++
        }

        assertEquals(2, validPasswords)
        assertNotEquals(0, validPasswords)
        assertNotEquals(3, validPasswords)
    }


    fun checkValidPartOne(word: String): Boolean {
        val words = word.split(" ")

        val min = words[0].split("-")[0].toInt() // hämtar ordet på 0 index och splittar om det på "-" o tar första siffran
        val max = words[0].split("-")[1].toInt() // samma som ovan fast andra siffran
        val specLetter = words[1][0]
        val password = words[2]

        val amount = password.count { it == specLetter }

        /*
        Checks whether the specified amount value belongs to the range.
        A value belongs to the closed range if it is greater than or equal to the start bound and less than or equal to the endInclusive bound.
         */
        return amount in min..max
    }


    @Test
    fun partOneActual(){
        var count = 0

        for (line in listActual) {
            if(checkValidPartOne(line)) count++
        }

        println(count)
    }

}


