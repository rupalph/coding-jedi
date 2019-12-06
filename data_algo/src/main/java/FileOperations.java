import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rupalph on 8/7/19.
 */
public class FileOperations {


    public List<Double> extractNumbers(String text,String delim){
        String[] lines = text.split(delim);
        List<Double> output = new ArrayList<>();
        for(int i=0;i<lines.length;i++){
            String line = lines[i];

            int digit = 0;
            int decimal = 0;
            StringBuffer sb = new StringBuffer();
            for(char c:line.toCharArray()){
                if(Character.isDigit(c) && decimal>=0&&decimal<=1) {
                    digit++;
                    sb.append(c);
                }
                else if(c=='.' && digit>0 && decimal>=0) {
                    if(decimal==0)
                        sb.append(c);
                    decimal++;

                }
            }
            System.out.println(sb.toString());
            output.add(Double.parseDouble(sb.toString()));
        }
        return output;
    }

    public Pair calculateStdDev(List<Double> input){

        double sum = input.stream().reduce(0.0, Double::sum);
        final double avg = sum/input.size();

        System.out.println(avg);
        sum = input.stream().mapToDouble(a->Math.pow(a-avg,2)).sum();
        double std = sum/input.size();

        return new ImmutablePair(std,Math.sqrt(std));

    }

    public static void main(String[] args){
        FileOperations f = new FileOperations();
        List<Double> input = f.extractNumbers("...9.0...7\nlll 81 ppp\n7\n7.2.3", "\n");

        Pair pair = f.calculateStdDev(input);

        System.out.println(pair);
    }
}
