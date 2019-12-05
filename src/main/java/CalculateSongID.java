import java.util.*;
/**
 * Created by rupalph on 8/12/19.
 */
public class CalculateSongID {
    static class Pair {
        int left;
        int right;
        public Pair(int l, int r){
            this.left = l;
            this.right = r;
        }

        public String toString(){
            return left + " " + right;
        }
    }
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    ArrayList<Integer> IDsOfSongs(int rideDuration,
                                  ArrayList<Integer> songDurations)
    {
        // WRITE YOUR CODE HERE
        Map<Integer,List<Integer>> mapDuration = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();
        if(songDurations==null || songDurations.isEmpty())
            return result;
        int expectedRideDuration = rideDuration - 30;
        if(expectedRideDuration<=0) {
            return result;
        }
        int i=0;
        for(int duration: songDurations){
            mapDuration.computeIfAbsent(duration, k -> new ArrayList<>()).add(i);
            i++;
        }

        i=0;
        int max = 0;
        int maxIndex = 0;
        for(int duration: songDurations) {
            if(mapDuration.containsKey(expectedRideDuration-duration)) {
                List<Integer> list = mapDuration.get(expectedRideDuration - duration);

                int curMax = Math.max(songDurations.get(i),songDurations.get(list.get(0)));
                if(curMax>max) {
                    maxIndex = i;
                    max = curMax;
                }


            }
            i++;
        }


        result.add(maxIndex);
        List<Integer> list = mapDuration.get(expectedRideDuration - songDurations.get(maxIndex));
        if(list==null||list.isEmpty())
            return new ArrayList<>();
        if(list.get(0)==maxIndex && list.size()>=1)
            result.add(list.get(1));
        else
            result.add(list.get(0));

        return result;
    }


    public static void main(String[] args){
        CalculateSongID t = new CalculateSongID();
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(100);
//        list.add(50);
//        list.add(180);
//        list.add(40);
//        list.add(120);
//        list.add(10);
//        list.add(220);
//        list.add(210);
//        List<Integer> ans = t.IDsOfSongs(250,list);
//        System.out.println(ans);


        ArrayList<Integer> list = new ArrayList<>();

//        list.add(20);
//        list.add(10);

        list.add(0);
        list.add(0);
        list.add(5);
        list.add(1);
        list.add(9);
//        list.add(5);
//        list.add(6);
//        list.add(4);
//        list.add(220);
//        list.add(210);
        List<Integer> ans = t.IDsOfSongs(40,list);
        System.out.println(ans);
    }
}
