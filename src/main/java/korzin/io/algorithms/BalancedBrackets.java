package korzin.io.algorithms;

import korzin.io.structures.Stack;

import java.util.*;

public class BalancedBrackets {

    private Map<String, String> closeToOpenSymbolsMap = new HashMap<String, String>() {{
        put(")", "(");
        put("]", "[");
    }};

    private Set<String> symbolsToHandle = new HashSet<>(Arrays.asList("(", ")", "[", "]"));

    public boolean hasBalancedBrackets(String strToCheck) {
        String[] sequence = strToCheck.split("");
        Stack<String> stack = new Stack<>();
        for (String s : sequence) {
            if (symbolsToHandle.contains(s)) {
                if (closeToOpenSymbolsMap.containsKey(s)) {
                    if (stack.top() == null) {
                        return false;
                    }
                    String head = stack.pop();
                    String closingSymbol = closeToOpenSymbolsMap.get(s);
                    if (!head.equals(closingSymbol)) {
                        return false;
                    }
                } else {
                    stack.push(s);
                }
            }
        }
        return stack.top() == null;
    }
}
