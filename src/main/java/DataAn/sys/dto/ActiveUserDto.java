package DataAn.sys.dto;

import java.util.List;
import java.util.Map;

/**
 * 用户身份信息，存入session 由于tomcat将session会序列化在本地硬盘上，所以使用Serializable接口
 */
public class ActiveUserDto implements java.io.Serializable { 

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String userName;
	
	private String passWord;
	
	private Map<String,List<SysPermissionDto>> menus;// 菜单

	private SysPermissionDto permissions;// 权限
	
	private Map<String,String> permissionItems;// 权限项
	
	private String permissionItemsJSON;// 权限项
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Map<String, List<SysPermissionDto>> getMenus() {
		return menus;
	}

	public void setMenus(Map<String, List<SysPermissionDto>> menus) {
		this.menus = menus;
	}

	public SysPermissionDto getPermissions() {
		return permissions;
	}

	public void setPermissions(SysPermissionDto permissions) {
		this.permissions = permissions;
	}

	public Map<String, String> getPermissionItems() {
		return permissionItems;
	}

	public void setPermissionItems(Map<String, String> permissionItems) {
		this.permissionItems = permissionItems;
	}

	public String getPermissionItemsJSON() {
		return permissionItemsJSON;
	}

	public void setPermissionItemsJSON(String permissionItemsJSON) {
		this.permissionItemsJSON = permissionItemsJSON;
	}

	@Override
	public String toString() {
		return "ActiveUserDto [id=" + id + ", userName=" + userName
				+ ", passWord=" + passWord + ", menus=" + menus
				+ ", permissions=" + permissions + ", permissionItems="
				+ permissionItems + ", permissionItemsJSON="
				+ permissionItemsJSON + "]";
	}

	

	
}
