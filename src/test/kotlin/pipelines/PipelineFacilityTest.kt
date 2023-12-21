package pipelines

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import pipelines.Pipeline.Result.*
import pipelines.Pipeline.Rule
import pipelines.Pipeline.Rule.Condition.*
import pipelines.PipelinePart.Feature.M
import pipelines.PipelinePart.Feature.S

class PipelineFacilityTest {

    @Test
    fun f() {
        val sut = PipelineFacility(listOf(
            Pipeline("qs", rules = listOf(Rule(More(S, 3448), Accepted), Rule(Always, Redirected("lnx")))),
            Pipeline("lnx", rules = listOf(Rule(More(M, 1548), Accepted), Rule(Always, Accepted))),
            Pipeline("qqz", rules = listOf(Rule(More(S, 2770), Redirected("qs")), Rule(Less(M, 1801), Redirected("hdj")), Rule(Always, Rejected))),
            Pipeline("in", rules = listOf(Rule(Less(S, 1351), Redirected("px")), Rule(Always, Redirected("qqz")))),
        ))

        val part = PipelinePart(x = 787, m = 2655, a = 1222, s = 2876)

        sut.embark(part)

        assertEquals(listOf(part), sut.accepted)
        assertEquals(emptyList<PipelinePart>(), sut.rejected)
    }
}