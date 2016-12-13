package DataAn.routing;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

import org.bson.Document;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;
import DataAn.mongo.db.MongodbUtil;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class RoutingService {

	private RoutingRepoService routingRepoService=RoutingRepoService.get();
	
	ConfigurationGetter configurationGetter=new ConfigurationGetter();

	private final ForkJoinPool forkJoinPool = new ForkJoinPool();
	
	public Map<String, YearAndParamDataDto> getData(RequestConfig requestConfig){
		
		
		System.out.println("***第1步***路由选择仓库开始时间："+ DateUtil.formatSSS(new Date()));
		long begin_routing = System.currentTimeMillis();
		
		Configuration configuration=configurationGetter.getConfiguration();

		Repo repo= routingRepoService.getExpectedRepo(requestConfig,configuration);
		Calendar date1 = Calendar.getInstance();
		date1.setTime(DateUtil.format(requestConfig.getTimeStart()));
		Date startDate = date1.getTime();//DateUtil.format(params[0]);
		
		Calendar date2 = Calendar.getInstance();
		date2.setTime(DateUtil.format(requestConfig.getTimeEnd()));
		Date endDate = date2.getTime();
		//如果小于两个小时
		if((endDate.getTime()-startDate.getTime())<86400000)
		{
			DefaultRepo  originalrepo= new DefaultRepo();
			originalrepo.setDatabase(repo.database());
			System.out.println("设备名称："+requestConfig.getDevice());
			if(requestConfig.getDevice().equals("flywheel")){
				originalrepo.setCollection("flywheel");
				originalrepo.setIndex(1);
			}
			if(requestConfig.getDevice().equals("top")){
				originalrepo.setCollection("top");
				originalrepo.setIndex(1);
			}
			repo=originalrepo;
		}
		else
		{
				MongodbUtil mg = MongodbUtil.getInstance();
				MongoCollection<Document> collection = mg.getCollection(repo.database(), repo.collection());		
									
				long paramCount= 0;
				paramCount = collection.count(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						
				
				if(paramCount-configuration.getExpectedPerPointNum()>0){
					Repo nextRepo=repo.next();
					while(paramCount-configuration.getExpectedPerPointNum()>0
							&&nextRepo!=null){
						repo=nextRepo;
						collection = mg.getCollection(repo.database(), repo.collection());		
						paramCount = collection.count(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						
					}
					nextRepo=repo.next();
				}
				else if(paramCount-configuration.getExpectedPerPointNum()<0){
					Repo aheadRepo=repo.ahead();
					while(paramCount-configuration.getExpectedPerPointNum()<0
							&&aheadRepo!=null){
						repo=aheadRepo;
						collection = mg.getCollection(repo.database(), repo.collection());		
						paramCount = collection.count(Filters.and(Filters.gte("datetime", startDate),Filters.lte("datetime", endDate)));						
						if(paramCount>(configuration.getExpectedPerPointNum()*1.2)){
							repo=repo.next();
							collection = mg.getCollection(repo.database(), repo.collection());
							break;
						}
						aheadRepo=repo.ahead();
					}
				}
		}
		
		
		MongoFilter mongoFilter=new MongoFilter();
		mongoFilter.setStartDate(startDate);
		mongoFilter.setEndDate(endDate);
		long end_routing = System.currentTimeMillis();
		System.out.println("***第1步***路由选择仓库结束时间："+ DateUtil.formatSSS(new Date())+"共耗时："+(end_routing-begin_routing));
		if(repo==null)
		{System.out.println("数据库为空");}
		Map<String, YearAndParamDataDto> vals=forkJoinPool.invoke(new DataSearchRoutingTask(requestConfig, repo, mongoFilter));
		return vals;
		
	}
	
}
