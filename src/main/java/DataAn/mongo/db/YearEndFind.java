package DataAn.mongo.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.bson.Document;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

public class YearEndFind extends Thread {
	
	private ArrayBlockingQueue<YearAndParamDataDto> resultQueue;
	
	private MongoCollection<Document> collection;
		
	private String[] params;
	
	public YearEndFind(ArrayBlockingQueue<YearAndParamDataDto> resultQueue,MongoCollection<Document> collection,String ...params)  
    {  
       
        this.resultQueue=resultQueue;
        this.collection=collection;
        this.params = params;
    
    }  
	
	@Override
	public void run() {
		
		super.run();
		Date endDate = DateUtil.format(params[1]);
		String year_end = (DateUtil.getYear(endDate))+"";
		//paramCount = (float)50;		
		 long paramCount = collection.count(Filters.and(Filters.gte("year", year_end),Filters.lte("datetime", params[1])));
		//获取所有的结果集合
		FindIterable<Document> document_It = collection.find(Filters.and(Filters.and(Filters.gte("year", year_end),Filters.lte("datetime", params[1]))));
		
		if(document_It!=null && paramCount!=0){			
			// cursor = document_It.iterator();
			YearAndParamDataDto yearAndParam = new YearAndParamDataDto();
			getResults(document_It.iterator(), yearAndParam,params);	
			yearAndParam.setParamCount(paramCount);
			try {
				resultQueue.put(yearAndParam);
				System.out.println(Thread.currentThread().getName()+"endYear.....执行完毕");
				System.out.println(Thread.currentThread().getName()+"....."+year_end);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
	}
	
	private void getResults(MongoCursor<Document> cursor,YearAndParamDataDto yearAndParam,
			String... params) {
		try {
			List<String> yearValue=new ArrayList<String>();
			List<String> paramValue =  new ArrayList<String>();
		//	int count=0;
			System.out.println(Thread.currentThread().getName()+".....执行装载");
		    while (cursor.hasNext()) {
		    	//System.out.println(Thread.currentThread().getName()+"....."+params[2]);
		    //	if(count>=50){break;}
		    	Document doc = cursor.next();
		    	if(doc.getString(params[2])!=null){	
		    	yearValue.add(doc.getString("datetime"));
		        paramValue.add(doc.getString(params[2]));	
		    	}		    	
		     //   count++;
		    }
		    yearAndParam.setParamValue(paramValue);
		    yearAndParam.setYearValue(yearValue); 
		    System.out.println(Thread.currentThread().getName()+".....执行装载完毕");
		} finally {
		    cursor.close();
		}
	}
	
	
	
	public MongoCollection<Document> getCollection() {
		return collection;
	}

	public void setCollection(MongoCollection<Document> collection) {
		this.collection = collection;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	


	public ArrayBlockingQueue<YearAndParamDataDto> getResultQueue() {
		return resultQueue;
	}

	public void setResultQueue(ArrayBlockingQueue<YearAndParamDataDto> resultQueue) {
		this.resultQueue = resultQueue;
	}
	
}
