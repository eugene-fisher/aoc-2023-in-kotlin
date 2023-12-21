package pipelines

data class Pipeline(
    val name: String,
    val rules: List<Rule>,
) {

    fun process(part: PipelinePart): Result {
        return rules.first { it.isApplicable(part) }.result
    }

    private fun Rule.isApplicable(part: PipelinePart): Boolean = when (condition) {
        is Rule.Condition.Always -> true
        is Rule.Condition.Less -> part.feature(condition.feature) < condition.value
        is Rule.Condition.More -> part.feature(condition.feature) > condition.value
    }

    sealed class Result {
        data object Accepted : Result()

        data object Rejected : Result()

        data class Redirected(val to: String) : Result()
    }

    data class Rule(val condition: Condition, val result: Result) {
        sealed class Condition {
            data object Always : Condition()
            data class More(val feature: PipelinePart.Feature, val value: Int) : Condition()
            data class Less(val feature: PipelinePart.Feature, val value: Int) : Condition()
        }
    }

    companion object {

        fun parse(line: String) = Pipeline(
            name = line.substringBefore('{'),
            rules = line.substring(line.indexOf('{') + 1, line.lastIndexOf('}')).split(',').map { parseRule(it) }
        )

        private fun parseRule(rule: String): Rule {
            val ruleParts = rule.split(':')
            return when (ruleParts.size) {
                2 -> Rule(condition = parseRuleCondition(ruleParts[0]), result = parseRuleDestination(ruleParts[1]))
                1 -> Rule(condition = Rule.Condition.Always, result = parseRuleDestination(ruleParts[0]))
                else -> throw IllegalArgumentException("Can't parse rule $rule")
            }
        }

        private fun parseRuleCondition(condition: String): Rule.Condition {
            val partsLess = condition.split('<')
            val partsMore = condition.split('>')
            return when {
                partsLess.size == 2 -> Rule.Condition.Less(feature(partsLess[0]), value = partsLess[1].toInt())
                partsMore.size == 2 -> Rule.Condition.More(feature(partsMore[0]), value = partsMore[1].toInt())
                else -> throw IllegalArgumentException("Cant parse condition $condition")
            }
        }

        private fun feature(s: String): PipelinePart.Feature = when (s) {
            "x" -> PipelinePart.Feature.X
            "m" -> PipelinePart.Feature.M
            "a" -> PipelinePart.Feature.A
            "s" -> PipelinePart.Feature.S
            else -> throw IllegalArgumentException("Unknown feature $s")
        }

        private fun parseRuleDestination(to: String): Result = when (to) {
            "A" -> Result.Accepted
            "R" -> Result.Rejected
            else -> Result.Redirected(to)
        }
    }
}