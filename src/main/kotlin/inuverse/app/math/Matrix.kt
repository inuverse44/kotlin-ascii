package inuverse.app.math

class Matrix(
    val rows: Int,
    val cols: Int,
    val data: DoubleArray
) {
    init {
        require(rows * cols == data.size) {
            "Error: Shape mismatch. rows: $rows, cols: $cols, rows*cols: ${rows * cols}, data.size: ${data.size}"
        }
    }

    /**
     * Get a matrix component
     *
     * e.g.,
     * val data = doubleArrayOf(1.0, 2.0, 3.0, 4.0)
     * val matrix = Matrix(2, 2, data)
     * println(matrix[0, 0]) // 1.0
     * println(matrix[1, 0]) // 3.0
     *
     * @param i: Int target row
     * @param j: Int target column
     * @return: Double matrix component
     */
    operator fun get(i: Int, j: Int): Double {
        return data[i * cols + j]
    }

    fun apply(other: Vector): Vector {
        require(this.cols == other.size) {
            "Error Shape mismatch. ${this.cols} and ${other.size} should be equal"
        }
        val result = DoubleArray(this.rows)
        for (r in 0 until this.rows) {
            var sum = 0.0
            for (i in 0 until other.size) {
                sum += this[r, i] * other[i]
            }
            result[r] = sum
        }
        return Vector(result)
    }

    /**
     * Sum two matrices
     * e.g.,
     * [1, 2]   [2, 5]   [3, 7]
     * [3, 4] + [7, 1] = [10, 5]
     *
     * @param other: Matrix
     * @return: Matrix
     */
    fun sum(other: Matrix): Matrix {
        require(this.rows == other.rows && this.cols == other.cols) {
            "Error Shape mismatch. this: ${this.rows} x ${this.cols}, but input matrix: ${other.rows} x ${other.cols}"
        }
        val result = DoubleArray(this.rows * this.cols)
        for (r in 0 until this.rows) {
            for (c in 0 until this.cols) {
                val index = r * this.cols + c
                result[index] = this[r, c] + other[r, c]
            }
        }
        return Matrix(this.rows, this.cols, result)
    }

    /**
     * Subtract two matrices
     *
     * e.g.,
     * [1, 2]   [2, 5]   [3, 7]
     * [3, 4] - [7, 1] = [10, 5]
     *
     * @param other: Matrix
     * @return: Matrix
     */
    fun sub(other: Matrix): Matrix {
        require(this.rows == other.rows && this.cols == other.cols) {
            "Error Shape mismatch. this: ${this.rows} x ${this.cols}, but input matrix: ${other.rows} x ${other.cols}"
        }
        val result = DoubleArray(this.rows * this.cols)
        for (r in 0 until this.rows) {
            for (c in 0 until this.cols) {
                val index = r * this.cols + c
                result[index] = this[r, c] - other[r, c]
            }
        }
        return Matrix(this.rows, this.cols, result)
    }

    /**
     *
     */
    fun mul(other: Matrix): Matrix {
        require(this.cols == other.rows) {
            "Error Shape mismatch. this.cols ${this.cols} and other.rows ${other.rows} should be equal"
        }
        val result = DoubleArray(this.rows * other.cols)
        for (r in 0 until this.rows) {
            for (c in 0 until other.cols) {
                val index = r * other.cols + c
                var sum = 0.0
                for (k in 0 until this.cols) {
                    sum += this[r, k] * other[k, c]
                }
                result[index] = sum
            }
        }
        return Matrix(this.rows, other.cols, result)
    }

    /**
     *
     */
    fun transport(): Matrix {
        val newCols = this.rows
        val newRows = this.cols
        val result = DoubleArray(newCols * newRows)
        for (r in 0 until newRows) {
            for (c in 0 until newCols) {
                val index = r * newCols + c
                result[index] = this[c, r]
            }
        }
        return Matrix(newCols, newRows, result)
    }


    /**
    * Represent all matrix components as standard output.
    *
    * e.g.,
    * [1, 0, 0]
    * [0, 1, 0]
    * [0, 0, 1]
    */
    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("\n")
        for (i in 0 until rows) {
            sb.append("[")
            for (j in 0 until cols) {
                sb.append(this[i, j])
                if (j < cols - 1) sb.append(", ")
            }
            sb.append("]\n")
        }
        return sb.toString()
    }


}