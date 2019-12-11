import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
       /* String i = "";
        String name = "()";
        String[] str = name.split("");
//        System.out.println("ddd");
        Map<String,String> charMap = new HashMap<String,String>();
        charMap.put(")","(");
        charMap.put("}","{");
        charMap.put("]","[");
        for (int i1 = 0; i1 < str.length; i1++) {
            System.out.println(charMap.get(str[i1]));
        }*/
        System.out.println(isValid("()"));
    }
    public static boolean isValid(String s) {
        Map<String,String> charMap = new HashMap<String,String>();
        charMap.put(")","(");
        charMap.put("}","{");
        charMap.put("]","[");
        if (s == null) {
            return false;
        }
        if (s.length() == 0 || s.trim().length() == 0) {
            return true;
        }

        Stack<String> stack = new Stack<>();
        String[] strs = s.split("");
        for(int i = strs.length -1; i >= 0; i--){

            if (!stack.isEmpty() ){
                if (("(".equals(strs[i]) && stack.peek().equals(")"))
                        || ("[".equals(strs[i]) && stack.peek().equals("]"))
                        || ("{".equals(strs[i]) && stack.peek().equals("}")))
                {
                    stack.pop();
                } else{
                    stack.push(strs[i]);
                }

            }else{
                stack.push(strs[i]);
            }
        }

        return stack.isEmpty();

    }
}
