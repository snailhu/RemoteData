package DataAn.mongo.db.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;
import DataAn.mongo.db.FindPointByYear;
import DataAn.mongo.db.FindPointByYearEnd;
import DataAn.mongo.db.FindPointByYearHead;
import DataAn.mongo.db.MongoService;
import DataAn.mongo.db.MongodbUtil;
import DataAn.mongo.init.InitMongo;

@Service
public class MongoServiceAopImpl implements MongoService {

	@Override
	public YearAndParamDataDto getList(int selectParamSize, String... params)
			throws InterruptedException {
		
		MongodbUtil mg = MongodbUtil.getInstance();
		MongoCollection<Document> collection = mg.getCollection(InitMongo.DATABASE_TEST, "star2");		
		//string 类型的时间转换成日期
		Calendar date1 = Calendar.getInstance();
		date1.setTime(DateUtil.format(params[0]));
		Date startDate = date1.getTime();//DateUtil.format(params[0]);
		Calendar date2 = Calendar.getInstance();
		date2.setTime(DateUtil.format(params[1]));
		Date endDate = date2.getTime();		
		int startYear = DateUtil.getYear(startDate);
		int endYear = DateUtil.getYear(endDate);		
		//根据年来确定需要开启的线程数
		int threadOpen = endYear-startYear;		 
		MongoCursor<Document> cursor =  null;
		long paramCount= 0;
		//Long totalDate =  (endDate.getTime() - startDate.getTime())/(1000*60*60*24);		
		//存放子线程

		paramCount = collection.count(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						

		List<Thread> allChridThread = new ArrayList<Thread>();
		HashMap<Integer,YearAndParamDataDto> resultMap = new HashMap<Integer,YearAndParamDataDto>();
		YearAndParamDataDto yearAndParam = new YearAndParamDataDto();
		

		//查出来的数据总量
	//	paramCount =  collection.count(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));
		//获取所有的结果集合	
		//	FindIterable<Document> a= collection.find(Filters.and(Filters.gte("datetime", params[0]),Filters.lte("datetime", params[1]))).batchSize(20);	
		
		//设置每页可显示的最多点数
		long total = 50000;
		if(selectParamSize==1){
			total =5000;
		}else if(selectParamSize==2){
			total =10000;
		}
		
		//如果参数的个数乘以每个参数查出来的数据小于等于总数则直接将查询结果返回
		if(paramCount*selectParamSize<=total){				
			//getResults(cursor,yearAndParam, params);	
			List<String> yearValue=new ArrayList<String>();
			List<String> paramValue =  new ArrayList<String>();
					
			FindIterable<Document> document_It = collection.find(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						
			for (Document doc : document_It) {
			    	if(doc.getString(params[2])!=null){	
			    	yearValue.add(DateUtil.format(doc.getDate("datetime")));
			        paramValue.add(doc.getString(params[2]));	
			    	}		    
			}
		    yearAndParam.setParamValue(paramValue);
		    yearAndParam.setYearValue(yearValue);
					
			return yearAndParam;					
		}
		else{	//如果比每页显示总数大
			
//			ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.SECONDS,
//	                 new ArrayBlockingQueue<Runnable>(5));
			long pear_param = total/selectParamSize;						
				//获取分子就是取多少个点
			long getpoint = paramCount/pear_param;	
			List<String> yearValue=new ArrayList<String>();
			List<String> paramValue =  new ArrayList<String>();
			if(threadOpen == 0){
				FindIterable<Document> document_It = collection.find(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						
				int count_for = 0;
				for (Document doc : document_It) {
					if(count_for%getpoint==0){
				    	if(doc.getString(params[2])!=null){	
				    	yearValue.add(DateUtil.format(doc.getDate("datetime")));
				        paramValue.add(doc.getString(params[2]));	
				    	}		    
					}
					count_for++;
				}
				System.out.println(Thread.currentThread().getName()+".....装载完毕"+count_for);
				yearAndParam.setYearValue(yearValue);
			    yearAndParam.setParamValue(paramValue);
				return yearAndParam;
			}
			if(threadOpen == 1){
				 
				FindPointByYearHead  headThread = new FindPointByYearHead(1,getpoint,resultMap, collection, params);
				allChridThread.add(headThread);
				headThread.start();				
				
				FindPointByYearEnd  endThread =  new FindPointByYearEnd(2,getpoint,resultMap, collection, params);
				allChridThread.add(endThread);
				endThread.start();	
//				executor.execute(headThread);
//				executor.execute(endThread);
			}
			if(threadOpen>1){
				int i = 1;
				FindPointByYearHead  headThread = new FindPointByYearHead(1,getpoint,resultMap, collection, params);
				allChridThread.add(headThread);
				headThread.start();	
//				executor.execute(headThread);
				
				for(;i<threadOpen;i++){
					FindPointByYear yearThread =  new FindPointByYear(i+1,getpoint,resultMap, collection, (startYear+i), params);					
					allChridThread.add(yearThread);
					yearThread.start();
//					executor.execute(yearThread);				
				}
				FindPointByYearEnd  endYearThread =  new FindPointByYearEnd(i+1,getpoint,resultMap,collection, params);
				allChridThread.add(endYearThread);	
				endYearThread.start();
			
				//executor.execute(endYearThread);
											
			}
			
			for(Thread year_Thread : allChridThread){			
				try {
					year_Thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}			
			for(int i=1;i<=threadOpen+1;i++){	
				yearValue.addAll(resultMap.get(i).getYearValue());
				paramValue.addAll(resultMap.get(i).getParamValue());
			}
			 yearAndParam.setParamValue(paramValue);
			 yearAndParam.setYearValue(yearValue);			
			return yearAndParam;
		}			
	}
}


