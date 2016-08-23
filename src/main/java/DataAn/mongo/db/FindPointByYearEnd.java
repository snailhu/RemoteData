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

public class FindPointByYearEnd extends Thread{

	

	private HashMap<Integer,YearAndParamDataDto> resultMap;
	
	private MongoCollection<Document> collection;
	
	private Integer series;
	
	private long getPoint;
	
	
	private String[] params;
	public FindPointByYearEnd(

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
		Date endDate = DateUtil.format(params[1]);
		String year_end = (DateUtil.getYear(endDate))+"";
		//paramCount = (float)50;		
		long paramCount = collection.count(Filters.and(Filters.lte("year", year_end),Filters.gte("datetime", params[1])));
		System.out.println(DateUtil.getYear(endDate)+"数据总数："+paramCount);
		//获取所有的结果集合
		FindIterable<Document> document_It = collection.find(Filters.and(Filters.and(Filters.gte("year", year_end),Filters.lte("datetime", params[1]))));
		
		if(document_It!=null){			
			// cursor = document_It.iterator();
			YearAndParamDataDto yearAndParam = new YearAndParamDataDto();
			getResults(document_It.iterator(), yearAndParam,params);	

			try {
				resultMap.put(series, yearAndParam);
				System.out.println(Thread.currentThread().getName()+"endYear.....执行完毕");
				System.out.println(Thread.currentThread().getName()+"....."+year_end);
			} catch (Exception e) {
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
			long count =0;
		    while (cursor.hasNext()) {		    	
		    	if(count%getPoint==0){
		    		Document doc = cursor.next();
			    	if(doc.getString(params[2])!=null){	
			    	yearValue.add(doc.getString("datetime"));
			        paramValue.add(doc.getString(params[2]));	
			    	}		    	
		    	}		    	
		       count++;
		    }
		    System.out.println(Thread.currentThread().getName()+".....装载完毕");
		    yearAndParam.setParamValue(paramValue);
		    yearAndParam.setYearValue(yearValue);
	 
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

	

	

	
	
}
