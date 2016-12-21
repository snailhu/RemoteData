package DataAn.routing;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.Log4jUtil;
import DataAn.mongo.db.MongodbUtil;

public class DataSearchByTimeTask extends RecursiveTask<Map<String, YearAndParamDataDto>>{

	private Repo repo ;
	private Date startDate;
	private Date endDate;
	private List<String> paramlist= new ArrayList<String>();
	private List<DataSearchProcess> DataSearchProcesslist = new LinkedList<>();
	private  Map<String,DataSearchTaskConfig> paramrulesmap;
	public DataSearchByTimeTask(Repo repo,Date startDate,Date endDate,List<String> paramlist, Map<String,DataSearchTaskConfig> paramrulesmap)
	{
		super();
		this.repo=repo;
		this.startDate=startDate;
		this.endDate=endDate;
		this.paramlist=paramlist;
		this.paramrulesmap=paramrulesmap;
		for(String paramcode:paramlist)
		{
			DataSearchProcess queryprocess =new DataSearchProcess(this.paramrulesmap.get(paramcode));
			DataSearchProcesslist.add(queryprocess);
		}
		
	}
	@Override
	protected Map<String, YearAndParamDataDto> compute() {
		
		Map<String, YearAndParamDataDto> valstemp=new HashMap<String, YearAndParamDataDto>();				
		MongodbUtil mg = MongodbUtil.getInstance();
		MongoCollection<Document> collection = mg.getCollection(repo.database(), repo.collection());
		FindIterable<Document> document_It = collection.find(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));
		System.out.println("开始一轮迭代");											
		
		for (Document doc : document_It) {
				for(DataSearchProcess datasearchprocess:DataSearchProcesslist)
				{
					datasearchprocess.Process(doc);
				}
			}
				
		for(DataSearchProcess datasearchprocess:DataSearchProcesslist)
		{
			datasearchprocess.merge();
			valstemp.put(datasearchprocess.getParamcode(), datasearchprocess.getYearAndParam());
		}			
		return valstemp;
		
	}

}
