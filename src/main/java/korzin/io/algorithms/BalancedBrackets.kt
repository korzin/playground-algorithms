package korzin.io.algorithms

import korzin.io.structures.Stack

class BalancedBrackets {

    private val bracketsOpenCloseMap: Map<String, String> = hashMapOf(")" to "(", "]" to "[")
    private val bracketsSet: Set<String> = setOf("(", ")", "[", "]")

    fun isBracketBalanced(input: String): Boolean {
        val inputLetters: List<String> = input.split("")
        val procStack = Stack<String>()
        for (letter: String in inputLetters) {
            if (bracketsSet.contains(letter)) {
                if (bracketsOpenCloseMap.containsKey(letter)) {
                    if (procStack.top() == null || procStack.pop() != bracketsOpenCloseMap[letter]) {
                        return false
                    }
                } else {
                    procStack.push(letter)
                }
            }
        }
        return procStack.top() == null;
    }
}
