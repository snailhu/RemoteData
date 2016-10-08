package DataAn.routing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

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
		for (String property : requestConfig.getProperties()) {
			DataSearchTaskConfig dataSearchTaskConfig=new DataSearchTaskConfig();
			dataSearchTaskConfig.setProperty(property);
			dataSearchTaskConfig.setStartDate(mongoFilter.getStartDate());
			dataSearchTaskConfig.setEndDate(mongoFilter.getEndDate());
			dataSearchTaskConfig.setRepo(routingRepoService.getTargetRepo(property, repo.index()));
			DataSearchTask task = new DataSearchTask(dataSearchTaskConfig);
		    forks.add(task);
		    task.fork();
		}
		
		for (DataSearchTask task : forks) {
		    vals.put(task.getDataSearchTaskConfig().getProperty(), task.join());
		}
		return vals;
	}
	
}
