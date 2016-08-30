package DataAn.mongo.db;

import java.util.ArrayList;
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

public class FindPointByYear extends Thread{
	
	private Integer year;
	
	private HashMap<Integer,YearAndParamDataDto> resultMap;
	
	private MongoCollection<Document> collection;
	
	private Integer series;
	
	private long getPoint;
	
	
	private String[] params;
	public FindPointByYear(
			Integer series,long getPoint,HashMap<Integer,YearAndParamDataDto> resultMap,
			MongoCollection<Document> collection, Integer year,String ...params)  
    {  
		this.collection=collection;
		this.resultMap=resultMap;
        this.params = params;
        this.series = series;
        this.getPoint=getPoint;
        this.year = year;
   
    }  
	
	@Override
	public void run() {
		super.run();
		//获取所有的结果集合
		long start = System.currentTimeMillis();
		System.out.println(start);
		FindIterable<Document> document_It  = collection.find(Filters.eq("year", year));	
		long paramCount = collection.count(Filters.eq("year", year));
		System.out.println(year+"数据总数："+paramCount);
		long end = System.currentTimeMillis() ;
		System.out.println(end);
		System.out.println(end-start);
		if(document_It!=null ){
			 // cursor = document_It.iterator();
			YearAndParamDataDto yearAndParam = new YearAndParamDataDto();
//			getResults(document_It.iterator(), yearAndParam,params);	
			List<String> yearValue=new ArrayList<String>();
			List<String> paramValue =  new ArrayList<String>();
			int count_for = 0;
			for (Document doc : document_It) {
				if(count_for%getPoint==0){
			    	//if(doc.getString(params[2])!=null){	
			    	yearValue.add(DateUtil.format(doc.getDate("datetime")));
			        paramValue.add(doc.getString(params[2]));	
			    //	}		    
				}
			//	System.out.println(count_for);
				count_for++;
			}
			System.out.println(Thread.currentThread().getName()+".....装载完毕"+count_for);
		    yearAndParam.setParamValue(paramValue);
		    yearAndParam.setYearValue(yearValue);	
			try {
				resultMap.put(series, yearAndParam);
				System.out.println(Thread.currentThread().getName()+"year.....执行完毕");
				System.out.println(Thread.currentThread().getName()+"....."+year);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
	
	}
	
//	private void getResults(MongoCursor<Document> cursor,YearAndParamDataDto yearAndParam,
//			String... params) {
//		try {
//			List<String> yearValue=new ArrayList<String>();
//			List<String> paramValue =  new ArrayList<String>();
//		//	int count=0;
//			System.out.println(Thread.currentThread().getName()+".....执行装载");
//			long count =0;
//		    while (cursor.hasNext()) {		    	
//		    	if(count%getPoint==0){
//		    		Document doc = cursor.next();
//			    	if(doc.getString(params[2])!=null){	
//			    	yearValue.add(doc.getDate("datetime")+"");
//			        paramValue.add(doc.getString(params[2]));	
//			    	}		    	
//		    	}		    	
//		       count++;
//		    }
//		    System.out.println(Thread.currentThread().getName()+".....装载完毕"+count);
//		    System.out.println(Thread.currentThread().getName()+".....装载完毕");
//		    yearAndParam.setParamValue(paramValue);
//		    yearAndParam.setYearValue(yearValue);
//	 
//		} finally {
//		    cursor.close();
//		}
//	}
	
		
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

	
	public HashMap<Integer, YearAndParamDataDto> getResultMap() {
		return resultMap;
	}

	public void setResultMap(HashMap<Integer, YearAndParamDataDto> resultMap) {
		this.resultMap = resultMap;
	}

	public Integer getSeries() {
		return series;
	}

	public void setSeries(Integer series) {
		this.series = series;
	}

	public long getGetPoint() {
		return getPoint;
	}

	public void setGetPoint(long getPoint) {
		this.getPoint = getPoint;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	
}
