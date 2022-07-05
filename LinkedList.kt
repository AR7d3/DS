package dataStructures

class LinkedList<E>: Sequence<E> {
    private data class Node<T>(var item: T, var prev: Node<T>? = null, var next: Node<T>? = null) {
        override fun toString(): String {
            return if (next != null) {
                "$item <-> ${next.toString()}"
            } else {
                "$item"
            }
        }
    }

    private fun getNode(i: Int): Node<E> {
        tailrec fun laterNode(currentNode: Node<E>?, i: Int): Node<E> {
            if (currentNode == null) {
                throw NoSuchElementException()
            }
            if (i == 0) {
                return currentNode
            }
            return laterNode(currentNode.next, i - 1)
        }

        tailrec fun earlierNode(currentNode: Node<E>?, i: Int): Node<E> {
            if (currentNode == null) {
                throw NoSuchElementException()
            }
            if (i == 0) {
                return currentNode
            }
            return earlierNode(currentNode.prev, i - 1)
        }

        if (i < 0 || i >= size) {
            throw IndexOutOfBoundsException()
        }

        return if (size - 1 - i < i) {
            earlierNode(tail, size - 1 - i)
        } else {
            laterNode(head, i)
        }
    }


    private var head: Node<E>? = null
    private var tail: Node<E>? = null
    private var size: Int = 0

    override fun toString(): String {
        return if (size == 0) {
            ""
        } else {
            head.toString()
        }
    }

    override fun length(): Int {
        return size
    }

    override fun get(i: Int): E {
        return getNode(i).item
    }

    override fun set(i: Int, x: E) {
        val node: Node<E> = getNode(i)
        node.item = x
    }

    override fun insertFirst(x: E) {
        val newNode: Node<E> = Node(x)
        newNode.next = head

        head ?. apply {
            this.prev = newNode
        }

        head = newNode
        if (size == 0) {
            tail = head
        }
        size++
    }

    override fun deleteFirst() {
        if (size == 0) {
            throw NoSuchElementException()
        }
        head = head?.next
        head?.prev = null
        size--
    }

    override fun insertLast(x: E) {
        val newNode: Node<E> = Node(x)
        newNode.prev = tail

        tail ?. apply {
            this.next = newNode
        }

        tail = newNode
        if (size == 0) {
            head = tail
        }
        size++
    }

    override fun deleteLast() {
        if (size == 0) {
            throw NoSuchElementException()
        }
        tail = tail?.prev
        tail?.next = null
        size--
    }

    override fun insertAt(i: Int, x: E) {
        if (i == 0) {
            return insertFirst(x)
        } else if (i == size - 1) {
            return insertLast(x)
        }

        val newNode: Node<E> = Node(x)
        val prevNode: Node<E> = getNode(i - 1)

        newNode.next = prevNode.next
        prevNode.next?.prev = newNode
        newNode.prev = prevNode
        prevNode.next = newNode

        size++
    }

    override fun deleteAt(i: Int) {
        if (i == 0) {
            return deleteFirst()
        } else if (i == size - 1) {
            return deleteLast()
        }

        val prevNode: Node<E> = getNode(i - 1)
        prevNode.next = prevNode.next?.next
        prevNode.next?.prev = prevNode
        size--
    }
}