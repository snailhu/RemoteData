package DataAn.routing;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

import DataAn.Analysis.dto.ParamAttributeDto;
import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;

public class DataSearchServiceTask extends RecursiveTask<Map<String, YearAndParamDataDto>>{
	
	private static final int THRESHOLD =3;//线程数
	private List<String> paramlist;
	//<参数名，参数的规则（最大值、最小值）>
	private Map<String,DataSearchTaskConfig> paramrulesmap =new HashMap<String,DataSearchTaskConfig>();
	private Date startdate;
	private Date enddate;
	private long timespace;
	private Repo repo;
	private RequestConfig requestConfig;	
	public DataSearchServiceTask(RequestConfig requestConfig, Repo repo)
	{
		this.repo = repo;
		paramlist = new ArrayList<String>();
		ParamAttributeDto properties[] =requestConfig.getProperties();
		for(int i=0;i<properties.length;i++)
		{
			paramlist.add(properties[i].getName());
			DataSearchTaskConfig datasearchtaskconfig = new DataSearchTaskConfig();
			datasearchtaskconfig.setMinvalue(properties[i].getMin());
			datasearchtaskconfig.setMaxvalue(properties[i].getMax());
			//这里的property应该是是code值
			datasearchtaskconfig.setProperty(properties[i].getValue());
			paramrulesmap.put(properties[i].getName(), datasearchtaskconfig);
		}
		startdate = DateUtil.format(requestConfig.getTimeStart());
		enddate = DateUtil.format(requestConfig.getTimeEnd());
		timespace =DateUtil.format(requestConfig.getTimeEnd()).getTime()-DateUtil.format(requestConfig.getTimeStart()).getTime();
		
	}
	@Override
	protected Map<String, YearAndParamDataDto> compute() {
		 List<DataSearchByTimeTask> forks = new LinkedList<>();
		 Map<String, YearAndParamDataDto> vals =new HashMap<String,YearAndParamDataDto>();
		
		//如果时间区间小于3个小时则不开多线程
		System.out.println("时间区间大小："+timespace);
		if(timespace<10800000)
		{
			DataSearchByTimeTask task =new DataSearchByTimeTask(repo,startdate,enddate,paramlist,paramrulesmap);
		}
		else{
			for(int i=0;i<THRESHOLD;i++)
			{
				Date starttime =startdate;
				Date endtime =new Date(startdate.getTime()+(timespace-1000*THRESHOLD)/THRESHOLD);
				//Date endtime =enddate;
				DataSearchByTimeTask task =new DataSearchByTimeTask(repo,starttime,endtime,paramlist,paramrulesmap);
				startdate = new Date(endtime.getTime()+1000);;
				forks.add(task);
				task.fork();
			}
			for(DataSearchByTimeTask task:forks)
			{
				if(vals.isEmpty())
				{
					for(String paramcode:task.join().keySet())
					{
						vals.put(paramcode, task.join().get(paramcode));
					}
				}
				else
				{
					for(String paramcode:task.join().keySet())
					{
						YearAndParamDataDto tempdto =new YearAndParamDataDto(); 
						//数量合并
						tempdto.setParamCount(vals.get(paramcode).getParamCount()+task.join().get(paramcode).getParamCount());
						//X轴合并
						List<String> YearValue =new ArrayList<String>();
						YearValue.addAll(vals.get(paramcode).getYearValue());
						YearValue.addAll(task.join().get(paramcode).getYearValue());
						tempdto.setYearValue(YearValue);
						//Y轴合并
						List<String> paramValue= new ArrayList<String>();
						paramValue.addAll(vals.get(paramcode).getParamValue());
						paramValue.addAll(task.join().get(paramcode).getParamValue());
						tempdto.setParamValue(paramValue);
						
						vals.put(paramcode, tempdto);
					}
				}
				 
				 
			}
		}
		
		return vals;
	}

}
