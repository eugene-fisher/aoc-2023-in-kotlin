package reflections

class ReflectionProcessor {

    fun findReflectionColumn(image: ReflectedImage): Int? {
        val reflections: Array<MutableSet<Int>> = Array(image.height) { mutableSetOf() }

        (0..<image.height).forEach { rowNumber ->
            (1..<image.width).forEach { index ->
                if (image.checkHorizontalReflection(index, rowNumber)) {
                    reflections[rowNumber].add(index)
                }
            }
        }

        return (reflections as Array<Set<Int>>).reduce { acc, columns -> acc.intersect(columns) }.singleOrNull()
    }

    private fun ReflectedImage.checkHorizontalReflection(index: Int, rowNumber: Int): Boolean {
        (0..index).forEach { offset ->
            val previousPixel = pixel(x = index - 1 - offset, y = rowNumber)
            val currentPixel = pixel(x = index + offset, y = rowNumber)
            if (currentPixel == null || previousPixel == null) {
                return true
            }
            if (currentPixel != previousPixel) {
                return false
            }
        }
        return true
    }

    fun findReflectionRow(image: ReflectedImage): Int? {
        val reflections: Array<MutableSet<Int>> = Array(image.width) { mutableSetOf() }

        (0..<image.width).forEach { columnNumber ->
            (1..<image.height).forEach { index ->
                if (image.checkVerticalReflection(index, columnNumber)) {
                    reflections[columnNumber].add(index)
                }
            }
        }


        return (reflections as Array<Set<Int>>).reduce { acc, columns -> acc.intersect(columns) }.singleOrNull()
    }

    private fun ReflectedImage.checkVerticalReflection(index: Int, columnNumber: Int): Boolean {
        (0..index).forEach { offset ->
            val previousPixel = pixel(x = columnNumber, y = index - 1 - offset)
            val currentPixel = pixel(x = columnNumber, y = index + offset)
            if (currentPixel == null || previousPixel == null) {
                return true
            }
            if (currentPixel != previousPixel) {
                return false
            }
        }
        return true
    }
}