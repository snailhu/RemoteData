package DataAn.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.dao.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.sys.dao.IStormServerDao;
import DataAn.sys.domain.StormServer;
import DataAn.sys.dto.StormServerDto;
import DataAn.sys.dto.StormSysStatus;
import DataAn.sys.service.IStormServerService;

@Service
public class StormServerServiceImpl implements IStormServerService{

	@Resource
	private IStormServerDao stormServerDao;

	@Override
	@Transactional
	public void save(StormServerDto serverDto) {
		StormServer server = new StormServer();
		server.setServerIp(serverDto.getServerIp());
		server.setStatus(StormSysStatus.getStormSysStatus(serverDto.getStatusValue()));
		stormServerDao.add(server);
	}

	@Override
	@Transactional
	public void delete(String serverIds) {
		String[] serverIdArray = serverIds.split(",");
		for (String serverId : serverIdArray) {
			stormServerDao.delete(Long.parseLong(serverId));
		}
	}

	@Override
	public void update(StormServerDto serverDto) {
		StormServer server = new StormServer();
		server.setId(serverDto.getId());
		server.setServerIp(serverDto.getServerIp());
		server.setStatus(StormSysStatus.getStormSysStatus(serverDto.getStatusValue()));
		stormServerDao.update(server);
	}

	@Override
	@Transactional
	public void updateByServerIP(String serverIP){
		List<StormServer> list = stormServerDao.findByParam("serverIp", serverIP);
		if(list != null && list.size() > 0){
			StormServer server = list.get(0);
			server.setStatus(StormSysStatus.SHUTDOWN);
			stormServerDao.update(server);
		}
	}
	@Override
	public Pager<StormServerDto> getStormServerList(int pageIndex, int pageSize) {
		if(pageIndex == 0){
			pageIndex = 1;
		}
		List<StormServerDto> serverDtoList = new ArrayList<StormServerDto>();
		Pager<StormServer> serverPager = stormServerDao.selectByPager(pageIndex, pageSize);
		if(serverPager != null){
			List<StormServer> serverList = serverPager.getDatas();
			if(serverList != null && serverList.size() > 0){
				for (StormServer server : serverList) {
					serverDtoList.add(this.pojoToDto(server));
				}
			}			
		}
		Pager<StormServerDto> pager = new Pager<StormServerDto>(pageIndex, pageSize, serverPager.getTotalCount(), serverDtoList);
		return pager;		
	}

	private StormServerDto pojoToDto(StormServer server) {
		StormServerDto serverDto = new StormServerDto();
		serverDto.setId(server.getId());
		serverDto.setServerIp(server.getServerIp());
		serverDto.setStatusName(server.getStatus().getName());
		serverDto.setStatusValue(server.getStatus().getValue());
		serverDto.setCreateDate(DateUtil.format(server.getCreateDate()));
		return serverDto;
	}

	@Override
	public boolean existServer(StormServerDto server) {
		List<StormServer> list = stormServerDao.findByParam("serverIp", server.getServerIp());
		if(list != null && list.size() > 0){
			if(server.getId() == 0){
				return true;				
			}else{
				if(server.getId() != list.get(0).getId()){
					return true;
				}
			}
		}
		return false;
	}
}
