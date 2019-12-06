import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by rupalph on 8/25/19.
 */
public class Brackets {
    /*
    * Complete the function below.
    */
    static String[] find_all_well_formed_brackets(int n) {
        Set<String> set = new HashSet<>();
         set = helper(n, set);
         return set.toArray(new String[0]);
    }

    private static Set<String> helper(int n, Set<String> set) {
        if(n==0) return set;
        if(n==1) {
            set.add("()");
            return set;
        }
        if(n==2) {
            set.add("()()");
            set.add("(())");
        }
        Set<String> set1 = helper(n-1, set);
        Set<String> set2 = helper(n-2, set);

        Set<String> newset = new HashSet<>();
        for(String b:set1){
            newset.add(b+"()");
            newset.add("()"+b);
            newset.add("("+b+")");
        }
        Set<String> newset2 = new HashSet<>();
        for(String c:set2){
            newset2.add(c+"(())");
//            newset2.add("()()"+c);
//            newset2.add(c+"()()");
            newset2.add("(())"+c);
        }
        newset.addAll(newset2);
        return newset;

     }

     static void allParenthesis(int lp, int rp, String res){
        if(lp<0 || rp<0) return;
        else if(lp==0 && rp==0) System.out.println(res);
        else if(lp==rp){
            allParenthesis(lp-1,rp,res+"(");
        }

        else if(rp>lp){
            allParenthesis(lp-1,rp,res+"(");
            allParenthesis(lp, rp-1, res+")");
        }
     }

    public static void main(String[] args){
//        String[] out = find_all_well_formed_brackets(3);
//        System.out.println(Arrays.toString(out));

        allParenthesis(4,4,"");
    }
}
