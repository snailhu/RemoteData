package DataAn.routing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DataAn.common.utils.DateUtil;

public class RoutingRepoService {
	
	ConfigurationGetter configurationGetter=new ConfigurationGetter();
	
	private long period(RequestConfig requestConfig){
		try {
			Date timeEnd=DateUtil.format(requestConfig.getTimeEnd());
			Date timeStart=DateUtil.format(requestConfig.getTimeStart());
			Configuration configuration=configurationGetter.getConfiguration();
			long propertyNum=requestConfig.getPropertyCount();
			long needPoint=configuration.getCanvasPointNum()/propertyNum;
			long period=(timeEnd.getTime()-timeStart.getTime())/needPoint;
			return period;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Repo getExpectedRepo(RequestConfig requestConfig){
		long period=period(requestConfig);
		List<Repo> repos=getAllRepos();
		Repo expectedOne=null;
		Repo left=null;
		Repo right=null;
		int max=repos.size();
		for(int i=0;i<max;i++){
			Repo current=repos.get(i);
			if(current.period()>=period){
				right=current;
				break;
			}
			left=current;
		}
		if(left==null){
			left=right;
			expectedOne=right;
		} else if (right==null){
			right=left;
			expectedOne=left;
		}else{
			// one case .... 
			if(right.period()-period>=period-left.period()){
				expectedOne=left;
			}
			//maybe other cases ... 
		}
		return expectedOne;
		
	}
	
	/**
	 * 
	 * @return sorted repos.
	 */
	public List<Repo> getAllRepos(){
		return new ArrayList<Repo>(){
			{
				add(new Repo() {
					
					@Override
					public long period() {
						return 5*1000;
					}
					
					@Override
					public String name() {
						return "5s";
					}
					@Override
					public String database() {
						return "db_j9_star2";
					}
					@Override
					public String collection() {
						return "flywheel5s";
					}
				});
				
				add(new Repo() {
					
					@Override
					public long period() {
						return 30*1000;
					}
					
					@Override
					public String name() {
						return "30s";
					}
					
					@Override
					public String database() {
						return "db_j9_star2";
					}
					@Override
					public String collection() {
						return "flywheel30s";
					}
				});
				
				add(new Repo() {
					
					@Override
					public long period() {
						return 5*60*1000;
					}
					
					@Override
					public String name() {
						return "5min";
					}
					
					@Override
					public String database() {
						return "db_j9_star2";
					}
					@Override
					public String collection() {
						return "flywheel30m";
					}
				});
			}
			
			
		};
	}
	
	
	
	
	
	
}
