package com.code.samples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;

import org.junit.Test;

public class SolutionIter implements Iterable<Integer> {


	
   
    private List<Integer> myIntList;
    
    //read a file and identifies integers/decimal numbers per line

	public SolutionIter(Reader inp) throws IOException
    {
		myIntList=new ArrayList<Integer>();
        BufferedReader br=new BufferedReader(inp);
        String line;
        while((line=br.readLine())!=null)
        {
            try{
            Integer i =Integer.parseInt(line.trim());
            myIntList.add(i);
            }catch(NumberFormatException e)
            {
                //do nothing,skip
            }
            
        }
        br.close();
        
    }

	//returns iterator
    public Iterator<Integer> iterator()
    {
        return myIntList.iterator();
    }
    



/**
 * Example usage:
 *
 * for (Integer x : new SolutionIter(reader)) {
 *     System.out.println(x);
 * }
 */


} 