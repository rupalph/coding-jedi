import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;

public class EmployeeCount {
    int entryPointer = -1;

    static int employeeCount = 0;

    TreeMap<String, Integer> employeeCountAtAnyTime = null;

    ArrayList<String> entryList = null;
    PriorityQueue<String> exitQueue = null;
    //TimeComparator tc = null;

    void createEntries(String file) {
        //tc = new TimeComparator();
        employeeCountAtAnyTime = new TreeMap<String, Integer>();

        entryList = new ArrayList<String>();
        exitQueue = new PriorityQueue<String>();
        String temp = null;
        String[] tempArray = null;
        FileReader reader = null;
        BufferedReader brReader = null;
        try {
            reader = new FileReader(new File(file));
            brReader = new BufferedReader(reader);
            temp = brReader.readLine();
            while (temp != null) {
                tempArray = temp.split("[ ]");
                entryList.add(tempArray[0]);
                entryPointer++;
                employeeCount++;
                exitQueue.add(tempArray[1]);

                checkExitStatus();

                temp = brReader.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (brReader != null)
                    brReader.close();
                if (reader != null)
                    reader.close();
            } catch (IOException e) {

                e.printStackTrace();
            }

        }

    }

    private void checkExitStatus() {
        String temp1 = entryList.get(entryPointer);
        String temp2 = exitQueue.peek();
        if (temp1.compareTo(temp2) >= 0) {
            temp2 = exitQueue.poll();
            while (temp1.compareTo(temp2) >= 0) {
                employeeCount--;
                employeeCountAtAnyTime.put(temp2, new Integer(employeeCount));
                temp2 = exitQueue.poll();


            }

        }

    }

    /*
    9-3
    9:30-10:30
    10-6
    10:15-11
    11-7

    entryList = 9, 9:30
    exitList = 10, 10:30
    entryPointer = 2
    empCOunt = 2

    9 Entry      1
    9:30 Entry   2
    10 Entry     3
    10:15 Entry  4
    10:30 Exit   3
    11 Entry     4
    11 Exit      3
    3 Exit       2
    6 Exit       1
    7 Exit       0


     */
    public static void main(String[] args) {
        EmployeeCount eec = new EmployeeCount();
//        eec.createEntries("EntryExitFile.txt");
//// System.out.println(eec.employeeCountAtAnyTime);
//        String ceiliString = eec.employeeCountAtAnyTime.ceilingKey("11:19:20");
//        System.out.println(eec.employeeCountAtAnyTime.get(ceiliString));

        int result = eec.largestRectangleArea(new int[] {2,1,5,6,2,4,3,2});

        System.out.println(result);
    }

    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();

        int max = 0;
        int i = 0;

        while (i < height.length) {
            //push index to stack when the current height is larger than the previous one
            if (stack.isEmpty() || height[i] >= height[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                //calculate max value when the current height is less than the previous one
                System.out.println(stack);
                int p = stack.pop();
                int h = height[p];

                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                System.out.println(w+":"+ w*h);
                max = Math.max(h * w, max);
            }

        }

        while (!stack.isEmpty()) {
            System.out.println(stack);

            int p = stack.pop();
            int h = height[p];
            int w = stack.isEmpty() ? i : i - stack.peek() - 1;
            System.out.println(w+":"+ h);

            max = Math.max(h * w, max);
        }

        return max;
    }
}