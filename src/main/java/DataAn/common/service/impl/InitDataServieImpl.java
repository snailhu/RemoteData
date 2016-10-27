package DataAn.common.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DataAn.common.service.IInitDataServie;
import DataAn.galaxyManager.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.service.ISeriesService;
import DataAn.sys.service.IPermissionService;

@Service("initDataSerice")
public class InitDataServieImpl implements IInitDataServie{

	@Resource
	private ISeriesService seriesService;
	@Resource
	private IPermissionService permissionService;
	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;
	
	@Override
	@Transactional
	public void initDataBase() throws Exception {
		//初始化j9星系库数据
		seriesService.initJ9Series();
		//初始化j9系列参数
		j9Series_Star_Service.initJ9SeriesParameterData();
		//初始化权限库数据
		permissionService.initPermission();
	}
	
	

}
