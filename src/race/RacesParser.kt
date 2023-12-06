package race

class RacesParser {

    fun parseRaces(times: String, distances: String): List<Race> {
        val parsedTimes = times.parseLongs("Time:")
        val parsedDistances = distances.parseLongs("Distance:")
        require(parsedTimes.size == parsedDistances.size)

        return parsedTimes.zip(parsedDistances) { time, distance ->
            Race(time = time, distance = distance)
        }
    }

    fun parseRace(time: String, distance: String): Race = Race(
        time = time.parseSeparatedLong("Time:"),
        distance = distance.parseSeparatedLong("Distance:"),
    )

    private fun String.parseLongs(prefix: String): List<Long> {
        return removePrefix(prefix)
            .split(' ')
            .filter { it.isNotBlank() }
            .map { it.toLong() }
    }

    private fun String.parseSeparatedLong(prefix: String): Long {
        return removePrefix(prefix).replace(" ", "").toLong()
    }
}