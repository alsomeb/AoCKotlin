import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class AoCDay2Year2021Test {


    private val reader = FileToList()
    private val list = reader.getListFromFile("C:\\Users\\46760\\Desktop\\AoCKotlin\\src\\main\\resources\\AoCDay2Year2021Test.txt")


    @Test
    fun testReadFileReturnsCorrectFormat() {
        assertEquals(6, list.size)
        assertEquals("forward 5", list[0])
        assertEquals("down 5", list[1])
        assertEquals("forward 8", list[2])
        assertEquals("up 3", list[3])
        assertEquals("down 8", list[4])
        assertEquals("forward 2", list[5])
        assertNotEquals(5, list.size)
        assertNotEquals(7, list.size)
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


        for (line in list) addCourseToTotal(line.split(" "))


        assertEquals(15, totalHorizontal)
        assertEquals(10, totalDepth)
        assertEquals(150, totalDepth * totalHorizontal)
    }


}