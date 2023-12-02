package cubes

data class Cubes(
    val red: Int = 0,
    val green: Int = 0,
    val blue: Int = 0,
)

val Cubes.power get() = red * green * blue