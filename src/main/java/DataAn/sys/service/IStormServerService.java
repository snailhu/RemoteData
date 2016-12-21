package DataAn.sys.service;

import DataAn.common.dao.Pager;
import DataAn.sys.dto.StormServerDto;

public interface IStormServerService {

	void save(StormServerDto server);

	void delete(String serverIds);
	
	void update(StormServerDto server);

	void updateByServerIP(String serverIP);

	Pager<StormServerDto> getStormServerList(int pageIndex, int pageSize);

	boolean existServer(StormServerDto server);





}
