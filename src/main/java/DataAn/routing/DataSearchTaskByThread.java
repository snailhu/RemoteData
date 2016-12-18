package DataAn.routing;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

import org.bson.Document;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.Log4jUtil;
import DataAn.mongo.db.MongodbUtil;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class DataSearchTaskByThread extends RecursiveTask<Map<String, YearAndParamDataDto>>{
private Map<String,DataSearchTaskConfig> dataSearchTaskConfigs = new HashMap<String,DataSearchTaskConfig>();
	
	public DataSearchTaskByThread(Map<String,DataSearchTaskConfig> dataSearchTaskConfigs) {
		super();
		this.dataSearchTaskConfigs = dataSearchTaskConfigs;
	}

	private static final int THRESHOLD =3; //分多少个线程查询
	private Date startDate;
	private Date endDate;
	
	List<DataSearchTask> forks = new LinkedList<>();
	
	@Override
	protected Map<String, YearAndParamDataDto> compute() {
		
		List<String> paramslist = new ArrayList<String>();
		
		for(int i=0;i<THRESHOLD;i++)
		{
			DataSearchTaskByThread datasearchtask =new DataSearchTaskByThread(dataSearchTaskConfigs);
			//执行子任务
			datasearchtask.fork();
		}
		
		
		return null;
	}
	


}
