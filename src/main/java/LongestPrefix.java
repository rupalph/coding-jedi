/**
 * Created by rupalph on 10/24/19.
 */
public class LongestPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        int pos = 0;
        boolean match=true;
        for(;match && pos < strs[0].length()+1;pos++){
            for(int i=0;i<strs.length-1;i++){
                if(!(pos<strs[i].length() && pos<strs[i+1].length())
                        || strs[i].charAt(pos) != strs[i+1].charAt(pos)) {
                    match = false;
                    break;
                }
            }
        }

        return strs[0].substring(0,pos-1);
    }
}
