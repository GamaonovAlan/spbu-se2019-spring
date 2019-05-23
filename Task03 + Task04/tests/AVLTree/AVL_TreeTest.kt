package tests.AVLTree

import AVLTree.AVLTree
import AVLTree.Node
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Tests for AVL tree")
internal class TestAVLTree {

    private val tree = AVLTree<Int, Int>()

    private fun checkStructure(node: Node<Int, Int>? = tree.root): Boolean {

        if (node == null) return true

        return if (node.getChildrenHeightDifference() in -1..1) {
            val checkLeft = checkStructure(node.leftChild)
            val checkRight = checkStructure(node.rightChild)
            checkLeft && checkRight
        } else {
            false
        }

    }

    @DisplayName("Search existing key")
    @Test
    fun searching_existing_key_succes_asserted() {

        for (testInputLength in 0..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            testInput.shuffle()

            for (data in testInput) {
                tree.insert(data, data)
            }

            for (data in testInput) {
                assertEquals(Pair(data, data), tree.find(data))
            }

            tree.root?.leftChild?.parent = null
            tree.root?.rightChild?.parent = null
            tree.root = null

        }

    }

    @DisplayName("Search in root")
    @Test
    fun searching_root_success_asserted() {

        tree.insert(1, 1)

        assertEquals(Pair(1, 1), tree.find(1))

    }

    @DisplayName("Search multiple")
    @Test
    fun searching_different_nodes_success_asserted() {

        tree.insert(1, 1)
        tree.insert(2, 2)

        assertEquals(Pair(2, 2), tree.find(2))
        assertEquals(Pair(1, 1), tree.find(1))

    }

    @DisplayName("Search nonexistent key in empty tree")
    @Test
    fun searching_not_existing_key_in_empty_tree_failure_asserted() {

        assertNull(tree.find(0))
        assertNull(tree.find(101))

    }

    @DisplayName("Search non existing key in nonempty tree")
    @Test
    fun searching_not_existing_key_in_not_empty_tree_failure_asserted() {

        val testInput: MutableList<Int> = MutableList(100) { it + 1 }

        testInput.shuffle()

        for (data in testInput) {
            tree.insert(data, data)
        }

        assertNull(tree.find(0))
        assertNull(tree.find(101))

    }

    @DisplayName("Double root insert check")
    @Test
    fun multiple_insert_to_root_success_asserted() {

        tree.insert(1, 1)
        tree.insert(1, 2)

        assertEquals(tree.root!!.key, 1)
        assertEquals(tree.root!!.value, 2)
        assertTrue(tree.root!!.parent == null)
        assertTrue(tree.root!!.leftChild == null)
        assertTrue(tree.root!!.rightChild == null)

    }

    @DisplayName("Insert check")
    @Test
    fun insert_success_asserted() {

        for (testInputLength in 0..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            testInput.shuffle()

            for (data in testInput) {
                tree.insert(data, data)
            }

            for (x in testInput) {
                assertEquals(tree.find(x), Pair(x, x))
            }

            tree.root?.leftChild?.parent = null
            tree.root?.rightChild?.parent = null
            tree.root = null

        }

    }

    @DisplayName("Insert save structure")
    @Test
    fun insert_save_structure_success_asserted() {

        for (testInputLength in 0..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            testInput.shuffle()

            for (data in testInput) {
                tree.insert(data, data)
                assertTrue(checkStructure())
            }

            tree.root?.leftChild?.parent = null
            tree.root?.rightChild?.parent = null
            tree.root = null

        }

    }

    @DisplayName("Insert save structure Direct order")
    @Test
    fun direct_order_insert_save_structure_success_asserted() {

        for (testInputLength in 0..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            for (data in testInput) {
                tree.insert(data, data)
                assertTrue(checkStructure())
            }

            tree.root?.leftChild?.parent = null
            tree.root?.rightChild?.parent = null
            tree.root = null

        }

    }

    @DisplayName("Insert save structure Reverse order")
    @Test
    fun reverse_order_insert_save_structure_success_asserted() {

        for (testInputLength in 0..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { testInputLength - it }

            for (data in testInput) {
                tree.insert(data, data)
                assertTrue(checkStructure())
            }

            tree.root?.leftChild?.parent = null
            tree.root?.rightChild?.parent = null
            tree.root = null

        }

    }

    @DisplayName("Iterate empty tree")
    @Test
    fun empty_tree_iteration_success_asserted() {

        for (i in tree)
            assertEquals(-1, 1)

    }

    @DisplayName("Iterate tree")
    @Test
    fun tree_iteration_succes_asserted() {

        for (testInputLength in 1..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            testInput.shuffle()

            for (data in testInput) {
                tree.insert(data, data)
            }

            var cur = 0

            for (i in tree) {
                ++cur
                assertEquals(tree.find(i.key), Pair(i.key, i.value))
            }

            tree.root?.leftChild?.parent = null
            tree.root?.rightChild?.parent = null
            tree.root = null

        }

    }

    @DisplayName("Iterate tree in direct order")
    @Test
    fun tree_iteration_in_direct_order_success_asserted() {

        for (testInputLength in 1..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            for (data in testInput) {
                tree.insert(data, data)
            }

            var cur = 0

            for (i in tree) {
                ++cur
                assertEquals(tree.find(i.key), Pair(i.key, i.value))
            }

            tree.root?.leftChild?.parent = null
            tree.root?.rightChild?.parent = null
            tree.root = null

        }

    }

    @DisplayName("Iterate tree in reverse order")
    @Test
    fun tree_itearation_in_reverse_order_success_asserted() {

        for (testInputLength in 1..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { testInputLength - it }

            for (data in testInput) {
                tree.insert(data, data)
            }

            var cur = 0

            for (i in tree) {
                ++cur
                assertEquals(tree.find(i.key), Pair(i.key, i.value))
            }

            tree.root?.leftChild?.parent = null
            tree.root?.rightChild?.parent = null
            tree.root = null

        }

    }

}