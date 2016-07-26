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

	/** 飞轮a */
	FLYWHEEL_A {
		@Override
		public String getName() {
			return "飞轮a";
		}
	},
	/** 飞轮b */
	FLYWHEEL_B {
		@Override
		public String getName() {
			return "飞轮b";
		}
	},
	/** 飞轮c */
	FLYWHEEL_C {
		@Override
		public String getName() {
			return "飞轮c";
		}
	},
	/** 飞轮d */
	FLYWHEEL_D {
		@Override
		public String getName() {
			return "飞轮d";
		}
	},
	/** 飞轮e */
	FLYWHEEL_E {
		@Override
		public String getName() {
			return "飞轮e";
		}
	},
	/** 飞轮f */
	FLYWHEEL_F {
		@Override
		public String getName() {
			return "飞轮f";
		}
	},
	/** 飞轮g */
	FLYWHEEL_G {
		@Override
		public String getName() {
			return "飞轮g";
		}
	},
	TOP {
		@Override
		public String getName() {
			return null;
		}
	};
	public abstract String getName();

	/**
	* @Title: get_FLYWHEE_Type
	* @Description: 获取飞轮数据类型
	* @return
	* @author Shenwp
	* @date 2016年7月26日
	* @version 1.0
	*/
	public static List<J9Series_Star_ParameterGroupType> get_FLYWHEE_Type() {
		List<J9Series_Star_ParameterGroupType> list = new ArrayList<J9Series_Star_ParameterGroupType>();
		J9Series_Star_ParameterGroupType[] s = J9Series_Star_ParameterGroupType.values();
		for (J9Series_Star_ParameterGroupType starDataType : s) {
			if(starDataType.name().indexOf("FLYWHEE") != -1){
				list.add(starDataType);
			}
		}
		return list;
	}
	
	/**
	* @Title: get_TOP_Type
	* @Description: 获取陀螺数据类型
	* @return
	* @author Shenwp
	* @date 2016年7月26日
	* @version 1.0
	*/
	public static List<J9Series_Star_ParameterGroupType> get_TOP_Type() {
		List<J9Series_Star_ParameterGroupType> list = new ArrayList<J9Series_Star_ParameterGroupType>();
		J9Series_Star_ParameterGroupType[] s = J9Series_Star_ParameterGroupType.values();
		for (J9Series_Star_ParameterGroupType starDataType : s) {
			if(starDataType.name().indexOf("TOP") != -1){
				list.add(starDataType);
			}
		}
		return list;
	}
}
