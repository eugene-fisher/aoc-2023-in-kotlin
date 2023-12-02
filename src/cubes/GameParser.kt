package cubes

class GameParser {

    private val gameRegex = Regex("Game ([0-9]+):((([^;]+);?)+)")
    private val drawsRegex = Regex(" ([^;]+);")
    private val greenRegex = Regex("([0-9]+) green")
    private val redRegex = Regex("([0-9]+) red")
    private val blueRegex = Regex("([0-9]+) blue")

    fun parseGame(game: String): Game {
        val match = requireNotNull(gameRegex.find(game))
        val draws = match.groupValues[2]
        return Game(
            id = match.groupValues[1].toInt(),
            revealed = drawsRegex.findAll("$draws;").map { it.groupValues[1] }.toList().map { draw ->
                Cubes(
                    red = redRegex.find(draw)?.groupValues?.get(1)?.toInt() ?: 0,
                    green = greenRegex.find(draw)?.groupValues?.get(1)?.toInt() ?: 0,
                    blue = blueRegex.find(draw)?.groupValues?.get(1)?.toInt() ?: 0,
                )
            }
        )
    }
}