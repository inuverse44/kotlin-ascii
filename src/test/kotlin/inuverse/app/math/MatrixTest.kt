package inuverse.app.math

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertContentEquals

class MatrixTest {
    @Test
    fun getMatrixTest() {
        // Arrange
        val data = doubleArrayOf(
            1.0, 2.0, 3.0,
            4.0, 5.0, 6.0,
            7.0, 8.0, 9.0
        )
        val matrix = Matrix(3, 3, data)

        // Act
        val actualRows = matrix.rows
        val expectedRows = 3

        val actualCols = matrix.cols
        val expectedCols = 3

        val actualData = matrix.data
        val expectedData = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)

        // Assert
        assertEquals(expectedRows, actualRows)
        assertEquals(expectedCols, actualCols)
        assertContentEquals(expectedData, actualData)
    }

    @Test
    fun applyMatrixToVectorTest() {
        // Arrange
        val data = doubleArrayOf(
            1.0, 2.0, 3.0,
            4.0, 5.0, 6.0,
            7.0, 8.0, 9.0
        )
        val matrix = Matrix(3, 3, data)

        val vector = Vector(doubleArrayOf(1.0, 2.0, 3.0))

        // Act
        val actual= matrix.apply(vector).data
        val expected = doubleArrayOf(14.0, 32.0, 50.0)

        // Assert
        assertContentEquals(expected, actual)
    }


    @Test
    fun getMatrixComponentTest() {
        // Arrange
        val data = doubleArrayOf(
            1.0, 2.0, 3.0,
            4.0, 5.0, 6.0,
            7.0, 8.0, 9.0
        )
        val matrix = Matrix(3, 3, data)

        // Act
        val expectedComponent00 = 1.0
        val actualComponent00 = matrix[0, 0]

        val expectedComponent12 = 6.0
        val actualComponent12 = matrix[1, 2]

        val expectedComponent20 = 7.0
        val actualComponent20 = matrix[2, 0]

        // Assert
        assertEquals(expectedComponent00, actualComponent00)
        assertEquals(expectedComponent12, actualComponent12)
        assertEquals(expectedComponent20, actualComponent20)
    }

    @Test
    fun sumMatrixTest() {
        // Arrange
        val data1 = doubleArrayOf(
            1.0, 2.0, 3.0,
            4.0, 5.0, 6.0
        )
        val matrix1 = Matrix(2, 3, data1)

        val data2 = doubleArrayOf(
            2.0, 4.0, 9.0,
            1.0, 1.0, 2.0
        )
        val matrix2 = Matrix(2, 3, data2)

        // Act
        val expected = doubleArrayOf(
            3.0, 6.0, 12.0,
            5.0, 6.0, 8.0
        )
        val actual = matrix1.sum(matrix2).data

        // Assert
        assertContentEquals(expected, actual)

    }

    @Test
    fun subMatrixTest() {
        // Arrange
        val data1 = doubleArrayOf(
            1.0, 2.0, 3.0,
            4.0, 5.0, 6.0
        )
        val matrix1 = Matrix(2, 3, data1)

        val data2 = doubleArrayOf(
            2.0, 4.0, 9.0,
            1.0, 1.0, 2.0
        )
        val matrix2 = Matrix(2, 3, data2)

        // Act
        val expected = doubleArrayOf(
            -1.0, -2.0, -6.0,
            3.0, 4.0, 4.0
        )
        val actual = matrix1.sub(matrix2).data

        // Assert
        assertContentEquals(expected, actual)

    }

    @Test
    fun mulMatrixTest() {
        // Arrange
        val data1 = doubleArrayOf(
            1.0, 2.0, 3.0,
            4.0, 3.0, 6.0
        )
        val matrix1 = Matrix(2, 3, data1)

        val data2 = doubleArrayOf(
            2.0, 4.0,
            1.0, 1.0,
            1.0, 2.0
        )
        val matrix2 = Matrix(3, 2, data2)

        // Act
        val expected = doubleArrayOf(
            7.0, 12.0,
            17.0, 31.0
        )
        val actual = matrix1.mul(matrix2).data

        // Assert
        assertContentEquals(expected, actual)

    }

    @Test
    fun testMatrixTransport() {
        // Arrange
        val data = doubleArrayOf(
            1.0, 2.0, 3.0,
            4.0, 3.0, 6.0
        )
        val matrix = Matrix(2, 3, data)

        // Act
        val expected = doubleArrayOf(
            1.0, 4.0,
            2.0, 3.0,
            3.0, 6.0
        )
        val actual = matrix.transport().data

        // Assert
        assertContentEquals(expected, actual)

    }


}