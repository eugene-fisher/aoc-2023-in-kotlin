package pipelines

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import pipelines.Pipeline.Result.Accepted
import pipelines.Pipeline.Result.Redirected
import pipelines.Pipeline.Rule.Condition.*
import pipelines.PipelinePart.Feature.*

class PipelineTest {

    @Test
    fun f() {
        val sut = Pipeline("foo", rules = listOf(Pipeline.Rule(condition = Always, result = Accepted)))
        val expected = Accepted

        val actual = sut.process(PipelinePart(0, 0, 0, 0))

        assertEquals(expected, actual)
    }

    @Test
    fun f2() {
        val sut = Pipeline("foo", rules = listOf(Pipeline.Rule(condition = Always, result = Accepted)))
        val expected = Accepted

        val actual = sut.process(PipelinePart(0, 0, 0, s = 43))

        assertEquals(expected, actual)
    }

    @Test
    fun f3() {
        val sut = Pipeline(
            "foo", rules = listOf(
                Pipeline.Rule(condition = More(S, 42), result = Accepted),
                Pipeline.Rule(condition = Always, result = Pipeline.Result.Rejected),
            )
        )
        val expected = Pipeline.Result.Rejected

        val actual = sut.process(PipelinePart(0, 0, 0, s = 42))

        assertEquals(expected, actual)
    }

    @Test
    fun parse() {
        val input = "px{a<2006:qkq,m>2090:A,rfg}"
        val expected = Pipeline(
            name = "px", rules = listOf(
                Pipeline.Rule(condition = Less(A, 2006), result = Redirected("qkq")),
                Pipeline.Rule(condition = More(M, 2090), result = Accepted),
                Pipeline.Rule(condition = Always, result = Redirected("rfg")),
            )
        )

        val actual = Pipeline.parse(input)

        assertEquals(expected, actual)
    }
}