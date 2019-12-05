import java.util.*;

/**
 * Created by rupalph on 8/23/19.
 */
public class Expressions {

    /*
     * Complete the function below.
     */


    static void generateExpressions(String s, long target, String expression, long left, long right, List<String> result, int i){

        if(i==s.length() && left == target){
            result.add(expression);
            return;
        }
        if(i==s.length()) return;
//        System.out.print(expression+" "+"left="+left+" right="+right+" i="+i);
        for(int j=i;j<s.length();j++) {
            String cur_str = s.substring(i,j+1);
            long cur = Long.parseLong(cur_str);
//            System.out.println(" processing.."+cur_str);
            if(i==0)
                generateExpressions(s, target, expression+cur_str, cur, cur, result, i+cur_str.length());
            else {
                generateExpressions(s, target, expression + "+" + cur_str, left + cur, cur, result, i + cur_str.length());
                generateExpressions(s, target, expression + "*" + cur_str, left-right + cur*right, right*cur, result, i + cur_str.length());
            }

        }

    }

    static void allSubset(String s, String prefix, int i, List<String> result){


        if(i==s.length()) {
            result.add(prefix);
            return;
        }


        allSubset(s, prefix+s.charAt(i)  , i+1, result);
        allSubset(s, prefix  , i+1, result);

    }

    static List<String> allSubset(String s){
        List<String> result = new ArrayList<>();
        allSubset(s, "", 0, result);
        return result;

    }

    static void allPermutations(String s, String prefix, int len, List<String> result){


        if(prefix.length()==len) {
            result.add(prefix);
            return;
        }

        for(int j=0;j<s.length();j++) {
            allPermutations(s, prefix + s.charAt(j), len, result);
//            allPermutations(s, prefix, i + 1, result);
        }

    }

    static List<String> allPermutations(String s, int len){
        List<String> result = new ArrayList<>();
        allPermutations(s, "", len, result);
        return result;

    }



    public static void main(String[] args){
        String[] out = generateExpressions("222", 24L);
        System.out.println(Arrays.asList(out));

        String[] out2 = generateExpressions("22", 4L);
        System.out.println(Arrays.asList(out2));



        String[] out4=generateExpressions("1234", 10L);
        System.out.println(Arrays.asList(out4));


        String[] out5=generateExpressions("050505", 5L);
        System.out.println(Arrays.asList(out5));

        List<String> res = allSubset("1234");
        System.out.println(res);


        List<String> res2 = allPermutations("1234",3);
        System.out.println(res2);
    }

    private static String[] generateExpressions(String s, long target) {
        String seed = s.charAt(0)+"";
        List<String> result = new ArrayList<>();
        generateExpressions(s, target, "", 0L, 0L, result, 0);
        System.out.println("Total output # "+result.size());
        return result.toArray(new String[0]);

    }
}
