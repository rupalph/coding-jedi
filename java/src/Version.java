import java.util.Arrays;

/**
 * Created by rupalh on 5/22/18.
 */
public class Version {

    public int compareVersion(String version1, String version2) {
        String[] vstr1 = version1.split("\\.");
        String[] vstr2 = version2.split("\\.");
        // System.out.println(Arrays.toString(vstr1)+" "+Arrays.toString(vstr2));
        int i=0, j=0;
        for(;i<vstr1.length&&j<vstr2.length;i++,j++){
            int v1= Integer.parseInt(vstr1[i]);
            int v2= Integer.parseInt(vstr2[j]);
//            System.out.println(v1+" "+v2);
            if(v1!=v2)
                return v1-v2<0?-1:1;


        }
        System.out.println("i="+i+" j="+j);
        if(vstr1.length>vstr2.length && i<vstr1.length){
//            i--;
            for(;i<vstr1.length;i++) {
                int v1= Integer.parseInt(vstr1[i]);
                if(v1>0)
                    return 1;
            }


        }
        if(vstr2.length>vstr1.length && j<vstr2.length){
//            j--;
            for(;j<vstr2.length;j++) {
                int v2= Integer.parseInt(vstr2[j]);
                if(v2!=0)
                    return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args){
        Version v=new Version();
//        System.out.println(v.compareVersion("1.2","0.2"));
//        System.out.println(v.compareVersion("1.2","1"));
        System.out.println(v.compareVersion("1","1.1"));
//        System.out.println(v.compareVersion("0.2","1.0.2"));

    }
}
