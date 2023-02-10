import java.io.File
import java.io.InputStream

class FileToList() {

    /*
        There are two ways to read all lines of a file using File directly:

        File.useLines() method along with Kotlin Sequence
        File.readLines() method to return a List<String>
     */

    fun getListFromFIle(path: String): List<String> {
        val inputStream: InputStream = File(path).inputStream()
        val list = mutableListOf<String>()

        // useLines, readLines  It will automatically close the reader once the processing is complete automatically
        // return inputStream.bufferedReader().readLines()
        inputStream.bufferedReader().useLines { lines -> lines.forEach { list.add(it.trim().replace(" ", "")) }  }
        return list
    }
}