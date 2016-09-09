package DataAn.sys.dto;


/**
 * 权限项信息
 */
public class PermissionItemDto {

    // 权限项ID
    private long id ;

    // 权限组ID
    private long permissionGroupId;
    
    //权限组名称
    private String permissionGroupName;

    // 权限代码（唯一）
    private String code;

    // 显示名称
    private String displayName;
    
    // 描述
    private String description;

    // 创建日期
    private String createDate;
    
    public PermissionItemDto()
    {
    	
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPermissionGroupId() {
		return permissionGroupId;
	}

	public void setPermissionGroupId(long permissionGroupId) {
		this.permissionGroupId = permissionGroupId;
	}

	public String getPermissionGroupName() {
		return permissionGroupName;
	}

	public void setPermissionGroupName(String permissionGroupName) {
		this.permissionGroupName = permissionGroupName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "PermissionItemDto [id=" + id + ", permissionGroupId="
				+ permissionGroupId + ", permissionGroupName="
				+ permissionGroupName + ", code=" + code + ", displayName="
				+ displayName + ", description=" + description
				+ ", createDate=" + createDate + "]";
	}

}
