package hotsprings

import hotsprings.ConditionRecord.Condition.*
import kotlin.math.pow

class HotSpringAnalyzer {

    fun analyzePossibleArrangements(record: ConditionRecord): Int {
        val unknowns = record.springs.count { it == UNKNOWN }
        if (unknowns == 0) {
            return if (generateBrokenGroups(record.springs) == record.brokenGroups) 1 else 0
        }
        return (0..<2.0.pow(unknowns.toDouble()).toLong().also { require(it >= 0) }).count { arrangement ->
            var unknownIndex = 0
            val possibleArrangement = record.springs.map { condition ->
                when {
                    condition != UNKNOWN -> condition
                    arrangement.shr(unknownIndex++).and(1) == 1L -> BROKEN
                    else -> OPERATIONAL
                }
            }
            generateBrokenGroups(possibleArrangement) == record.brokenGroups
        }
    }

    private fun generateBrokenGroups(springs: List<ConditionRecord.Condition>): List<Int> {
        val groups = mutableListOf<Int>()
        var currentGroup = 0
        springs.forEach {
            if (it == BROKEN) {
                currentGroup++
            } else {
                if (currentGroup > 0) {
                    groups.add(currentGroup)
                }
                currentGroup = 0
            }
        }
        if (currentGroup > 0) {
            groups.add(currentGroup)
        }
        return groups
    }
}