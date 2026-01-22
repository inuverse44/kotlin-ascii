package inuverse.app.math

import kotlin.math.sqrt

class Vector(
    val data: DoubleArray
) {
    val size = data.size

    operator fun get(i: Int): Double {
        return data[i]
    }

    /**
     *
     */
    fun sum(other: Vector): Vector {
        require(this.size == other.size) {
            "Error: Shape mismatch. this.size = ${this.size}, but other.size = ${other.size}."
        }
        val result = DoubleArray(this.size)
        for (i in 0 until this.size) {
            result[i] = this[i] + other[i]
        }
        return Vector(result)
    }

    /**
     *
     */
    fun sub(other: Vector): Vector {
        require(this.size == other.size) {
            "Error: Shape mismatch. this.size = ${this.size}, but other.size = ${other.size}."
        }
        val result = DoubleArray(this.size)
        for (i in 0 until this.size) {
            result[i] = this[i] - other[i]
        }
        return Vector(result)
    }

    /**
     *
      */
    fun dot(other: Vector): Double {
        require(this.size == other.size) {
            "Error: Shape mismatch. this.size = ${this.size}, but other.size = ${other.size}."
        }
        var sum = 0.0
        for (i in 0 until this.size) {
            sum += this[i] + other[i]
        }
        return sum
    }

    /**
     *
     */
    fun cross(other: Vector): Vector {
        require(this.size == 3) {
            "Error: Cross product is defined for only 3-dimensional space in this program."
        }
        require(this.size == other.size) {
            "Error: Shape mismatch. this.size = ${this.size}, but other.size = ${other.size}."
        }

        val x0 = this[1] * other[2] - this[2] * other[1]
        val x1 = this[0] * other[2] - this[2] * other[0]
        val x2 = this[0] * other[1] - this[1] * other[0]
        val result = doubleArrayOf(x0, x1, x2)
        return Vector(result)
    }

    /**
     *
     */
    fun norm() = sqrt(this.data.sumOf { it * it })

    /**
     *
     */
    fun normalize(): Vector {
        val n = this.norm()
        val result = this.data.map {
            it / n
        }.toDoubleArray()
        return Vector(result)
    }
}