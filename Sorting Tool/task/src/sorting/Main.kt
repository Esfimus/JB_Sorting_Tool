package sorting

import java.io.File
import java.util.Scanner
import kotlin.math.roundToInt

class NoSortingType : Exception()
class NoDataType : Exception()

/**
 * Displays the result to the console or creates the file
 */
fun result(result:String, outputFileName: String) {
    if (outputFileName != "") {
        val outputFile = File(outputFileName)
        outputFile.writeText(result)
    } else {
        println(result)
    }
}

/**
 * Sorts the given numbers by count
 */
fun numberSortCount(inputFileName: String, outputFileName: String) {
    val scanner = Scanner(System.`in`)
    val numbersList = mutableListOf<Int>()
    val inputFile = File(inputFileName)
    // reading numbers if the file exists
    if (inputFile.exists()) {
        val text: String = inputFile.readText()
        val regex = "\\s+".toRegex()
        val wordsList = text.split(regex).map { it }.toMutableList()
        for (word in wordsList) {
            if (word.toIntOrNull() == null) {
                println("\"$word\" is not a long. It will be skipped." )
            } else {
                numbersList.add(word.toInt())
            }
        }
        // reading numbers from the console
    } else {
        do {
            if (scanner.hasNextInt()) {
                val num = scanner.nextInt()
                numbersList.add(num)
            } else {
                println("\"${scanner.next()}\" is not a long. It will be skipped." )
            }
        } while (scanner.hasNext())
    }
    // counting numbers and creating a map
    val numbersMap = mutableMapOf<Int, Int>()
    val totalNumbers = numbersList.size
    for (num in numbersList) {
        if (!numbersMap.containsKey(num)) {
            numbersMap += num to 1
        } else {
            numbersMap[num] = numbersMap.getValue(num) + 1
        }
    }
    // sorting the map by keys and then by values
    val sortedKeysMap = numbersMap.toList().sortedBy { (key) -> key }.toMap()
    val sortedValuesMap = sortedKeysMap.toList().sortedBy { (value) -> value }.toMap()
    // creating the resulting string
    var resultString = "Total numbers: $totalNumbers."
    for ((k, v) in sortedValuesMap) {
        val countPercent = v.toDouble() * 100.00 / totalNumbers.toDouble()
        resultString += "\n$k: $v time(s), ${countPercent.roundToInt()}%"
    }
    result(resultString, outputFileName)
}

/**
 * Sorts the given lines by count
 */
fun lineSortCount(inputFileName: String, outputFileName: String) {
    val scanner = Scanner(System.`in`)
    var linesList = mutableListOf<String>()
    val inputFile = File(inputFileName)
    // reading lines if the file exists
    if (inputFile.exists()) {
        linesList = inputFile.readLines().toMutableList()
    // reading lines from the console
    } else {
        do {
            val line = scanner.nextLine()
            linesList.add(line)
        } while (scanner.hasNext())
    }
    // counting lines and creating a map
    val listMap = mutableMapOf<String, Int>()
    val totalWords = linesList.size
    for (line in linesList) {
        if (!listMap.containsKey(line)) {
            listMap += line to 1
        } else {
            listMap[line] = listMap.getValue(line) + 1
        }
    }
    // sorting the map by keys and then by values
    val sortedKeysMap = listMap.toList().sortedBy { (key) -> key }.toMap()
    val sortedValuesMap = sortedKeysMap.toList().sortedBy { (value) -> value }.toMap()
    // creating the resulting string
    var resultString = "Total lines: $totalWords."
    for ((k, v) in sortedValuesMap) {
        val countPercent = v.toDouble() * 100.00 / totalWords.toDouble()
        resultString += "\n$k: $v time(s), ${countPercent.roundToInt()}%"
    }
    result(resultString, outputFileName)
}

/**
 * Sorts the given words by count
 */
fun wordSortCount(inputFileName: String, outputFileName: String) {
    val scanner = Scanner(System.`in`)
    var wordsList = mutableListOf<String>()
    val inputFile = File(inputFileName)
    // reading words if the file exists
    if (inputFile.exists()) {
        val text: String = inputFile.readText()
        val regex = "\\s+".toRegex()
        wordsList = text.split(regex).map { it }.toMutableList()
    // reading words from the console
    } else {
        do {
            val word = scanner.next()
            wordsList.add(word)
        } while (scanner.hasNext())
    }
    // counting words and creating a map
    val wordsMap = mutableMapOf<String, Int>()
    val totalWords = wordsList.size
    for (word in wordsList) {
        if (!wordsMap.containsKey(word)) {
            wordsMap += word to 1
        } else {
            wordsMap[word] = wordsMap.getValue(word) + 1
        }
    }
    // sorting the map by keys and then by values
    val sortedKeysMap = wordsMap.toList().sortedBy { (key) -> key }.toMap()
    val sortedValuesMap = sortedKeysMap.toList().sortedBy { (value) -> value }.toMap()
    // creating the resulting string
    var resultString = "Total words: $totalWords."
    for ((k, v) in sortedValuesMap) {
        val countPercent = v.toDouble() * 100.00 / totalWords.toDouble()
        resultString += "\n$k: $v time(s), ${countPercent.roundToInt()}%"
    }
    result(resultString, outputFileName)
}

/**
 * Reads numbers and sorts them in ascending order
 */
fun numberSortNaturally(inputFileName: String, outputFileName: String) {
    val scanner = Scanner(System.`in`)
    val numbersList = mutableListOf<Int>()
    val inputFile = File(inputFileName)
    // reading numbers if the file exists
    if (inputFile.exists()) {
        val text: String = inputFile.readText()
        val regex = "\\s+".toRegex()
        val wordsList = text.split(regex).map { it }.toMutableList()
        for (word in wordsList) {
            if (word.toIntOrNull() == null) {
                println("\"$word\" is not a long. It will be skipped." )
            } else {
                numbersList.add(word.toInt())
            }
        }
    // reading numbers from the console
    } else {
        do {
            if (scanner.hasNextInt()) {
                val num = scanner.nextInt()
                numbersList.add(num)
            } else {
                println("\"${scanner.next()}\" is not a long. It will be skipped." )
            }
        } while (scanner.hasNext())
    }
    // creating the resulting string
    val resultString = "Total numbers: ${numbersList.size}." +
            "\nSorted data: ${quickSortInt(numbersList).joinToString(" ")}"
    result(resultString, outputFileName)
}

/**
 * Recursively sorts given list of integers
 */
fun quickSortInt(list: MutableList<Int>): MutableList<Int> {
    if (list.size < 2) {
        return list
    } else {
        val midValue = list[list.size / 2]
        val lessList = mutableListOf<Int>()
        val greaterList = mutableListOf<Int>()
        for (i in 0 until list.size) {
            if (i == list.size / 2) continue
            if (list[i] >= midValue) {
                greaterList.add(list[i])
            } else {
                lessList.add(list[i])
            }
        }
        val sortedList = mutableListOf<Int>()
        sortedList.addAll(quickSortInt(lessList))
        sortedList.add(midValue)
        sortedList.addAll(quickSortInt(greaterList))
        return sortedList
    }
}

/**
 * Reads words and sorts them in ascending order
 */
fun wordSortNaturally(inputFileName: String, outputFileName: String) {
    val scanner = Scanner(System.`in`)
    var wordsList = mutableListOf<String>()
    val inputFile = File(inputFileName)
    // reading words if the file exists
    if (inputFile.exists()) {
        val text: String = inputFile.readText()
        val regex = "\\s+".toRegex()
        wordsList = text.split(regex).map { it }.toMutableList()
    // reading words from the console
    } else {
        do {
            val word = scanner.next()
            wordsList.add(word)
        } while (scanner.hasNext())
    }
    // creating the resulting string
    val resultString = "Total words: ${wordsList.size}." +
            "\nSorted data: ${quickSortString(wordsList).joinToString(" ")}"
    result(resultString, outputFileName)
}

/**
 * Recursively sorts given list of strings both words and lines
 */
fun quickSortString(list: MutableList<String>): MutableList<String> {
    if (list.size < 2) {
        return list
    } else {
        val midValue = list[list.size / 2]
        val lessList = mutableListOf<String>()
        val greaterList = mutableListOf<String>()
        for (i in 0 until list.size) {
            if (i == list.size / 2) continue
            if (list[i] >= midValue) {
                greaterList.add(list[i])
            } else {
                lessList.add(list[i])
            }
        }
        val sortedList = mutableListOf<String>()
        sortedList.addAll(quickSortString(lessList))
        sortedList.add(midValue)
        sortedList.addAll(quickSortString(greaterList))
        return sortedList
    }
}

/**
 * Reads lines and sorts them in ascending order
 */
fun lineSortNaturally(inputFileName: String, outputFileName: String) {
    val scanner = Scanner(System.`in`)
    var linesList = mutableListOf<String>()
    val inputFile = File(inputFileName)
    // reading lines if the file exists
    if (inputFile.exists()) {
        linesList = inputFile.readLines().toMutableList()
    // reading lines from the console
    } else {
        do {
            val line = scanner.nextLine()
            linesList.add(line)
        } while (scanner.hasNext())
    }
    // creating the resulting string
    val sortedLines = quickSortString(linesList)
    var resultString = "Total lines: ${linesList.size}.\nSorted data:\n"
    for (line in sortedLines) {
        resultString += line + "\n"
    }
    result(resultString, outputFileName)
}

/**
 * Chooses the sorting and date types according to the input arguments
 */
fun sortingSelection(sorting: String, data: String, inputFileName: String, outputFileName: String) {
    if (sorting == "natural") {
        when (data) {
            "long" -> numberSortNaturally(inputFileName, outputFileName)
            "line" -> lineSortNaturally(inputFileName, outputFileName)
            "word" -> wordSortNaturally(inputFileName, outputFileName)
        }
    } else if (sorting == "byCount") {
        when (data) {
            "long" -> numberSortCount(inputFileName, outputFileName)
            "line" -> lineSortCount(inputFileName, outputFileName)
            "word" -> wordSortCount(inputFileName, outputFileName)
        }
    }
}

fun main(args: Array<String>) {
    var dataType = "word"
    var sortingType = "natural"
    var inputFileName = ""
    var outputFileName = ""
    try {
        // setting sorting type
        if (args.contains("-sortingType") &&
            args.size > args.indexOf("-sortingType") + 1 &&
            args[args.indexOf("-sortingType") + 1] == "byCount") {
            sortingType = "byCount"
        } else if (args.contains("-sortingType") &&
            args.size > args.indexOf("-sortingType") + 1 &&
            args[args.indexOf("-sortingType") + 1] == "natural") {
            sortingType = "natural"
        } else if (args.contains("-sortingType")) {
            throw NoSortingType()
        }
        // setting data type
        if (args.contains("-dataType") &&
            args.size > args.indexOf("-dataType") + 1 &&
            args[args.indexOf("-dataType") + 1] == "long") {
            dataType = "long"
        } else if (args.contains("-dataType") &&
            args.size > args.indexOf("-dataType") + 1 &&
            args[args.indexOf("-dataType") + 1] == "line") {
            dataType = "line"
        } else if (args.contains("-dataType") &&
            args.size > args.indexOf("-dataType") + 1 &&
            args[args.indexOf("-dataType") + 1] == "word") {
            dataType = "word"
        } else if (args.contains("-dataType")) {
            throw NoDataType()
        }
        // checking for input na output files
        if (args.contains("-inputFile") &&
            args.size > args.indexOf("-inputFile") + 1) {
            inputFileName = args[args.indexOf("-inputFile") + 1]
        }
        if (args.contains("-outputFile") &&
            args.size > args.indexOf("-outputFile") + 1) {
            outputFileName = args[args.indexOf("-outputFile") + 1]
        }
        // skipping unknown parameters
        for (arg in args) {
            if (arg.startsWith("-") &&
                arg != "-sortingType" &&
                arg != "-dataType" &&
                arg != "-inputFile" &&
                arg != "-outputFile") {
                println("\"$arg\" is not a valid parameter. It will be skipped.")
            }
        }
        sortingSelection(sortingType, dataType, inputFileName, outputFileName)
    } catch (e: NoSortingType) {
        println("No sorting type defined!")
    } catch (e: NoDataType) {
        println("No data type defined!")
    }
}