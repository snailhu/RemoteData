package DataAn.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import DataAn.sys.dao.IStormServersDao;
import DataAn.sys.service.IStormServersService;

@Service
public class StormServersServiceImpl implements IStormServersService{

	@Resource
	private IStormServersDao stormServersDao;
}
