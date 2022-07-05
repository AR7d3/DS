package dataStructures

interface Sequence<E> {
    // Container operations

    /**
     * Returns the number of stored items.
     */
    fun length(): Int

    // Static operations

    /**
     * Return the `i`th item.
     */
    fun get(i: Int): E

    /**
     * Replace the `i`th item with `x`.
     */
    fun set(i: Int, x: E)

    // Dynamic operations

    /**
     * Add `x` as the first item
     */
    fun insertFirst(x: E)

    /**
     * Remove and return the first item
     */
    fun deleteFirst()

    /**
     * Add `x` as the last item
     */
    fun insertLast(x: E)

    /**
     * Remove and return the last item
     */
    fun deleteLast()

    /**
     * Add `x` as the `i`th item
     */
    fun insertAt(i: Int, x: E)

    /**
     * Remove and return the `i`th item
     */
    fun deleteAt(i: Int)
}