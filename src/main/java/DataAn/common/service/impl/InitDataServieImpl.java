package DataAn.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.service.IInitDataServie;
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.sys.service.IPermissionService;

@Service("initDataSerice")
public class InitDataServieImpl implements IInitDataServie{

	@Resource
	private ISeriesService seriesService;
	@Resource
	private IPermissionService permissionService;
		
	@Override
	@Transactional
	public void initDataBase() throws Exception {
		//初始化星系库数据
		seriesService.initSeries();
		//初始化权限库数据
		permissionService.initPermission();
	}
	
	

}
