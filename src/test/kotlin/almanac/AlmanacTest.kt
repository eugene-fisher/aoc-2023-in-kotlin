package almanac

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AlmanacTest {

    @Test
    fun `given maps (52 50 48),(0 15 37),(49 53 8),(18 25 70),(68 64 13),(0 69 1),(60 56 37) when get(79) then 82 returned`() {
        val input = listOf(
            AlmanacMap(listOf(AlmanacMap.Range(52.toBigInteger(), 50.toBigInteger(), 48.toBigInteger()))),
            AlmanacMap(listOf(AlmanacMap.Range(0.toBigInteger(), 15.toBigInteger(), 37.toBigInteger()))),
            AlmanacMap(listOf(AlmanacMap.Range(49.toBigInteger(), 53.toBigInteger(), 8.toBigInteger()))),
            AlmanacMap(listOf(AlmanacMap.Range(18.toBigInteger(), 25.toBigInteger(), 70.toBigInteger()))),
            AlmanacMap(listOf(AlmanacMap.Range(68.toBigInteger(), 64.toBigInteger(), 13.toBigInteger()))),
            AlmanacMap(listOf(AlmanacMap.Range(0.toBigInteger(), 69.toBigInteger(), 1.toBigInteger()))),
            AlmanacMap(listOf(AlmanacMap.Range(60.toBigInteger(), 56.toBigInteger(), 37.toBigInteger()))),
        )
        val expected = 82.toBigInteger()

        val actual = Almanac(maps = input).get(79.toBigInteger())

        assertEquals(expected, actual)
    }
}