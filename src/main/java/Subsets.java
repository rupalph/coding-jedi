import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rupalph on 8/25/19.
 */
public class Subsets {

    /*
     * Complete the function below.
     */
    static String[] generate_all_subsets(String s) {
        List<String> result = new ArrayList<>();
        char[] buffer = new char[s.length()];
        helper(s,0, buffer, 0,result);
        return result.toArray(new String[0]);
    }

    static void helper(String s, int i, char[] buffer, int sb_i, List<String> result){
        if( i == s.length()) {
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<sb_i;j++)
                sb.append(buffer[j]);
            result.add(sb.toString());
            return;
        }

// 	Exclusions
        helper(s, i+1, buffer, sb_i, result);

// 	inclusions
        buffer[sb_i]=s.charAt(i);
        helper(s, i + 1, buffer, sb_i+1,result);
    }

}
