/**
 * Created by rupalph on 8/20/19.
 */
public class UrlShortening {
    final         char[] input = new char[]{'a', 'b', 'c'};

    int urlIndex = 0;
    int inputIndex = 0;
    String generateNextShortenedId(String lastShortenedUrl){
        System.out.println(urlIndex+":"+inputIndex);
        if(inputIndex == input.length) {
            urlIndex++;
            inputIndex=0;
        }
        if(urlIndex==3){
            return "STOP";
        }
        String urlId =  (lastShortenedUrl.substring(0,urlIndex)+input[inputIndex]+lastShortenedUrl.substring(urlIndex+1));
        inputIndex++;


        return urlId;
    }

    void printAllShortenedUrls() {
        int len = input.length;
        String url = getSeed(input[0],len);
        while(!url.equals("STOP")) {

            url = generateNextShortenedId(url);

            System.out.println(url);
        }


    }
    private String getSeed(char c, int len) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<len;i++)
            sb.append(c);
        return sb.toString();
    }

    public static void main(String[] args){
        UrlShortening s = new UrlShortening();

        char[] input={'a','b','c'};
        s.printAll(3,input);
        String nextUrlId = s.getNext("aaa", 2, 3, input, 1);
        System.out.println(nextUrlId);

        String nextUrlId2 = s.getNext(nextUrlId, 2, 3, input, 2);
        System.out.println(nextUrlId2);

        String nextUrlId3 = s.getNext(nextUrlId2, 1, 3, input, 1);
        System.out.println(nextUrlId3);

        String nextUrlId4 = s.getNext(nextUrlId3, 2, 3, input, 0);
        System.out.println(nextUrlId4);

        String nextUrlId5 = s.getNext(nextUrlId3, 2, 3, input, 1);
        System.out.println(nextUrlId5);

        String nextUrlId6 = s.getNext(nextUrlId3, 2, 3, input, 2);
        System.out.println(nextUrlId6);


        String u = s.getUrlShorterned(1000000789);
        System.out.println(u);
    }

    public void printAll(int len, char[] input) {
        printNext("", len, input);

    }

    private void printNext(String prefix, int len, char[] input) {

        if(len==0) {
            System.out.println(prefix);
            return;
        }


        for(int i=0;i<input.length;i++)
            printNext(prefix+input[i],len-1,input);
    }




    private String getNext(String last, int lastI, int len, char[] input, int inputI) {

           return last.substring(0,lastI)+input[inputI]+last.substring(lastI+1);
    }


    private String getUrlShorterned(int n){
        String letters =  "abcdefghijklmnopqrstuvwxyzABCDEF"+
        "GHIJKLMNOPQRSTUVWXYZ0123456789";
        char map[] = letters.toCharArray();

        StringBuffer sb = new StringBuffer();
        while(n>0){
            sb.append(map[n%62]);
            n=n/62;
        }

        sb.reverse();
        return sb.toString();
    }
}