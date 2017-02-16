package DataAn.galaxyManager.option;

import java.util.ArrayList;
import java.util.List;

import DataAn.common.config.CommonConfig;

/**
 * Title: J9Series_Star_ParameterGroupType
 * @Description: j9系列 星星的参数组
 */
public enum J9Series_Star_ParameterType {
	
	/**飞轮*/
	FLYWHEEL {
		public String getName() {
			return "飞轮";
		}

		@Override
		public String getValue() {
			return "flywheel";
		}
	},
	/**陀螺*/
	TOP {
		public String getName() {
			return "陀螺";
		}

		@Override
		public String getValue() {
			return "top";
		}
	};
	public abstract String getName();
	
	public abstract String getValue();
	
	public static J9Series_Star_ParameterType getJ9SeriesStarParameterTypeByName(String name){
		if(name.indexOf(FLYWHEEL.getName()) > -1){
			return J9Series_Star_ParameterType.FLYWHEEL;
		}else{
			return J9Series_Star_ParameterType.TOP;
		}
	}
	
	public static J9Series_Star_ParameterType getJ9SeriesStarParameterType(String type) {
		switch (type) {
		case "flywheel":
			return J9Series_Star_ParameterType.FLYWHEEL;
			
		case "top":
			return J9Series_Star_ParameterType.TOP;
			
		default:
			return null;
		}
	}
	
	public static List<String> getFlywheelDeviceNameList() {
		List<String> list = CommonConfig.getFlywheelDeviceNames();
		if(list != null && list.size() > 0){
			return list;
		}
		else{
			list = new ArrayList<String>();
			list.add("Xa");
			list.add("Ya");
			list.add("Za");
			list.add("Xb");
			list.add("Yb");
			list.add("Zb");
//			list.add("飞轮a");
//			list.add("飞轮b");
//			list.add("飞轮c");
//			list.add("飞轮d");
//			list.add("飞轮e");
//			list.add("飞轮f");
			return list;			
		}
	}
	
	public static List<String> getTopDeviceNameList() {
		List<String> list = CommonConfig.getTopDeviceNames();
		if(list != null && list.size() > 0){
			return list;
		}
		else{
			list = new ArrayList<String>();
			list.add("陀螺");
			return list;			
		}

	}
	/**
	* Description:  获取飞轮数据类型 通过参数分类  "电流","转速","温度","指令","供电状态","角动量"
	* @return  
	*/
	public static List<String> getFlywheelParamTypeList() {
		List<String> list = CommonConfig.getFlywheelParamTypes();
		if(list != null && list.size() > 0){
			return list;
		}else{
			list = new ArrayList<String>();
			list.add("转速");
			list.add("电流");
			list.add("温度");
			list.add("指令");
			list.add("供电状态");
			list.add("角动量");
			return list;			
		}
	}
	
	/**
	* Description: 获取陀螺数据类型 通过名称分类
	* @return
	*/
	public static List<String> getTopParamTypeList() {
		List<String> list = CommonConfig.getTopParamTypes();
		if(list != null && list.size() > 0){
			return list;
		}else{
			list = new ArrayList<String>();
			list.add("角速率");
			list.add("角速度");
			list.add("温度");
			return list;			
		}
	}

}
