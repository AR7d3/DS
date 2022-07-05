package dataStructures

@Suppress("UNCHECKED_CAST", "PrivatePropertyName")
class DynamicArray<E>: Sequence<E> {
    private val DEFAULT_CAPACITY = 4
    private var capacity = DEFAULT_CAPACITY
    private var data: Array<E?> = arrayOfNulls<Any?>(capacity) as Array<E?>
    private var size: Int = 0

    override fun toString(): String {
        return if (size == 0) {
            "[]"
        } else {
            var ret: String = "[ "
            for (i in 0 until size) {
                ret += data[i]
                ret += " "
            }
            ret += "]"
            ret
        }
    }

    private fun resize(newSize: Int) {
        fun transformData(new_capacity: Int) {
            val newData = arrayOfNulls<Any?>(capacity) as Array<E?>
            for (i in 0 until size) {
                newData[i] = data[i]
            }

            data = newData
        }

        if (newSize > capacity) {
            capacity *= 2
            transformData(capacity)
        }
        else if (newSize < capacity / 4) {
            capacity /= 2
            transformData(capacity)
        }
    }

    override fun length(): Int {
        return size
    }

    override fun get(i: Int): E {
        return try {
            data[i] ?: throw IndexOutOfBoundsException()
        } catch (i: java.lang.IndexOutOfBoundsException) {
            throw IndexOutOfBoundsException()
        }
    }

    override fun set(i: Int, x: E) {
        if (i < 0 || i >= size) {
            throw IndexOutOfBoundsException()
        }
        data[i] = x
    }

    override fun insertAt(i: Int, x: E) {
        if (i > size || i < 0) {
            throw IndexOutOfBoundsException()
        }
        resize(size + 1)

        tailrec fun shift(j: Int) {
            if (j == i) return
            data[j] = data[j - 1]
            shift(j - 1)
        }
        shift(size)

        data[i] = x
        size++
    }

    override fun deleteAt(i: Int) {
        if (i >= size || i < 0) {
            throw IndexOutOfBoundsException()
        }
        resize(size - 1)

        tailrec fun shift(j: Int) {
            if (j == size - 1) return

            data[j] = data[j + 1]
            shift(j + 1)
        }
        shift(i)

        size--
    }

    override fun deleteFirst() {
        deleteAt(0)
    }

    override fun deleteLast() {
        deleteAt(size - 1)
    }

    override fun insertLast(x: E) {
        insertAt(size, x)
    }

    override fun insertFirst(x: E) {
        insertAt(0, x)
    }
}