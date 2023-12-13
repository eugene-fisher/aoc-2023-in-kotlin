package hotsprings

import hotsprings.ConditionRecord.Companion.unfold
import hotsprings.ConditionRecord.Condition.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ConditionRecordTest {

    @Test
    fun unfold0() {
        val input = ConditionRecord(springs = listOf(UNKNOWN, OPERATIONAL, BROKEN), brokenGroups = listOf(2, 3))
        val expected = ConditionRecord(springs = listOf(UNKNOWN, OPERATIONAL, BROKEN), brokenGroups = listOf(2, 3))

        val actual = input.unfold(0)

        assertEquals(expected, actual)
    }

    @Test
    fun unfold1() {
        val input = ConditionRecord(springs = listOf(UNKNOWN, OPERATIONAL, BROKEN), brokenGroups = listOf(2, 3))
        val expected = ConditionRecord(springs = listOf(UNKNOWN, OPERATIONAL, BROKEN, UNKNOWN, UNKNOWN, OPERATIONAL, BROKEN), brokenGroups = listOf(2, 3, 2, 3))

        val actual = input.unfold(1)

        assertEquals(expected, actual)
    }

    @Test
    fun unfold2() {
        val input = ConditionRecord(springs = listOf(UNKNOWN, OPERATIONAL, BROKEN), brokenGroups = listOf(2, 3))
        val expected = ConditionRecord(springs = listOf(UNKNOWN, OPERATIONAL, BROKEN, UNKNOWN, UNKNOWN, OPERATIONAL, BROKEN, UNKNOWN, UNKNOWN, OPERATIONAL, BROKEN), brokenGroups = listOf(2, 3, 2, 3, 2, 3))

        val actual = input.unfold(2)

        assertEquals(expected, actual)
    }

    @Test
    fun unfold3() {
        val input = ConditionRecord(springs = listOf(UNKNOWN, OPERATIONAL, BROKEN), brokenGroups = listOf(2, 3))
        val expected = ConditionRecord(springs = listOf(UNKNOWN, OPERATIONAL, BROKEN, UNKNOWN, UNKNOWN, OPERATIONAL, BROKEN, UNKNOWN, UNKNOWN, OPERATIONAL, BROKEN, UNKNOWN, UNKNOWN, OPERATIONAL, BROKEN), brokenGroups = listOf(2, 3, 2, 3, 2, 3, 2, 3))

        val actual = input.unfold(3)

        assertEquals(expected, actual)
    }
}