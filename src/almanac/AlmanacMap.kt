package almanac

import java.math.BigInteger

class AlmanacMap(
    private val ranges: List<Range>,
) {
    fun get(source: BigInteger): BigInteger {
        return ranges.firstOrNull { it.contains(source) }?.destination(source) ?: source
    }

    private fun Range.contains(source: BigInteger) = source in sourceStartIndex..<(sourceStartIndex + length)

    private fun Range.destination(source: BigInteger): BigInteger {
        require(contains(source))
        val delta = source - sourceStartIndex
        return destinationStartIndex + delta
    }

    data class Range(
        val destinationStartIndex: BigInteger,
        val sourceStartIndex: BigInteger,
        val length: BigInteger,
    ) {

        companion object {
            fun parse(line: String): Range {
                val rangePointers = line.split(' ').map { it.toBigInteger() }
                return Range(
                    destinationStartIndex = rangePointers[0],
                    sourceStartIndex = rangePointers[1],
                    length = rangePointers[2],
                )
            }
        }
    }
}