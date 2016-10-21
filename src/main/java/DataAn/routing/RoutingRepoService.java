package DataAn.routing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import DataAn.Util.JsonStringToObj;
import DataAn.common.utils.DateUtil;

public class RoutingRepoService {
	
	public static RoutingRepoService get(){
		return new RoutingRepoService();
	}
	
	private long period(RequestConfig requestConfig,Configuration configuration){
		try {
			Date timeEnd=DateUtil.format(requestConfig.getTimeEnd());
			Date timeStart=DateUtil.format(requestConfig.getTimeStart());
			int propertyNum=requestConfig.getPropertyCount();
			int needPoint=configuration.getCanvasPointNum()/propertyNum;
			configuration.setExpectedPerPointNum(needPoint);
			long period=(timeEnd.getTime()-timeStart.getTime())/needPoint;
			return period;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Repo getExpectedRepo(RequestConfig requestConfig,Configuration configuration){
		long period=period(requestConfig,configuration);
		List<? extends Repo> repos=getAllRepos(requestConfig.getProperties()[0]);
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
	public List<? extends Repo> getAllRepos(String property){
		try{
			String string=new String(getBytes(Repo.class.getResourceAsStream("repo.json")),"utf-8");
			List<DefaultRepo> repos= JsonStringToObj.jsonArrayToListObject(string, DefaultRepo.class, new HashMap());
			for(int i=0;i<repos.size();i++){
				repos.get(i).setIndex(i);
				repos.get(i).setRepos(repos);
			}
			return repos;
		}catch(Exception e){
		}
		return new ArrayList();
	}
	
	public Repo getTargetRepo(String property,int index){
		return getAllRepos(property).get(index);
	}
	
	public static byte[] getBytes(InputStream input) {
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    byte[] buffer = new byte[4096];
	    int n = 0;
	    try {
			while (-1 != (n = input.read(buffer))) {
			    output.write(buffer, 0, n);
			}
			output.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	    return output.toByteArray();
	}
	
	
	
	
}
