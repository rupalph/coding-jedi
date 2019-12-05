import org.junit.Test;

/**
 * Created by rupalph on 8/7/19.
 */
public class LicensekeyFormat {

    public String licenseKeyFormatting(String s, int k) {

        StringBuffer sb = new StringBuffer();
        int count=k;
        char input[]=s.replaceAll("-","").toCharArray();
        for(int i=input.length-1;i>=0;i--){
            char c = input[i];
            if(count==0) {
                sb.append("-");
                count = k-1;
            }
            else {
                count--;
            }
            sb.append(c);

        }
        return sb.reverse().toString().toUpperCase();
    }

    @Test
    public void test1(){
        LicensekeyFormat l = new LicensekeyFormat();
        String res = l.licenseKeyFormatting("2-5g-3-J",2);
        System.out.println(res);
    }
}
