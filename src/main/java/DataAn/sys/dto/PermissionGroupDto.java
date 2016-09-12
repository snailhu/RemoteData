package DataAn.sys.dto;


/**
 * 权限组信息
 */
public class PermissionGroupDto {

    // 权限组ID
    private long id ;
    
    // 权限组名称
    private String name ;

    // 描述
    private String description;

    // 创建日期
    private String createDate;
    
    public PermissionGroupDto()
    {
    	
    }

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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "PermissionGroupDto [id=" + id + ", name=" + name + ", description="
				+ description + ", createDate=" + createDate + "]";
	}
     
  
		
}
