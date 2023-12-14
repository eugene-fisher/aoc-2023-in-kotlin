package reflections

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReflectedImageTest {

    @Test
    fun `given image(00,11,00) when pixel(1, 1) then 1 returned`() {
        val input = ReflectedImage(width = 2, height = 3).apply {
            initNext(false)
            initNext(false)

            initNext(true)
            initNext(true)

            initNext(false)
            initNext(false)
        }
        val expected = true

        val actual = input.pixel(1, 1)

        assertEquals(expected, actual)
    }
}