import kotlin.test.*

class AoCDay2Year2020Test {

    /*
    Each line gives the password policy and then the password.
    The password policy indicates the lowest and highest number of times a given letter must appear for the password to be valid.
    For example, 1-3 a means that the password must contain an at least 1 time and at most 3 times.
     */

    // Har gjort en förbättrad version som funkar utan absolute path för att läsa in input
    private val reader = FileToList()
    private val listActual = reader.getListFromFileImproved("src/main/resources/AoCDay2Year2020Actual.txt")
    private val listTest = reader.getListFromFileImproved("src/main/resources/AoCDay2Year2020Test.txt")

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
    fun partTwoTest() {
        var validPasswords = 0

        for (line in listTest) {
            if(checkValidPartTwo(line)) validPasswords++
        }

        assertEquals(1, validPasswords)
        assertNotEquals(2, validPasswords)
        assertNotEquals(3, validPasswords)

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

            /*
        Each policy actually describes two positions in the password,
        where 1 means the first character, 2 means the second character,
        and so on. (Be careful; Toboggan Corporate Policies have no concept of "index zero"!)
        Exactly one of these positions must contain the given letter.
        Other occurrences of the letter are irrelevant for the purposes of policy enforcement.
         */
    fun checkValidPartTwo(word: String): Boolean {
        val words = word.split(" ")

        val pos1 = words[0].split("-")[0].toInt() - 1 // - 1 pga index
        val pos2 = words[0].split("-")[1].toInt() - 1
        val specLetter = words[1][0]
        val password = words[2]


        // Måste prova && först pga kan bli fel i algoritm annars!
        // Annars så plussar den true pga OR kollar bara om 1 är, men båda får ej va samma enligt spec!
        return if(password[pos1] == specLetter && password[pos2] == specLetter) false
            else password[pos1] == specLetter || password[pos2] == specLetter // kommer bli true / false, expression
    }

    fun partOneActual(): Int{
        var count = 0

        for (line in listActual) {
            if(checkValidPartOne(line)) count++
        }

        return count
    }


    fun partTwoActual(): Int{
        var count = 0

        for (line in listActual) {
            if(checkValidPartTwo(line)) count++
        }

        return count
    }

    // EFTER, LÖSNING MHA https://www.youtube.com/watch?v=MyvJ7G6aErQ&ab_channel=KotlinbyJetBrains
    // Använder en data class för att store password + policy för varje line
    data class PasswordWithPolicy(
        val password: String,
        val range: IntRange,
        val letter: Char){ // constructor, equals, hashCode, toString

        fun validatePartOne() = password.count{ it == letter } in range
        // x in range
        // is equivalent to
        // range.first <= x && x <= range.last

        // Någon måste va sant, inte båda, då blir de false "xor"
        fun validatePartTwo() = (password[range.first -1] == letter) xor
                (password[range.last - 1] == letter)

        //An object declaration inside a class can be marked with the companion keyword
        // Typ som en statisk metod i klasen
        companion object {
            private val regex = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")
            fun parseUsingRegex(line: String): PasswordWithPolicy? =
                regex.matchEntire(line)?.destructured
                    ?.let { (start, end, letter, password) -> PasswordWithPolicy(password, start.toInt()..end.toInt(), letter.single()) }
            }
        }


    fun partOneImproved(): Int {
        val passwords = listActual
            .map (PasswordWithPolicy::parseUsingRegex)
        val count = passwords.count { it!!.validatePartOne() }
        println(count)
        return count
    }

    // Används "xor" operator
    // a true, b true, a xor b == false
    // a true, b false a xor b == true
    fun partTwoImproved(): Int {
        val passwords = listActual
            .map (PasswordWithPolicy::parseUsingRegex)
        val count = passwords.count { it!!.validatePartTwo() }
        println(count)
        return count
    }

    @Test
    fun testMyVersionIsSameAsImprovedPartOne() {
        assertEquals(partOneActual(), partOneImproved())
    }

    @Test
    fun testMyVersionIsSameAsImprovedPartTwo() {
        assertEquals(partTwoActual(), partTwoImproved())
    }

}


