package race

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RaceKtTest {
    @Test
    fun `given Race(time = 7, distance = 9) when waysToWin() then 4 returned`() {
        val input = Race(time = 7, distance = 9)
        val expected = 4

        val actual = input.waysToWin()

        assertEquals(expected, actual)
    }
}