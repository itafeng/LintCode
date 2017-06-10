 /**

Given an expression s includes numbers, letters and brackets. Number represents the number of repetitions inside the brackets(can be a string or another expression)．Please expand expression to be a string.

Example
s = abc3[a] return abcaaa
s = 3[abc] return abcabcabc
s = 4[ac]dy, return acacacacdy
s = 3[2[ad]3[pf]]xyz, return adadpfpfpfadadpfpfpfadadpfpfpfxyz

**/

public class Solution {
    /**
     * @param s  an expression includes numbers, letters and brackets
     * @return a string
     */
    public String expressionExpand(String s) {
        // Write your code here
        
        Stack<Object> stk = new Stack<>();
        
        int reps = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                reps = reps * 10 + c - '0';
            }
            else if (c == '[') {
                stk.push(Integer.valueOf(reps));
                reps = 0;
            }
            else if (c == ']') {
                
                String str = popHelper(stk);
                int count = (int)stk.pop();
                for (int i = 0; i < count; ++i) {
                    stk.push((String)str);
                }
            }
            else {
                stk.push(String.valueOf(c));
            }
        }
        
        return popHelper(stk);        
    }
    
    private String popHelper(Stack<Object> stk) {
        StringBuilder sb = new StringBuilder();
        Stack<String> travStk = new Stack<>();
        
        while (!stk.isEmpty() && stk.peek() instanceof String) {
            travStk.push((String)stk.pop());
        }
        
        while (!travStk.isEmpty()) {
            sb.append(travStk.pop());
        }
        
        return sb.toString();
    }
}


/**
	Note:
	
	Use stack to store expression elements:
	1. When current token is '[', push reps to stack
	2. When current token is ']', pop String type items and reps off stack and push the result back to stack
	3. At the end of program, pop everything off stack
	
	Use generic type (Object) as Stack element type to fit String and Integer types.

**/