package ik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rupalph on 10/4/19.
 */
public class Parenthesis {

    public static List<String> generateParenthesis (int count) {
        StringBuffer sb = new StringBuffer();
        List<String> result = new ArrayList<>();
        gp(count, count, "", result);
        return result;
    }

    /*
    gp(4,4) ==> gp (3,4,( ) ==> gp(3,3,() ) ==> ..gp(1,1,()()() ) ==> gp(0,1, ()()()( ) ==> gp (0,0, ()()()())

    gp(2,4, (( ) ==> gp ( 2, 3, (() ) ==> gp(2,2, (()) ) ==> gp ( 1, 2, (())( ) ==> gp (1,1, (())() ) ==> ..

     */
    private static void gp(int l, int r, String sb, List<String> result) {
        if (l == 0 && r == 0)
            result.add(sb.toString());

        else {
            if (l < r) {
                gp(l, r - 1, sb + ")", result);
            }
            if(l>0){
                gp(l - 1, r, sb + "(", result);
            }
        }

    }

    public static void main(String[] args){
        List<String> res = generateParenthesis(4);

        System.out.println(res);
    }

}


