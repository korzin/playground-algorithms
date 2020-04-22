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
                String head = stack.top();
                String closingSymbol;
                if (head == null
                        || (closingSymbol = closeToOpenSymbolsMap.get(s)) == null
                        || !head.equals(closingSymbol)) {
                    stack.push(s);
                } else {
                    stack.pop();
                }
            }
        }
        return stack.top() == null;
    }
}
