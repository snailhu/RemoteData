package DataAn.routing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import org.bson.Document;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.Log4jUtil;
import DataAn.mongo.db.MongodbUtil;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class DataSearchTask extends RecursiveTask<YearAndParamDataDto> {
	
	private DataSearchTaskConfig dataSearchTaskConfig;
	
	public DataSearchTask(DataSearchTaskConfig dataSearchTaskConfig) {
		super();
		this.dataSearchTaskConfig = dataSearchTaskConfig;
	}

	@Override
	protected YearAndParamDataDto compute() {
		Repo repo=dataSearchTaskConfig.getRepo();
		Date startDate=dataSearchTaskConfig.getStartDate();
		Date endDate=dataSearchTaskConfig.getEndDate();
		Double maxtemp=Double.valueOf(dataSearchTaskConfig.getMaxvalue());
		Double mintemp=Double.valueOf(dataSearchTaskConfig.getMinvalue());
		
		
		MongodbUtil mg = MongodbUtil.getInstance();
		MongoCollection<Document> collection = mg.getCollection(repo.database(), repo.collection());		

		YearAndParamDataDto yearAndParam = new YearAndParamDataDto();
		List<String> yearValue=new ArrayList<String>();
		List<String> paramValue =  new ArrayList<String>();
		
		FindIterable<Document> document_It = collection.find(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						
		

		for (Document doc : document_It) {
			/*String paraVal = doc.getString(dataSearchTaskConfig.getProperty());
			if (paraVal != null) {
				yearValue.add(DateUtil.format(doc.getDate("datetime")));
				paramValue.add(paraVal);
			}*/
			String paraVal = doc.getString(dataSearchTaskConfig.getProperty());
			if(paraVal==null){
				paraVal = "\'-\'";
			}else{
			
				Double paraValtemp=null;
				try{paraValtemp=Double.valueOf(paraVal);}
				catch(Exception e)
				{
					String error = "将字符串转换为Double类型时出错";
			        Log4jUtil.getInstance().getLogger(DataSearchTask.class).error(error);
				}
				if((paraValtemp==null)|(paraValtemp>maxtemp) | (paraValtemp<mintemp))
				{paraVal = "\'-\'";}
			}
			yearValue.add(DateUtil.format(doc.getDate("datetime")));
			paramValue.add(paraVal);
			
		}
		yearAndParam.setParamCount(yearValue.size());
		yearAndParam.setParamValue(paramValue);
	    yearAndParam.setYearValue(yearValue);
		return yearAndParam;
	}

	public DataSearchTaskConfig getDataSearchTaskConfig() {
		return dataSearchTaskConfig;
	}
	
}
