package DataAn.sys.dto;

public class RoleDto {

	// 角色ID
    private long id ;

    // 角色名
    private String name;

    // 角色描述
    private String description;

    // 权限Json数据
    private String permissionJsonData = "{}";

    // 创建日期
    private String createDate ;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPermissionJsonData() {
		return permissionJsonData;
	}

	public void setPermissionJsonData(String permissionJsonData) {
		this.permissionJsonData = permissionJsonData;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "RoleDto [id=" + id + ", name=" + name + ", description="
				+ description + ", permissionJsonData=" + permissionJsonData
				+ ", createDate=" + createDate + "]";
	}
    
    
}
