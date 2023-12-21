package pipelines

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PipelinePartTest {

    @Test
    fun parse() {
        val input = "{x=787,m=2655,a=1222,s=2876}"
        val expected = PipelinePart(x = 787, m = 2655, a = 1222, s = 2876)

        val actual = PipelinePart.parse(input)

        assertEquals(expected, actual)
    }
}