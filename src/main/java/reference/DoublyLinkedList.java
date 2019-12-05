package com.code.samples;

import org.junit.Test;

public class DoublyLinkedList {

	class Node
	{
		int data;
		Node next;
		Node prev;
	}
	Node head=null,tail=null;
	public void reverse()
	{
		Node t1=null;
		
		Node t2=head;
		Node p;
		while(t2!=null)
		{
			//if(t1!=null)System.out.println("t1 "+t1.data+" t2 "+t2.data);
			p=t2.next;
			t2.next=t1;
			if(t1!=null)
			t1.prev=t2;
			t1=t2;
			t2=p;
		}
		
		head=t1;
		
		
	}
	public void insert(int i)
	{
		System.out.println("inserting "+i);
		Node n=new Node();
		n.data=i;
		n.next=null;
		n.prev=null;
		if(head==null) { head=tail=n; return; }
		
		tail.next=n;
		n.prev=tail;
		
		tail=n;
		
		
		
	}
	
	public void print()
	{
		Node tmp=head;
		while(tmp!=null) {
			System.out.print(tmp.data+ " ");
			tmp=tmp.next;
		}
		
		System.out.println();
	}
	@Test
	public void testReverse()
	{
		//Node head=null;
		insert(10);
		insert(5);
		insert(7);
		insert(3);
		insert(4);
		
		print();
		
		reverse();
		
		print();
		
	}
}
