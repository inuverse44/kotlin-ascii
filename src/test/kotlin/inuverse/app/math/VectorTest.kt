package inuverse.app.math

import org.junit.jupiter.api.Test
import kotlin.math.sqrt
import kotlin.math.sin
import kotlin.math.PI
import kotlin.test.assertEquals
import kotlin.test.assertContentEquals

class VectorTest {

    val eps = 1.0e-8

    @Test
    fun getVectorTest() {
        // Arrange
        val data = doubleArrayOf(1.0, 2.0, 3.0)
        val vector = Vector(data)

        // Act
        val actual0 = vector[0]
        val expected0 = 1.0

        val actual1 = vector[1]
        val expected1 = 2.0

        val actual2 = vector[2]
        val expected2 = 3.0

        // Assert
        assertEquals(actual0, expected0)
        assertEquals(actual1, expected1)
        assertEquals(actual2, expected2)
    }

    @Test
    fun sumVectorTest() {
        // Arrange
        val data1 = doubleArrayOf(1.0, 2.0, 3.0)
        val vector1 = Vector(data1)

        val data2 = doubleArrayOf(4.0, 2.0, 2.0)
        val vector2 = Vector(data2)

        // Act
        val expected = doubleArrayOf(5.0, 4.0, 5.0)
        val actual = vector1.sum(vector2).data

        // Assert
        assertContentEquals(expected, actual)
    }

    @Test
    fun subVectorTest() {
        // Arrange
        val data1 = doubleArrayOf(1.0, 2.0, 3.0)
        val vector1 = Vector(data1)

        val data2 = doubleArrayOf(4.0, 2.0, 2.0)
        val vector2 = Vector(data2)

        // Act
        val expected = doubleArrayOf(-3.0, 0.0, 1.0)
        val actual = vector1.sub(vector2).data

        // Assert
        assertContentEquals(expected, actual)
    }

    @Test
    fun dotVectorTest() {
        // Arrange
        val data1 = doubleArrayOf(1.0, 2.0, 3.0)
        val vector1 = Vector(data1)

        val data2 = doubleArrayOf(4.0, 2.0, 2.0)
        val vector2 = Vector(data2)

        // Act
        val expected = 14.0
        val actual = vector1.dot(vector2)

        // Assert
        assertEquals(expected, actual)
    }

    @Test
    fun crossVectorTest() {
        // Arrange
        val data1 = doubleArrayOf(1.0, 2.0, 3.0)
        val vector1 = Vector(data1)

        val data2 = doubleArrayOf(4.0, 2.0, 2.0)
        val vector2 = Vector(data2)

        // Act
        val dataExpected = doubleArrayOf(-2.0, -10.0, -6.0)
        val expected = Vector(dataExpected).data
        val actual = vector1.cross(vector2).data

        // Assert
        assertContentEquals(expected, actual)
    }

    @Test
    fun crossVectorAbsoluteValueTest() {
        // Arrange
        val data1 = doubleArrayOf(2.0, 0.0, 0.0)
        val vector1 = Vector(data1)

        val data2 = doubleArrayOf(3.0, 3.0, 0.0)
        val vector2 = Vector(data2)

        // Act
        /**
         * v = v1 x v2 = [0.0, 0.0, 6.0]
         * |v| = sqrt(6.0)
         */
        val vector1Norm = vector1.norm()
        val vector2Norm = vector2.norm()
        val expected = vector1Norm * vector2Norm * sin(PI/4.0)
        val actual = vector1.cross(vector2).norm()

        // Assert
        assertEquals(expected, actual, eps)
    }

    @Test
    fun normVectorTest() {
        // Arrange
        val data = doubleArrayOf(1.0, 2.0, 3.0)
        val vector = Vector(data)

        // Act
        val expected = sqrt(14.0)
        val actual = vector.norm()

        // Assert
        assertEquals(expected, actual, eps)
    }

    @Test
    fun normalizeVectorTest() {
        // Arrange
        val data = doubleArrayOf(1.0, 2.0, 3.0)
        val vector = Vector(data)

        // Act
        val expectedData = doubleArrayOf(
            1.0 / sqrt(14.0),
            2.0 / sqrt(14.0),
            3.0 / sqrt(14.0)
        )
        val expected = Vector(expectedData).data
        val actual = vector.normalize().data

        // Assert
        expected.zip(actual).forEach { (e, a) ->
            assertEquals(a, e, eps)
        }
    }



}