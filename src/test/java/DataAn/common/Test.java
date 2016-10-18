package DataAn.common;

import java.util.HashSet;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					long begin = System.currentTimeMillis();
					Test test = new Test();
					test.print();
					long end = System.currentTimeMillis();
					System.out.println((end - begin) );
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}}).start();
	}	
	public void print() throws Exception{
		Set<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("c");
		for (String str : set) {
			System.out.println(str);
			Thread.sleep(10000);
		}
	}
}
