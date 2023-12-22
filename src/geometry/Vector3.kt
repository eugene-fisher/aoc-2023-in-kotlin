package geometry

data class Vector3(
    val x: Int,
    val y: Int,
    val z: Int,
) {
    fun translate(dx: Int, dy: Int, dz: Int) = Vector3(x = x + dx, y = y + dy, z = z + dz)
}
