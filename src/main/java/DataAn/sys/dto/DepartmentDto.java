package DataAn.sys.dto;

import java.util.ArrayList;
import java.util.List;
public class DepartmentDto {
	
    private long id ;

    private String name;
    
    private long parentId;
    
    private String parentName;
    
    private String createPerson;

	private String updatePerson;
	
    private int sort;

    private String desc ;

    private String createTime ;
    
	private String updateTime ;
    
    private List<DepartmentDto> children = new ArrayList<DepartmentDto>();

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

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public List<DepartmentDto> getChildren() {
		return children;
	}

	public void setChildren(List<DepartmentDto> children) {
		this.children = children;
	}

  

}
