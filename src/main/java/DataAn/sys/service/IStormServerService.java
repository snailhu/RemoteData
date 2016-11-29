package DataAn.sys.service;

import DataAn.common.dao.Pager;
import DataAn.sys.dto.StormServerDto;

public interface IStormServerService {

	void create(StormServerDto server);

	void delete(String serverIds);
	
	void update(StormServerDto server);

	Pager<StormServerDto> getStormServerList(int pageIndex, int pageSize);

	boolean existServer(StormServerDto server);




}
