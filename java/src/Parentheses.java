import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rupalh on 1/28/18.
 */
public class Parentheses {

    public static boolean removeInvalid(String s, int count, int last, int index, StringBuffer sb) {
        System.out.println("Count: "+count+", last:"+last+", sb:"+sb.toString());

        boolean result = false;
        if (index >= s.length()) {
//            System.out.println(sb.toString());
            return count==0?true:false;
        }
        if ((index+1) < s.length() && s.charAt(index) == '(' && s.charAt(index + 1) == ')')
            result =  removeInvalid(s, count, count>0?last:(index+2), (index + 2), sb.append("()"));
        else if (s.charAt(index) == '(')
            result =  removeInvalid(s, ++count, last, ++index, sb.append(s.charAt(index-1)));
        else {
            if (!result && count > 0) { //backtrack
                System.out.println("Backtrack -- Count: " + count + ", last:" + last + ", sb:" + sb.toString());

                result = removeInvalid(s, 0, (last + count), (last + count), sb.delete(last, sb.length()));
            }
            else
                result = removeInvalid(s, --count, last, ++index, sb.append(s.charAt(index - 1)));
        }
        return result;
    }
    public static List<String> removeInvalidParentheses(String s) {
        int rmL = 0, rmR = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rmL++;
            } else if (s.charAt(i) == ')') {
                if (rmL != 0) {
                    rmL--;
                } else {
                    rmR++;
                }
            }
        }
        Set<String> res = new HashSet<>();
        dfs(s, 0, res, new StringBuilder(), rmL, rmR, 0);
        return new ArrayList<String>(res);
    }

    public static void dfs(String s, int i, Set<String> res, StringBuilder sb, int rmL, int rmR, int open) {
        System.out.println("i="+i+",rmL:"+rmL+",rmR:"+rmR+",open:"+open+",sb:"+sb.toString());
        if (rmL < 0 || rmR < 0 || open < 0) {
            return;
        }
        if (i == s.length()) {
            if (rmL == 0 && rmR == 0 && open == 0) {
                res.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(i);
        int len = sb.length();

        if (c == '(') {
            dfs(s, i + 1, res, sb, rmL - 1, rmR, open);		    // not use (
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open + 1);       // use (

        } else if (c == ')') {
            dfs(s, i + 1, res, sb, rmL, rmR - 1, open);	            // not use  )
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open - 1);  	    // use )

        } else {
            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open);
        }

        sb.setLength(len);
    }
    public static void main(String args[]){
        StringBuffer sb = new StringBuffer();
        List<?> ans = removeInvalidParentheses("()(((()())((()");
        System.out.println("Final ans ---- "+ans);

    }
}
