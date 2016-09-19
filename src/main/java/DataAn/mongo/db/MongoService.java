package DataAn.mongo.db;

import DataAn.Analysis.dto.YearAndParamDataDto;

public interface MongoService {
	
	public YearAndParamDataDto getList(int selectParamSize,String ...params)throws InterruptedException;
}
