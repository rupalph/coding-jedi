package graph;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the function below.
     */
    static class DepedencyGraph {
        private Map<String,List<String>> dependencyMap = new HashMap<>(); //to hold all dependencies
        private LinkedHashMap<String,String> installedItems = new LinkedHashMap<>();
        private LinkedHashMap<String,String> explicitlyInstalledItems = new LinkedHashMap<>();


        public void process(String[] input){
            for(String line:input){
                System.out.println(line);
                String[] commands = line.split(" ");
                //process commands
                if(commands[0].trim().equals("DEPEND")){
                    for(int i = 2; i<commands.length; i++) {
                        if(dependsOn(commands[i], commands[1])) {
                            System.out.println(commands[i]+" depends on "+commands[1]+", ignoring command");
                        }
                        else {
                            dependencyMap.computeIfAbsent(commands[1].trim(), k -> new ArrayList<>()).add(commands[i]);
                            dependencyMap.putIfAbsent(commands[i], new ArrayList<>());
                        }
                    }
                    //System.out.println(adjMap);
                }
                else if(commands[0].trim().equals("INSTALL")){
                    if(installedItems.containsKey(commands[1]))
                        System.out.println(commands[1]+" is already installed");
                    else if(dependencyMap.containsKey(commands[1])) {
                        List<String> installOrder = findOrder(commands[1]);
                        for (String item : installOrder) {
                            if (!installedItems.containsKey(item)) {
                                installedItems.put(item, item);
                                System.out.println("Installing " + item);
                            }

                        }
                    }
                    else {
                        String item = commands[1];
                        if (!installedItems.containsKey(item)) {
                            installedItems.put(item, item);
                            System.out.println("Installing " + item);
                        }
                        else
                            System.out.println(item+" is already installed");
                    }
                    explicitlyInstalledItems.put(commands[1],commands[1]);
                }
                else if(commands[0].trim().equals("LIST")){
                    for(String item:installedItems.values()){
                        System.out.println(item);
                    }
                }
                else if(commands[0].trim().equals("REMOVE")){
                    //System.out.println(explicitlyInstalledItems);
                    if(!installedItems.containsKey(commands[1]))
                        System.out.println(commands[1]+" is not installed");
                    else if(hasDepdency(commands[1])) {
                        System.out.println(commands[1]+" is still needed");
                    }
                    else {
                        List<String> removeOrder = findOrder(commands[1]);
                        Collections.reverse(removeOrder);
                        System.out.println("Removing "+commands[1]);
                        removeOrder.remove(commands[1]);
                        installedItems.remove(commands[1]);
                        dependencyMap.remove(commands[1]);
                        explicitlyInstalledItems.remove(commands[1]);
                        for(String item:removeOrder){

                            if(!hasDepdency(item) && !explicitlyInstalledItems.containsKey(item)){
                                System.out.println("Removing "+item);
                                installedItems.remove(item);
                                dependencyMap.remove(item);
                                explicitlyInstalledItems.remove(item);
                            }
                        }
                    }
                }

            }
            //System.out.println(adjMap);
        }
        private boolean hasDepdency(String item) {
            for(List<String> items:dependencyMap.values()) {
                if(items.contains(item))
                    return true;
            }
            return false;
        }

        private boolean dependsOn(String from,String to) {
            if(dependencyMap.containsKey(from) && dependencyMap.get(from).contains(to))
                return true;
            return false;
        }

        private List<String>  findOrder(String item){
            Set<String> visited = new HashSet<>();
            Set<String> exploring = new HashSet<>();

            List<String> order = new LinkedList<>();

            if(!visited.contains(item) && dependencyMap.containsKey(item)) {
                dfs(item,visited,exploring,order);
                //System.out.println(order);
            }
            return order;
        }



        private void dfs(String startNode, Set<String> visited,Set<String> exploring,List<String> order){
            if(exploring.contains(startNode)) return; //cycle
            visited.add(startNode);
            exploring.add(startNode);
            //System.out.println("DFS:"+startNode);
            for(String neighbor:dependencyMap.get(startNode)){
                if(!visited.contains(neighbor))
                    dfs(neighbor, visited, exploring, order);
            }
            order.add(startNode);
            exploring.remove(startNode);
        }

    }
    static void doIt(String[] input) {
        DepedencyGraph g = new DepedencyGraph();
        g.process(input);
        //g.topSort();

    }


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int _input_size = 0;
        _input_size = Integer.parseInt(in.nextLine().trim());
        String[] _input = new String[_input_size];
        String _input_item;
        for(int _input_i = 0; _input_i < _input_size; _input_i++) {
            try {
                _input_item = in.nextLine();
            } catch (Exception e) {
                _input_item = null;
            }
            _input[_input_i] = _input_item;
        }

        doIt(_input);

    }
}