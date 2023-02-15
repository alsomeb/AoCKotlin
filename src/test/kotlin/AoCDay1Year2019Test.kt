import kotlin.math.floor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class AoCDay1Year2019Test {


    private val reader = FileToList()
    private val listTest = reader.getListFromFile("C:\\Users\\alex\\Desktop\\Nackademin\\Funktionell Programmering\\AoCKotlin\\src\\main\\resources\\AoCDay1Year2019Test.txt")
    private val listActual = reader.getListFromFile("C:\\Users\\alex\\Desktop\\Nackademin\\Funktionell Programmering\\AoCKotlin\\src\\main\\resources\\AoCDay1Year2019Actual.txt")


    @Test
    fun testReadFileReturnsCorrectFormat() {
        assertEquals(12, listTest[0].toInt())
        assertEquals(14, listTest[1].toInt())
        assertEquals(1969, listTest[2].toInt())
        assertEquals(100756, listTest[3].toInt())
    }

    @Test
    fun testIsFuelCalculationCorrectPartOne(){
        var total = 0.0
        val expected = 34241.0

        listTest.forEach{ total += fuelRequired(it.toDouble()) }

        assertEquals(expected, total)
        assertEquals(2.0, fuelRequired(listTest[0].toDouble()))
        assertEquals(2.0, fuelRequired(listTest[1].toDouble()))
        assertNotEquals(2.0, fuelRequired(listTest[2].toDouble()))
    }

    @Test
    fun testIsFuelCalculationCorrectPartTwo() {
        val expected = 51316.0
        val sum = listTest.sumOf { fuelWithFuel(it.toDouble()) }
        assertEquals(expected, sum)
    }

    @Test
    fun checkMyPartOneHasSameAnswerAsImprovedPartOne() {
        assertEquals(partOne(), part1Improved())
    }

    @Test
    fun checkMyPartTwoHasSameAnswerAsImprovedPartTwo() {
        assertEquals(partTwo(), part2Improved())
    }

    // FÖRE, EGEN LÖSNING AoC Dag 1 År 2019


    // Formeln för att calc fuel requirements
    fun fuelRequired(mass: Double) = floor((mass / 3)) - 2


    // Recursive function for part Two
    // return the 0 if we’re asked to calculate the fuel required for a value under 9.
    // Why 9? Because anything less will yield a negative or zero fuel value.
    fun fuelWithFuel(total: Double): Double {
        return if (total < 9) 0.0
        else {
            val mass = fuelRequired(total)
            mass + fuelWithFuel(mass)
        }
    }


    fun partOne(): Int {
        var sumFuelReq = 0.0
        listActual.forEach{ sumFuelReq += fuelRequired(it.toDouble()) }

        println(sumFuelReq.toInt())
        return sumFuelReq.toInt()
    }

    fun partTwo(): Int {
        // Returns the sum of all values produced by selector function applied to each element in the collection
        val sum = listActual.sumOf { fuelWithFuel(it.toDouble()) }
        println(sum.toInt())
        return sum.toInt()
    }


    // EFTER MHA .. Tod GinsBerg
    // https://todd.ginsberg.com/post/advent-of-code/2019/day1/
    // Tod Har gjort Liknande fast mha EXTENSION FUNCTIONS, vilket var perfekt för mig att träna på

    // Behöver ej math floor, om vi ret Int
    private fun Int.fuel(): Int = (this / 3) - 2

    // Rekursive Extension Function
    //  Return the 0 if we’re asked to calculate the fuel required for a value under 9.
    //  Why 9? Because anything less will yield a negative or zero fuel value.
    private fun Int.fuelWithFuel(): Int =
        if(this < 9)  {
            0
        } else {
            val fuel = this.fuel()
            fuel + fuel.fuelWithFuel()
        }

    fun part1Improved(): Int {
        // sedan iter över listan mha sumOf, fancy metod i Kotlin
        return listActual.sumOf { it.toInt().fuel() }
    }

    fun part2Improved(): Int {
        return listActual.sumOf { it.toInt().fuelWithFuel() }
    }




}