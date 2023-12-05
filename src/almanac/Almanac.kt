package almanac

import java.math.BigInteger

class Almanac(
    val seeds: Iterable<BigInteger> = emptyList(),
    private val maps: List<AlmanacMap>,
) {

    fun get(seed: BigInteger): BigInteger = maps.fold(initial = seed) { acc, map -> map.get(acc) }
}