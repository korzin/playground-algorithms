package korzin.io.algorithms

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BalancedBracketsTest {

    @Test
    fun isBracketBalanced() {

        val algorithm = BalancedBrackets()
        assertTrue(algorithm.isBracketBalanced("([sc])s/ugf43&c[sd 1+565]()sdcsc"))
        assertTrue(algorithm.isBracketBalanced("((([scsccs([6576887564])354dscsdscs]sdc))sdc())"))
        assertFalse(algorithm.isBracketBalanced("(4356[]]()"))
        assertFalse(algorithm.isBracketBalanced("]["))
    }

}