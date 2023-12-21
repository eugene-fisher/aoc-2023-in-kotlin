package pipelines

import java.util.*

class PipelineFacility(
    private val pipelines: List<Pipeline>
) {

    private val pipelinesMap: Map<String, Pipeline> =
        pipelines.groupingBy { it.name }.aggregate { _, _, element, _ -> element }

    val accepted = mutableListOf<PipelinePart>()
    val rejected = mutableListOf<PipelinePart>()

    fun embark(part: PipelinePart) {
        processResult(Pipeline.Result.Redirected(to = "in"), part)
    }

    fun allPossible() {
        pipelines.forEach { pipeline -> allPossible(pipeline) }
    }

    private fun allPossible(pipeline: Pipeline): Range {
        return pipeline.rules.foldRight(Range.EMPTY) { rule, acc ->
            when (rule.condition) {
                Pipeline.Rule.Condition.Always -> when (rule.result) {
                    is Pipeline.Result.Accepted -> acc.apply {
                        accepted[PipelinePart.Feature.X] = SparseRange(1..4000)
                        accepted[PipelinePart.Feature.M] = SparseRange(1..4000)
                        accepted[PipelinePart.Feature.A] = SparseRange(1..4000)
                        accepted[PipelinePart.Feature.S] = SparseRange(1..4000)
                    }
                    is Pipeline.Result.Rejected -> acc.apply {
                        accepted[PipelinePart.Feature.X] = SparseRange()
                        accepted[PipelinePart.Feature.M] = SparseRange()
                        accepted[PipelinePart.Feature.A] = SparseRange()
                        accepted[PipelinePart.Feature.S] = SparseRange()
                    }
                    is Pipeline.Result.Redirected -> allPossible(pipelinesMap[rule.result.to]!!)
                }

                is Pipeline.Rule.Condition.More -> when (rule.result) {
                    is Pipeline.Result.Accepted -> acc.apply {
                        accepted[rule.condition.feature]!!.add(rule.condition.value.inc()..4000)
                    }
                    is Pipeline.Result.Rejected -> acc.apply {
                        accepted[rule.condition.feature]!!.atMost(rule.condition.value)
                    }
                    is Pipeline.Result.Redirected -> acc.apply {
                        val redirected = allPossible(pipelinesMap[rule.result.to]!!)
                        applyAllFrom(redirected, rule.condition.value)
                    }
                }

                is Pipeline.Rule.Condition.Less -> when (rule.result) {
                    else -> TODO()
//                    is Pipeline.Result.Accepted -> acc.apply {
//                        accepted[rule.condition.feature] = 1..rule.condition.value
//                    }
//                    is Pipeline.Result.Rejected -> acc.apply {
//                        accepted[rule.condition.feature] = (rule.condition.value + 1)..acc.accepted[rule.condition.feature]!!.endInclusive
//                    }
//                    is Pipeline.Result.Redirected -> TODO()
                }
            }
        }
    }

    private data class Range(val accepted: MutableMap<PipelinePart.Feature, SparseRange>) {

        fun applyAllFrom(range: Range, from: Int) {
            for (feature in PipelinePart.Feature.entries) {
                accepted[PipelinePart.Feature.X]!!.apply {
                    atMost(from)
                    range.accepted[PipelinePart.Feature.X]!!.apply { from(from + 1) }.range.forEach { add(it) }
                }
            }
        }

        companion object {
            val EMPTY = Range(EnumMap(PipelinePart.Feature::class.java)).apply {
                accepted[PipelinePart.Feature.X] = SparseRange()
                accepted[PipelinePart.Feature.M] = SparseRange()
                accepted[PipelinePart.Feature.A] = SparseRange()
                accepted[PipelinePart.Feature.S] = SparseRange()
            }
        }
    }

    private fun processResult(result: Pipeline.Result, part: PipelinePart) {
        when (result) {
            is Pipeline.Result.Redirected -> processRedirect(result, part)
            is Pipeline.Result.Accepted -> accepted.add(part)
            is Pipeline.Result.Rejected -> rejected.add(part)
        }
    }

    private fun processRedirect(redirect: Pipeline.Result.Redirected, part: PipelinePart) {
        val pipeline = requireNotNull(pipelinesMap[redirect.to])
        processResult(pipeline.process(part), part)
    }
}