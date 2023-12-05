package almanac

import java.math.BigInteger

interface SeedsParser {
    fun parseSeeds(line: String): Iterable<BigInteger>
}

class NumberOfSeedsParser : SeedsParser {
    override fun parseSeeds(line: String): Iterable<BigInteger> {
        return line.substring(startIndex = "seeds:".length + 1, endIndex = line.length)
            .split(' ')
            .map { it.toBigInteger() }
    }
}

class RangeOfSeedsParser : SeedsParser {
    override fun parseSeeds(line: String): Iterable<BigInteger> {
        val list = line.substring(startIndex = "seeds:".length + 1, endIndex = line.length)
            .split(' ')
            .map { it.toBigInteger() }

        val ranges = Array(list.size / 2) {
            val start = list[it * 2]
            val length = list[it * 2 + 1]
            start..<(start + length)
        }.sortedBy { it.start }

        return object : Iterable<BigInteger> {
            override fun iterator(): Iterator<BigInteger> {
                return object : Iterator<BigInteger> {
                    var current: BigInteger? = null

                    override fun hasNext(): Boolean = nextValue() != null

                    override fun next(): BigInteger = nextValue()!!.also { current = it }

                    private fun nextValue(): BigInteger? {
                        val value = current
                        if (value == null) {
                            return ranges.first().start
                        } else {
                            ranges.forEachIndexed { index, range ->
                                if (value in range && value < range.endExclusive.dec()) {
                                    return value.inc()
                                }
                                if (value in range && value == range.endExclusive.dec() && index != ranges.size.dec()) {
                                    return ranges[index.inc()].start
                                }
                            }
                        }
                        return null
                    }
                }
            }
        }
    }
}