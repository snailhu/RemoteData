package DataAn.common;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {
	volatile LinkedList<Integer> list = new LinkedList<Integer>();
	LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
	
	public static void main(String[] args) {
		System.out.println("...");
		List<Integer> list2 = new ArrayList<Integer>();
		for (int i = 1; i < 50; i++) {
			list2.add(i);
		}
		for (final Integer i : list2) {
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						System.out.println("come in i: " + i);
						Test test = new Test();
						test.print2(i);
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}}).start();			
			
		}
	}	
	public void print(int i) throws Exception{
		list.addLast(i);		
		Integer first = list.poll();
		while(first != null){
			System.out.println("i: " + first);
			Thread.sleep(1000);
			first = list.poll();
		}
		
	}
	public void print2(int i) throws Exception{
		queue.put(i);
		Integer first = queue.take();
		while(first != null){
			System.out.println("i: " + first);
			Thread.sleep(1000);
			first = queue.take();
		}
		
	}
}
