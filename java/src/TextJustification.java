import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rupalh on 5/27/18.
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        if(maxWidth == 0)
            return Arrays.asList(words);
        List<String> wordsPerLine = new ArrayList<>();
        int len = words[0].length();
        List<String> result = new ArrayList<>();
        int space = maxWidth - words[0].length();
        wordsPerLine.add(words[0]);
        for (int i=1;i<words.length;i++){
            String str = words[i];
            len += str.length()+1;
            if(len<=maxWidth) {
                wordsPerLine.add(str);
                space -= str.length();
            }
            else {
                System.out.println(wordsPerLine+" "+space);
                // if(space>0)
                result.add(build(wordsPerLine,space));
                // else
                // result.add(wordsPerLine.toString());
                wordsPerLine.clear();
                wordsPerLine.add(words[i]);
                space = maxWidth - words[i].length();
                len = words[i].length();
            }
        }
        String s1=build(wordsPerLine,wordsPerLine.size()-1);
        space = maxWidth - s1.length();
        if(space>0)
            s1 += String.format("%"+space+"s"," ");
        result.add(s1);
        return result;
    }

    private  String build(List<String> str, int space) {
        int spIn = 0;
        if(str.size()>1)
            spIn = space / (str.size()-1);
        int reminder = space;
        if(str.size() > 1)
            reminder = space % (str.size()-1);

        StringBuilder sb = new StringBuilder();
        sb.append(str.get(0));
        for(int i=1;i<str.size();i++){
            if(spIn>0)
                sb.append(String.format("%"+spIn+"s"," "));
            if((reminder)>0) {
                sb.append(" ");
                reminder--;
            }
            sb.append(str.get(i));

        }
        if(reminder>0)
            sb.append(String.format("%"+reminder+"s"," "));
        return sb.toString();
    }

    public static void main(String[] args){
        TextJustification tj = new TextJustification();
        List<String> result = tj.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
        System.out.println(result);
    }
}
