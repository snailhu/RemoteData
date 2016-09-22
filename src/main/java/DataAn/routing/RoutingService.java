package DataAn.routing;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;
import DataAn.mongo.db.MongodbUtil;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class RoutingService {

	private RoutingRepoService routingRepoService=new RoutingRepoService();
	
	public YearAndParamDataDto getData(RequestConfig requestConfig){
		
		Repo repo= routingRepoService.getExpectedRepo(requestConfig);
		
		MongodbUtil mg = MongodbUtil.getInstance();
		MongoCollection<Document> collection = mg.getCollection(repo.database(), repo.collection());		
		
		Calendar date1 = Calendar.getInstance();
		date1.setTime(DateUtil.format(requestConfig.getTimeStart()));
		Date startDate = date1.getTime();//DateUtil.format(params[0]);
		
		Calendar date2 = Calendar.getInstance();
		date2.setTime(DateUtil.format(requestConfig.getTimeEnd()));
		Date endDate = date2.getTime();		
		
		YearAndParamDataDto yearAndParam = new YearAndParamDataDto();
		List<String> yearValue=new ArrayList<String>();
		List<String> paramValue =  new ArrayList<String>();
		
		FindIterable<Document> document_It = collection.find(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						
		for (Document doc : document_It) {
		    	if(doc.getString(requestConfig.getProperties()[0])!=null){	
		    	yearValue.add(DateUtil.format(doc.getDate("datetime")));
		        paramValue.add(doc.getString(requestConfig.getProperties()[0]));	
		    	}		    
		}
		yearAndParam.setParamValue(paramValue);
	    yearAndParam.setYearValue(yearValue);
		return yearAndParam;
		
	}
	
}
