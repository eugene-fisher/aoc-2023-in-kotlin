package reflections

class ReflectedImage(
    val width: Int,
    val height: Int,
) {
    private val pixels = BooleanArray(width * height)

    private var initializedPixels = 0

    fun initNext(marked: Boolean) {
        pixels[initializedPixels++] = marked
    }

    fun pixel(x: Int, y: Int): Boolean? = if (x in 0..<width && y in 0..<height) pixels[y * width + x] else null
}