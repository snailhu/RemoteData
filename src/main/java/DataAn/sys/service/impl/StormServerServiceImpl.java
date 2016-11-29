package DataAn.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import DataAn.common.dao.Pager;
import DataAn.sys.dao.IStormServerDao;
import DataAn.sys.dto.StormServerDto;
import DataAn.sys.service.IStormServerService;

@Service
public class StormServerServiceImpl implements IStormServerService{

	@Resource
	private IStormServerDao stormServerDao;

	@Override
	public void create(StormServerDto server) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String serverIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(StormServerDto server) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pager<StormServerDto> getStormServerList(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existServer(StormServerDto server) {
		// TODO Auto-generated method stub
		return false;
	}
}
