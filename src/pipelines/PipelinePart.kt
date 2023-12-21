package pipelines

data class PipelinePart(val x: Int, val m: Int, val a: Int, val s: Int) {
    fun feature(feature: Feature): Int = when (feature) {
        Feature.X -> x
        Feature.M -> m
        Feature.A -> a
        Feature.S -> s
    }

    enum class Feature {
        X, M, A, S,
    }

    companion object {
        private val regex = Regex("\\{x=([0-9]+),m=([0-9]+),a=([0-9]+),s=([0-9]+)}")

        fun parse(line: String): PipelinePart = requireNotNull(regex.find(line)?.groupValues).let {
            PipelinePart(x = it[1].toInt(), m = it[2].toInt(), a = it[3].toInt(), s = it[4].toInt())
        }
    }
}