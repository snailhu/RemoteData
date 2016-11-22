package DataAn.routing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import DataAn.Util.JsonStringToObj;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.option.J9Series_Star_ParameterType;
import DataAn.storm.hierarchy.HierarchyModel;
import DataAn.storm.hierarchy.HieraychyUtils;
import DataAn.mongo.init.InitMongo;

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
		
		//获取系列和星和部件
		String series = requestConfig.getSeries();
		String star = requestConfig.getStar();
		String paramType =requestConfig.getDevice(); 
		
		//List<? extends Repo> repos=getAllRepos(requestConfig.getProperties()[0]);
		//List<? extends Repo> repos=getAllRepos(requestConfig.getProperties()[0],series,star,paramType);
		List<? extends Repo> repos=getAllRepos(requestConfig.getProperties()[0].getValue(),series,star,paramType);
		System.out.println("getAllRepo获取到的参数："+requestConfig.getProperties()[0]);
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
	/*public List<? extends Repo> getAllRepos(String property){
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
	}*/
	public List<? extends Repo> getAllRepos(String property,String series,String star,String drive  ){
		List<HierarchyModel> hierarchyModelList = null;
		String paramType = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(drive).getValue();
		String jsonstr=null;
		try {
			paramType = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(paramType).getValue();
			hierarchyModelList = HieraychyUtils.getHierarchyModels();
			List<RepoDto> list = new ArrayList<RepoDto>();
			for (HierarchyModel hierarchyModel : hierarchyModelList) {
				RepoDto repodto =new RepoDto();
				repodto.setPeriod(hierarchyModel.getInterval());
				String databasename = InitMongo.getDataBaseNameBySeriesAndStar(series,star);
				repodto.setDatabase(databasename);
				repodto.setCollection(paramType+hierarchyModel.getName());
				repodto.setName(hierarchyModel.getName());
				list.add(repodto);
				//list.add(paramType + hierarchyModel.getName());
			}
			JSONArray array = JSONArray.fromObject(list);
			jsonstr = array.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		try{
			//String string=new String(getBytes(Repo.class.getResourceAsStream("repo.json")),"utf-8");
			String string=jsonstr;
			List<DefaultRepo> repos= JsonStringToObj.jsonArrayToListObject(string, DefaultRepo.class, new HashMap());
			
			for(int i=0;i<repos.size();i++){
				repos.get(i).setIndex(i);
				repos.get(i).setRepos(repos);
			}
			return repos;
		}catch(Exception e){
		}
		return new ArrayList();
		/*List<HierarchyModel> hierarchyModelList = null;
		String paramType = J9Series_Star_ParameterType.getJ9SeriesStarParameterType("flywheel").getValue();
		try {
			paramType = J9Series_Star_ParameterType.getJ9SeriesStarParameterType(paramType).getValue();
			hierarchyModelList = HieraychyUtils.getHierarchyModels();
			List<String> list = new ArrayList<String>();
			for (HierarchyModel hierarchyModel : hierarchyModelList) {
				list.add(paramType + hierarchyModel.getName());
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;*/
	}
	
	/*public Repo getTargetRepo(String property,int index){
		return getAllRepos(property).get(index);
	}*/
	public Repo getTargetRepo(RequestConfig requestConfig,String property,int index){
		//return getAllRepos(property).get(index);
		
		//获取系列、星、设备
		String series = requestConfig.getSeries();
		String star = requestConfig.getStar();
		String paramType =requestConfig.getDevice();  
		return getAllRepos(property,series,star,paramType).get(index);
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
