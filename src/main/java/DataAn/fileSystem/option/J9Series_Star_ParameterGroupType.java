package DataAn.fileSystem.option;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: J9Series_Star_ParameterGroupType
 * @Description: j9系列 星星的参数组数据类型
 * @author Shewp
 * @date 2016年7月26日
 */
public enum J9Series_Star_ParameterGroupType {
	
	/**飞轮*/
	FLYWHEEL {
		public String getName() {
			return "飞轮";
		}
	},
	/**陀螺*/
	TOP {
		public String getName() {
			return "陀螺";
		}
	};
	public abstract String getName();
	
	/**
	* Description: 获取飞轮数据类型 通过名称分类
	* @return  
	* @author Shenwp
	* @date 2016年8月2日
	* @version 1.0
	*/
	public static List<String> getFlywheelTypeOnName() {
		List<String> list = new ArrayList<String>();
		list.add("飞轮a");
		list.add("飞轮b");
		list.add("飞轮c");
		list.add("飞轮d");
		list.add("飞轮e");
		list.add("飞轮f");
		return list;
	}
	
	/**
	* Description:  获取飞轮数据类型 通过参数分类  "电流","转速","温度","指令","供电状态","角动量"
	* @return  
	* @author Shenwp
	* @date 2016年8月2日
	* @version 1.0
	*/
	public static List<String> getFlywheelTypeOnParams() {
		List<String> list = new ArrayList<String>();
		list.add("电流");
		list.add("转速");
		list.add("温度");
		list.add("指令");
		list.add("供电状态");
		list.add("角动量");
		return list;
	}
	
	/**
	* Description: 获取陀螺数据类型 通过名称分类
	* @return
	* @author Shenwp
	* @date 2016年8月2日
	* @version 1.0
	*/
	public static List<String> getTopTypeOnName() {
		List<String> list = new ArrayList<String>();
		return list;
	}

}