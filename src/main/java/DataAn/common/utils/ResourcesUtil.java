package DataAn.common.utils;

import java.util.ArrayList;
import java.util.List;

public class ResourcesUtil {

	public static List<String> getkeyList(String key) {
		List<String> reslist = new ArrayList<String>();
		switch (key) {
		//逆名访问url
		case "anonymousURL":
			
			return reslist;
		//公共访问地址
		case "commonURL":
			
			return reslist;
		//用户管理url
		case "userManagerURL":
			reslist.add("/admin/user");
			reslist.add("/admin/role");
			reslist.add("/admin/permission");
			reslist.add("/admin/log");
			return reslist;
		default:
			return null;
		}
	}

}
