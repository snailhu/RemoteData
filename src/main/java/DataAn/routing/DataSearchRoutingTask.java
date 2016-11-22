package DataAn.routing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

import DataAn.Analysis.dto.ParamAttributeDto;
import DataAn.Analysis.dto.SingleParamDto;
import DataAn.Analysis.dto.YearAndParamDataDto;

public class DataSearchRoutingTask extends RecursiveTask<Map<String, YearAndParamDataDto>> {

	private RoutingRepoService routingRepoService=RoutingRepoService.get();
	
	private RequestConfig requestConfig;
	
	private Repo repo;
	
	private MongoFilter mongoFilter;

	public DataSearchRoutingTask(RequestConfig requestConfig, Repo repo,
			MongoFilter mongoFilter) {
		this.requestConfig = requestConfig;
		this.repo = repo;
		this.mongoFilter = mongoFilter;
	}


	@Override
	protected Map<String, YearAndParamDataDto> compute() {
		Map<String, YearAndParamDataDto> vals=new HashMap<String, YearAndParamDataDto>();
		List<DataSearchTask> forks = new LinkedList<>();
		//获取系列、星、设备
		String series = requestConfig.getSeries();
		String star = requestConfig.getStar();
		String paramType =requestConfig.getDevice();
		//for (String property : requestConfig.getProperties()) {
		for (ParamAttributeDto property : requestConfig.getProperties()) {
			DataSearchTaskConfig dataSearchTaskConfig=new DataSearchTaskConfig();
			dataSearchTaskConfig.setProperty(property.getValue());
			dataSearchTaskConfig.setStartDate(mongoFilter.getStartDate());
			dataSearchTaskConfig.setEndDate(mongoFilter.getEndDate());
			dataSearchTaskConfig.setMaxvalue(property.getMax());
			dataSearchTaskConfig.setMinvalue(property.getMin());
			dataSearchTaskConfig.setRepo(routingRepoService.getTargetRepo(requestConfig,property.getValue(),repo.index()));
			DataSearchTask task = new DataSearchTask(dataSearchTaskConfig);
		    forks.add(task);
		    task.fork();
		}
		
		for (DataSearchTask task : forks) {
		    //把参数对应的squence值转换为简写中文simplezh
			//需要实现vals.put(task.getDataSearchTaskConfig().getProperty(), task.join());
			vals.put(task.getDataSearchTaskConfig().getProperty(), task.join());
		}
		return vals;
	}
	
}
