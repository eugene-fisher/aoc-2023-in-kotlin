package hotsprings

import hotsprings.ConditionRecord.Condition.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HotSpringAnalyzerTest {

    private val sut = HotSpringAnalyzer()

    @Test
    fun `given record with impossible groups when analyzePossibleArrangements() then 0 returned`() {
        val input = ConditionRecord(springs = listOf(OPERATIONAL, BROKEN, OPERATIONAL), brokenGroups = listOf(1, 1))
        val expected = 0

        val actual = sut.analyzePossibleArrangements(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given possible record without unknowns when analyzePossibleArrangements() then 1 returned`() {
        val input = ConditionRecord(springs = listOf(OPERATIONAL, BROKEN, OPERATIONAL), brokenGroups = listOf(1))
        val expected = 1

        val actual = sut.analyzePossibleArrangements(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given UUUOBBB-1,1,3 when analyzePossibleArrangements() then 1 returned`() {
        val input = ConditionRecord(
            springs = listOf(UNKNOWN, UNKNOWN, UNKNOWN, OPERATIONAL, BROKEN, BROKEN, BROKEN),
            brokenGroups = listOf(1, 1, 3)
        )
        val expected = 1

        val actual = sut.analyzePossibleArrangements(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given OUUOOUUOOOUBB O-1,1,3 when analyzePossibleArrangements() then 4 returned`() {
        val input = ConditionRecord(
            springs = listOf(OPERATIONAL, UNKNOWN, UNKNOWN, OPERATIONAL, OPERATIONAL, UNKNOWN, UNKNOWN, OPERATIONAL, OPERATIONAL, OPERATIONAL, UNKNOWN, BROKEN, BROKEN, OPERATIONAL),
            brokenGroups = listOf(1, 1, 3)
        )
        val expected = 4

        val actual = sut.analyzePossibleArrangements(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given UBUBUBUBUBUBUBU-1,3,1,6 when analyzePossibleArrangements() then 1 returned`() {
        val input = ConditionRecord(
            springs = listOf(UNKNOWN, BROKEN, UNKNOWN, BROKEN, UNKNOWN, BROKEN, UNKNOWN, BROKEN, UNKNOWN, BROKEN, UNKNOWN, BROKEN, UNKNOWN, BROKEN, UNKNOWN),
            brokenGroups = listOf(1, 3, 1, 6)
        )
        val expected = 1

        val actual = sut.analyzePossibleArrangements(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given UUUUOBOOOBOOO-4,1,1 when analyzePossibleArrangements() then 1 returned`() {
        val input = ConditionRecord(
            springs = listOf(UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, OPERATIONAL, BROKEN, OPERATIONAL, OPERATIONAL, OPERATIONAL, BROKEN, OPERATIONAL, OPERATIONAL, OPERATIONAL),
            brokenGroups = listOf(4, 1, 1)
        )
        val expected = 1

        val actual = sut.analyzePossibleArrangements(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given UUUUOBBBBBBOOBBBBB-1,6,5 when analyzePossibleArrangements() then 4 returned`() {
        val input = ConditionRecord(
            springs = listOf(UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, OPERATIONAL, BROKEN, BROKEN, BROKEN, BROKEN, BROKEN, BROKEN, OPERATIONAL, OPERATIONAL, BROKEN, BROKEN, BROKEN, BROKEN, BROKEN),
            brokenGroups = listOf(1, 6, 5)
        )
        val expected = 4

        val actual = sut.analyzePossibleArrangements(input)

        assertEquals(expected, actual)
    }

    @Test
    fun `given UBBBUUUUUUUU-3,2,1 when analyzePossibleArrangements() then 10 returned`() {
        val input = ConditionRecord(
            springs = listOf(UNKNOWN, BROKEN, BROKEN, BROKEN, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN, UNKNOWN),
            brokenGroups = listOf(3, 2, 1)
        )
        val expected = 10

        val actual = sut.analyzePossibleArrangements(input)

        assertEquals(expected, actual)
    }
}