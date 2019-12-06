/**
 * Created by rupalph on 10/24/19.
 */
public class RomanNumbers {
    public String intToRoman(int num) {
        /*
        8 = 5 + 3 = VIII
        21 = 20 + 1 = 10 + 10 + 1 = XXI
        44 = 40 + 4 = XLIV
        112 = 100 + 10 + 2 = CXII
        */
        int[] decimals = {1,5,10,50,100,500,1000,5000};
        String[] romans = {"I","V", "X", "L", "C", "D", "M","_V"};
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<decimals.length-1;i+=2) {
            int r = num % 10;
            if(r == 4) {
                sb.insert(0,romans[i+1]);
                sb.insert(0,romans[i]);
            }
            else if(r>=5&&r<9){
                sb.insert(0,repeat(romans[i],r-5));
                sb.insert(0,romans[i+1]);
            }
            else if(r == 9) {
                sb.insert(0,romans[i+2]);
                sb.insert(0,romans[i]);
            }
            else {
                sb.insert(0,repeat(romans[i],r));
            }
            //System.out.println(r);
            num = num / 10;

            if(num<=0) break;

        }
        return sb.toString();
    }

    private String repeat(String s, int times){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<times;i++)
            sb.append(s);
        return sb.toString();
    }
}
