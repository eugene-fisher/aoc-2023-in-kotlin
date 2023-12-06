package race

data class Race(val time: Long, val distance: Long)

fun Race.waysToWin(): Int = (0..time).fold(0) { acc, charge ->
    val timeToTravel = time - charge
    if (charge * timeToTravel > distance) acc + 1 else acc
}