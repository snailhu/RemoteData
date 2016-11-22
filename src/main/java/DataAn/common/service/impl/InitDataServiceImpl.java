package DataAn.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import DataAn.common.service.IInitDataService;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.sys.service.IPermissionService;

@Service
public class InitDataServiceImpl implements IInitDataService{

	@Resource
	private ISeriesService seriesService;
	@Resource
	private IPermissionService permissionService;
	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	
	@Override
	@Transactional
	public void initDataBase(){
		//初始化j9星系库数据
		seriesService.initJ9Series();
		//初始化j9系列参数
		j9Series_Star_Service.initJ9SeriesParameterData();
		//初始化权限库数据
		permissionService.initPermission();
	}
	
	

}
