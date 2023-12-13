package hotsprings

import java.lang.IllegalArgumentException

data class ConditionRecord(
    val springs: List<Condition>,
    val brokenGroups: List<Int>,
) {

    override fun toString(): String {
        return springs.joinToString("") {
            when (it) {
                Condition.OPERATIONAL -> "."
                Condition.BROKEN -> "#"
                Condition.UNKNOWN -> "?"
            }
        } + "-" + brokenGroups.joinToString(",")
    }

    enum class Condition {
        OPERATIONAL,
        BROKEN,
        UNKNOWN,
    }

    companion object {
        fun parse(record: String): ConditionRecord {
            val (conditions, groups) = record.split(' ').also { require(it.size == 2) }
            return ConditionRecord(
                springs = conditions.map {
                    when (it) {
                        '.' -> Condition.OPERATIONAL
                        '#' -> Condition.BROKEN
                        '?' -> Condition.UNKNOWN
                        else -> throw IllegalArgumentException("Cant parse $it")
                    }
                },
                brokenGroups = groups.split(',').map { it.toInt() }
            )
        }

        fun ConditionRecord.unfold(times: Int): ConditionRecord {
            val unfoldedSprings = ArrayList(springs)
            val unfoldedGroups = ArrayList(brokenGroups)
            repeat(times) {
                unfoldedSprings.add(Condition.UNKNOWN)
                unfoldedSprings.addAll(springs)
                unfoldedGroups.addAll(brokenGroups)
            }

            return ConditionRecord(springs = unfoldedSprings, brokenGroups = unfoldedGroups)
        }
    }
}