import java.util.ArrayList;
import java.util.List;


public class MyThread extends Thread {
	
	  public MyThread(String name)  
	    {  
	        this.setName(name);  
	    }  
	  
	    @Override  
	    public void run()  
	    {  
	        System.out.println(this.getName() + " staring...");  
	  
	        System.out.println(this.getName() + " end...");  
	    }  
	  
	    /** 
	     * @param args 
	     */  
	    public static void main(String[] args)  
	    {  
	        System.out.println("main thread starting...");  
	  
	        List<MyThread> list = new ArrayList<MyThread>();  
	  
	        for (int i = 1; i <= 5; i++)  
	        {  
	            MyThread my = new MyThread("Thrad " + i);  
	            my.start();  
	            list.add(my);  
	        }  
	  
	        try  
	        {  
	            for (MyThread my : list)  
	            {  
	                my.join();  
	            }  
	        }  
	        catch (InterruptedException e)  
	        {  
	            e.printStackTrace();  
	        }  
	  
	        System.out.println("main thread end...");  
	  
	    }  
}
