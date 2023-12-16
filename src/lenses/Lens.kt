package lenses

data class Lens(val label: String) {
    var focalLength: Int = 0

    override fun hashCode(): Int = label.fold(0) { acc, char -> (acc + char.code) * 17 % 256 }

    override fun equals(other: Any?): Boolean = (other as? Lens)?.label == label
}