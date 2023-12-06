package race

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RacesParserTest {

    @Test
    fun `given Time_7_15_30 Distance_9_40_200 when parseRaces() then List of Race returned`() {
        val inputTimes = "Time:      7  15   30"
        val inputDistances = "Distance:  9  40  200"
        val expected = listOf(
            Race(time = 7, distance = 9),
            Race(time = 15, distance = 40),
            Race(time = 30, distance = 200),
        )

        val actual = RacesParser().parseRaces(times = inputTimes, distances = inputDistances)

        assertEquals(expected, actual)
    }

    @Test
    fun `given Time_7_15_30 Distance_9_40_200 when parseRace() then Race returned`() {
        val inputTime = "Time:      7  15   30"
        val inputDistance = "Distance:  9  40  200"
        val expected = Race(time = 71530, distance = 940200)

        val actual = RacesParser().parseRace(time = inputTime, distance = inputDistance)

        assertEquals(expected, actual)
    }
}