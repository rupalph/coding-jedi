import java.util.*;

/**
 * Created by rupalh on 5/23/18.
 */
public class CourseSchedule {

        Map<Integer,List<Integer>> edges;
        Set<Integer> visited;
        Set<Integer> completed;

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            edges =  new HashMap<>();
            visited = new HashSet<Integer>();
            completed = new HashSet<Integer>();
            for(int i=0;i<prerequisites.length;i++)
            {
                int src=prerequisites[i][1];
                int dst=prerequisites[i][0];
                edges.computeIfAbsent(src, (x -> new ArrayList<>())).add(dst);
            }
            System.out.println(edges);
            for(int i:edges.keySet())
            {
                    boolean result = dfs(i);
                    if(!result)
                        return false;
            }
            return true;
        }

        public boolean dfs(int src){
            boolean result = true;
            if(!visited.contains(src)) {
                System.out.println(src + " ");
                visited.add(src);

                for (int node : edges.get(src)) {
                    if(edges.containsKey(node) && !dfs(node))
                        return false;
                }
                completed.add(src);
            }
            else if(!completed.contains(src))
                result = false;
            System.out.println(src+" "+result);
            return result;
        }


    public static void main(String[] args){
        CourseSchedule cs= new CourseSchedule();
        boolean result = cs.canFinish(2, new int[][]{{1,0},{0,1}});
        System.out.println(result);

        boolean result2 = cs.canFinish(2, new int[][]{{1,0}});
        System.out.println(result2);


        boolean result3 = cs.canFinish(4, new int[][]{{2,0},{1,0},{3,1},{3,2},{1,3}});
        System.out.println(result3);
    }


}
