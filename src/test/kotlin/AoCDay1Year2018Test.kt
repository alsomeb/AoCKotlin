import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AoCDay1Year2018Test {

    // Har @TEST på vissa funktioner för att kunna köra dem och skriva ut resultat för AOC

    private val reader = FileToList()
    private val listTest = listOf("+1", "-2", "+3", "+1")
    private val listTest2 = listOf("+1", "+1", "-2")
    private val listTest3 = listOf("-1", "-2", "-3")
    private val listTestDupesFreq = listOf("+1", "-2", "+3", "+1", "+1", "-2")
    private val listActual = reader.getListFromFileImproved("src/main/resources/AoCDay1Year2018Actual.txt")



    @Test
    fun testWeCanAdditionAndSubtractionFromStringList() {
        assertEquals(4, listTest[0].toInt() + listTest[2].toInt())
        assertEquals(-1, listTest[0].toInt() + listTest[1].toInt())
    }

    @Test
    fun testOurFancyRecursiveFunctionReturnsCorrect() {
        val expected = 3
        val expected2 = 0
        val expected3 = -6
        assertEquals(expected, sum(listTest))
        assertEquals(expected2, sum(listTest2))
        assertEquals(expected3, sum(listTest3))
    }


    @Test
    fun testAlgoWorksForPartTwo() {
        val freqAsIntList = listTestDupesFreq.map { it.toInt() }
        val testList2 = listOf(-6, 3, 8, 5, -6)
        val testList3 = listOf(3, 3, 4, -2, -4)
        val actualNr = findDupeFreq(freqAsIntList)
        val actualNr2 = findDupeFreq(testList2)
        val actualNr3 = findDupeFreq(testList3)

        assertEquals(2, actualNr)
        assertEquals(5, actualNr2)
        assertEquals(10, actualNr3)
        assertTrue { freqAsIntList.take(3) == listOf(1, -2, 3) }
        assertTrue { freqAsIntList.takeLast(2) == listOf(1, -2) }

    }

    // FÖRE MIN EGNA LÖSNING
    // Rekursive Funktion for fun, vi startar med Frequency 0 Enligt Instruktion
    fun sum(nrsStringList: List<String>): Int {
        val nrs = nrsStringList.map { it.toInt() }

        tailrec fun doSum(sum: Int, counter: Int): Int {
            return if (counter < 0) sum // Stop
            else doSum(sum + nrs[counter], counter - 1)
        }

        return doSum(0, nrs.size - 1)
    }

    fun findDupeFreq(list: List<Int>): Int {
        val seen: MutableSet<Int> = mutableSetOf()
        var values = list
        var total = 0

        // Går igenom values på listan och sedan repeat tills den hittar dupe freq
        // Använder Set pga får ej finnas dupes där
        while (!seen.contains(total)) {
            // Adds the specified element to the set.
            // true if the element has been added,
            // false if the element is already contained in the set.
            seen.add(total)
            if (values.isEmpty()) {
                values = list // Refresh Full List
            }
            // Hämtar alltid den på första platsen, sen droppar vi den nedan, då hamnar index 1 på 0
            total += values[0]

            // Returns a list containing all elements except first n elements.
            values = values.drop(1)
        }

        return total
    }


    @Test
    fun partOne() = println(sum(listActual))


    fun partTwo(): Int {
        val freqAsIntList = listActual.map { it.toInt() }
        return findDupeFreq(freqAsIntList)
    }

    // EFTER MHA Todd Ginsberg https://todd.ginsberg.com/post/advent-of-code/2018/day1/
    // Hans part 1 var lika som min, då jag lärt mig av honom tidigare ang sum() och sumOf()
    // Jag tyckte hans lösning var extremt svår och jag förstår inte allt, min var enklare att förstå
    // Men det är nog pga jag inte kan så mycket Kotlin Co Routines

    // Pga Del 2 måste man Loopa igenom listan flera gånger, så har han använt något som heter "Infinite Sequence" med Kotlin Co Routines
    // Han lägger till en Extension function på List möjliga functions

    // All this does is check that our list isn’t empty (in this case it’s not, but I wanted to be thorough), and if not, just keep yielding the entire list.
    // Kotlin will turn this into a sequence for us.
    fun <T> List<T>.toInfiniteSequence(): Sequence<T> = sequence {
        if(this@toInfiniteSequence.isEmpty()) {
            return@sequence
        }
        while (true) {
            // Co Routine i kotlin
            // yielding an infinite sequence
            yieldAll(this@toInfiniteSequence)
        }
    }

    // In this solution, we use our infinite sequence to provide values that we map to the existing sum.
    // This turns our sequence of inputs into a sequence of sums. Then we just stop the first time we’ve seen the sum before, by testing against our frequencies set.
    fun solvePart2Improved(): Int {
        val nrs = listActual.map { it.toInt() }

        val frequencies = mutableSetOf(0)
        var sum = 0
        return nrs.toInfiniteSequence()
            .map { sum += it
                sum
            }
            .first { !frequencies.add(it) }
    }

    @Test
    fun testMyPartTwoHasSameValueAsImprovedVersion() {
        assertEquals(partTwo(), solvePart2Improved())
    }
}

