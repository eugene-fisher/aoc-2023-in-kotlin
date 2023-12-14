package reflections

import java.lang.IllegalArgumentException

class ReflectedImageParser {

    private val parsed = mutableListOf<ReflectedImage>()

    private var renderingImage = mutableListOf<String>()

    val images: List<ReflectedImage> get() = parsed

    fun addLine(line: String) {
        if (line.isBlank()) {
            renderImage()
        } else {
            renderingImage.add(line)
        }
    }

    fun finish() = renderImage()

    private fun renderImage() {
        parsed.add(ReflectedImage(width = renderingImage.first().length, height = renderingImage.size).apply {
            renderingImage.forEach { line ->
                line.forEach {
                    initNext(
                        marked = when (it) {
                            '.' -> false
                            '#' -> true
                            else -> throw IllegalArgumentException("Cant parse symbol $it")
                        }
                    )
                }
            }
        })
        renderingImage.clear()
    }
}