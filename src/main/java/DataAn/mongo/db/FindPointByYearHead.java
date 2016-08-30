package DataAn.mongo.db;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.bson.Document;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

public class FindPointByYearHead extends Thread {

	
	private HashMap<Integer,YearAndParamDataDto> resultMap;
	
	private MongoCollection<Document> collection;
	
	private Integer series;
	
	private long getPoint;
	
	
	private String[] params;
	public FindPointByYearHead(
			Integer series,long getPoint,HashMap<Integer,YearAndParamDataDto> resultMap,MongoCollection<Document> collection,String ...params)  
    {  
        this.resultMap=resultMap;
        this.collection=collection;
        this.params = params;
        this.series = series;
        this.getPoint=getPoint;
    
    }  
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Calendar date1 = Calendar.getInstance();
		date1.setTime(DateUtil.format(params[0]));
		Date startDate = date1.getTime();//DateUtil.format(params[0]);
		
//		Date startDate = DateUtil.format(params[0]);
		int year_start = (DateUtil.getYear(startDate)+1);
		Calendar date2 = Calendar.getInstance();
		date2.set(year_start, 0, 0, 0, 0, 0);
		Date year_start_n = date2.getTime();
		//paramCount = (float)50;	
		long paramCount_head =  collection.count(Filters.and(Filters.gte("datetime",startDate),Filters.lte("datetime", year_start_n)));
		System.out.println(DateUtil.getYear(startDate)+"数据总数："+paramCount_head);
		//获取所有的结果集合
		FindIterable<Document> document_It = collection.find(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime",year_start_n)));
		if(document_It!=null){			
			 // cursor = document_It.iterator();
			YearAndParamDataDto yearAndParam = new YearAndParamDataDto();
			//getResults(document_It.iterator(), yearAndParam,params);	
			//MongoCursor<Document> cursor_head =document_It.iterator();
			List<String> yearValue=new ArrayList<String>();
			List<String> paramValue =  new ArrayList<String>();
			int count_for = 0;
			long start = System.currentTimeMillis();
			System.out.println(start);			
			for (Document doc : document_It) {
				if(count_for%getPoint==0){
			    //	if(doc.getString(params[2])!=null){	
			    	yearValue.add(DateUtil.format(doc.getDate("datetime")));
			        paramValue.add(doc.getString(params[2]));	
			   // 	}		    
				}
				count_for++;
			}
			System.out.println(Thread.currentThread().getName()+".....装载完毕"+count_for);
			long end = System.currentTimeMillis() ;
			System.out.println(end);
			System.out.println(end-start);
		    yearAndParam.setParamValue(paramValue);
		    yearAndParam.setYearValue(yearValue);	
			try {
				//放入队列
				resultMap.put(1, yearAndParam);
				System.out.println(Thread.currentThread().getName()+"HeadYear.....执行完毕");
				System.out.println(Thread.currentThread().getName()+"....."+DateUtil.getYear(startDate));
				this.interrupt();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
	}
	
//	private void getResults(MongoCursor<Document> cursor_head,YearAndParamDataDto yearAndParam,
//			String... params) {
//		try {
//			List<String> yearValue=new ArrayList<String>();
//			List<String> paramValue =  new ArrayList<String>();
//		//	int count=0;
//			System.out.println(Thread.currentThread().getName()+".....执行装载");
//			int count_head =0;
//		    while (cursor_head.hasNext()) {		    	
//		    	if(count_head%getPoint==0){
//		    		Document doc = cursor_head.next();
//			    	if(doc.getString(params[2])!=null){	
//			    	yearValue.add(doc.getDate("datetime")+"");
//			    	 System.out.println(doc.getDate("datetime")+"");
//			        paramValue.add(doc.getString(params[2]));	
//			    	}		    	
//		    	}
//		    System.out.println("循环次数："+count_head);
//		    count_head++;
//		    }
//		    System.out.println(Thread.currentThread().getName()+".....装载完毕"+count_head);
//		    System.out.println(Thread.currentThread().getName()+".....装载完毕");
//		    yearAndParam.setParamValue(paramValue);
//		    yearAndParam.setYearValue(yearValue);	 
//		} finally {
//			cursor_head.close();
//		}
//	}
	
	
	public HashMap<Integer, YearAndParamDataDto> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<Integer, YearAndParamDataDto> resultMap) {	
		this.resultMap = resultMap;
	}

	public long getGetPoint() {
		return getPoint;
	}

	public void setGetPoint(long getPoint) {
		this.getPoint = getPoint;
	}

	public Integer getSeries() {
		return series;
	}

	public void setSeries(Integer series) {
		this.series = series;
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
}
