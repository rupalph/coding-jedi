package ik;

import org.junit.Test;

import java.util.*;

/**
 * Created by rupalph on 8/16/19.
 */
public class Sorting {

    /*
     * Complete the mergeArrays function below.
     */
    static int[] mergeArrays(int[][] arr) {
        /*
         * Write your code here.
         */
        int n = arr.length;
        int m = arr[0].length;
        PriorityQueue<HeapNode> pq = new PriorityQueue<>((a, b)-> Integer.compare(a.val, b.val));
        boolean isAsc = isArraySortedInAscOrder(arr[0],arr[1]);
        if(!isAsc)
            pq = new PriorityQueue<>((a, b)-> Integer.compare(b.val, a.val));
        int outputIndex=0;
        int[] output = new int[n*m];

        //build the heap
        for(int row=0;row<n;row++) {
            HeapNode node = new HeapNode(arr[row][0], row, 0);
            pq.offer(node);
        }

        while(!pq.isEmpty()) {
            HeapNode min = pq.poll();
            output[outputIndex++] = min.val;
            if (min.rowId < n && min.colId + 1 < m) {
                int rowId = min.rowId;
                int colId = min.colId + 1;
                pq.offer(new HeapNode(arr[rowId][colId], rowId, colId));
            }

        }
        return output;
    }

    static boolean isArraySortedInAscOrder(int[] a, int b[]) {
        if(a[0]<b[0])
            return true;
        return false;
    }

    static class HeapNode {

        int val;
        int colId; //col -> element position
        int rowId; //row -> array #

        public HeapNode(int v, int r, int c){
            this.val = v;
            this.rowId = r;
            this.colId = c;
        }
    }

    public static int[] merge2Arr(int[] arr1,int[] arr2){
        int i=0;
        int j=0;
        int k=arr1.length;
        while(i<arr1.length&&j<arr1.length&&k<arr2.length){
            if(arr2[k]!=0&&arr2[k]<=arr1[i]){
                swap(arr2,k,arr2,j);
                j++;
            }
            else if(arr1[i]<=arr2[j]){
                arr2[k]=arr2[j];
                arr2[j]=arr1[i];
                i++;j++;
            }
            else {
                j++;
            }
        }

        return arr2;
    }

    private static void swap(int[] arr1, int i, int[] arr2, int j) {
        int t = arr2[j];
        arr2[j]=arr1[i];
        arr1[i]=t;
    }

    @Test
    public void  testMergeArray() {
        int[][] input = {{1, 3, 5, 7},
                {2, 4, 6, 8},
                {0, 9, 10, 11}};

       int[] output =  mergeArrays(input);
       System.out.println( Arrays.toString(output));


        int[][] input2 = {{10, 30, 50, 70},
                {200, 400, 600, 800},
                {0, 10, 10, 11}};


        int[] output2 =  mergeArrays(input2);
        System.out.println( Arrays.toString(output2));

        int[][] input3 = {{1000, 300, 50, 7},
                {200, 40, 8, 6},
                {11, 10, 10, 0}};


        int[] output3 =  mergeArrays(input3);
        System.out.println( Arrays.toString(output3));


    }

    @Test
    public void  testMergeArray2() {
        int[] input1 = {1, 3, 5, 7};
        int[] input2 =       {2, 4, 6, 8, 0, 0, 0, 0};
        int[] output =  merge2Arr(input1, input2);
        System.out.println( Arrays.toString(output));



    }

    /*
     * Complete the function below.
     */
    static int[] topK(int[] arr, int k) {
        //k largest elements

        //1. sort array and get k elements
        //2. maintain heap of k elements
        //    -fetch them when done processing all numbers so far

        Heap heap = new Heap(k);
        for(int num:arr){
            heap.insert(num);
        }

        return heap.getValues();
    }
    static class Heap {
        private LinkedHashMap<Integer, Integer> heap = new LinkedHashMap<>();
        private int capacity;
        public Heap(int capacity){
            this.capacity = capacity;
        }
        public void insert(int ele){
            if(heap.isEmpty() ||
                    !heap.containsKey(ele) &&
                            (heap.size()<capacity) ||
                                    ele > heap.keySet().iterator().next()) {
                heap.put(ele,ele);
                heap = sort();
                if(heap.size()>capacity) {
                    int val = heap.get(heap.size() - 1);
                    heap.remove(val);
                }
            }
        }

        private LinkedHashMap<Integer, Integer> sort() {
            LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();

            heap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

            return sortedMap;
        }


        public int[] getValues() {
            return heap.entrySet().stream().mapToInt(x->x.getKey()).toArray();
        }
    }

    @Test
    public void testTopK(){
        int[] input1 = {1, 3, 5, 7};
        int[] input2 =       {2, 4, 6, 8, 0, 0, 0, 0};
        int[] output =  topK(input1, 2);
        int[] output2 =  topK(input2, 4);
        System.out.println( Arrays.toString(output));
        System.out.println( Arrays.toString(output2));

    }
}
