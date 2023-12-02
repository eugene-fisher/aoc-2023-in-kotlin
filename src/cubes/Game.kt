package cubes

data class Game(
    val id: Int,
    val revealed: List<Cubes>,
)

fun Game.isPossible(total: Cubes): Boolean = revealed.all {
    it.red <= total.red && it.green <= total.green && it.blue <= total.blue
}

val Game.minCubesPossible: Cubes get() = revealed.reduce { acc, cubes ->
    Cubes(
        red = if (cubes.red > acc.red) cubes.red else acc.red,
        green = if (cubes.green > acc.green) cubes.green else acc.green,
        blue = if (cubes.blue > acc.blue) cubes.blue else acc.blue,
    )
}
