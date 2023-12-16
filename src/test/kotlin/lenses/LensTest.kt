package lenses

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LensTest {

    @Test
    fun `given rn=1 when hash() then 30 returned`() {
        val input = "rn=1"
        val expected = 30

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given cm- when hash() then 253 returned`() {
        val input = "cm-"
        val expected = 253

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given qp=3 when hash() then 97 returned`() {
        val input = "qp=3"
        val expected = 97

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given cm=2 when hash() then 47 returned`() {
        val input = "cm=2"
        val expected = 47

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given qp- when hash() then 14 returned`() {
        val input = "qp-"
        val expected = 14

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given pc=4 when hash() then 180 returned`() {
        val input = "pc=4"
        val expected = 180

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given ot=9 when hash() then 9 returned`() {
        val input = "ot=9"
        val expected = 9

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given ab=5 when hash() then 197 returned`() {
        val input = "ab=5"
        val expected = 197

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given pc- when hash() then 48 returned`() {
        val input = "pc-"
        val expected = 48

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given pc=6 when hash() then 214 returned`() {
        val input = "pc=6"
        val expected = 214

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given ot=7 when hash() then 231 returned`() {
        val input = "ot=7"
        val expected = 231

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given rn when hash() then 0 returned`() {
        val input = "rn"
        val expected = 0

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given cm when hash() then 0 returned`() {
        val input = "cm"
        val expected = 0

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }

    @Test
    fun `given qp when hash() then 1 returned`() {
        val input = "qp"
        val expected = 1

        val actual = Lens(input).hashCode()

        assertEquals(expected, actual)
    }
}