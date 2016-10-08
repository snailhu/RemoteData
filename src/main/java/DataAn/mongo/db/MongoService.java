package DataAn.mongo.db;

import DataAn.Analysis.dto.YearAndParamDataDto;

public interface MongoService {
	
	public YearAndParamDataDto getList(int selectParamSize,String ...params)throws InterruptedException;
	
	//按时间批次获取数据
	public YearAndParamDataDto getListByTimeBatch(int selectParamSize,String ...params)throws InterruptedException;
}
