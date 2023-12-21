package math

fun lcm(numbers: LongArray) = numbers.reduce { acc, number -> lcm(acc, number) }

fun lcm(a: Long, b: Long): Long {
    val larger = if (a > b) a else b
    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm.mod(a) == 0L && lcm.mod(b) == 0L) {
            return lcm
        }
        lcm += larger
    }
    return maxLcm
}