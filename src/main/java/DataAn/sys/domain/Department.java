package DataAn.sys.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "t_department", uniqueConstraints = @UniqueConstraint(columnNames = "departmentName"))
public class Department implements java.io.Serializable {

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "departmentId", unique = true, nullable = false)
	private Long departmentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentId", nullable = true)
	private Department department;
	
	@Column(name = "departmentName", unique = true, nullable = false, length = 64)
	private String departmentName;
	
	@Column(name = "sort", nullable = true)
	private Integer sort;
	
	@Column(name = "description", nullable = true, length = 256)
	private String description;
	
	@Column(name = "createPerson", nullable = true, length = 32)
	private String createPerson;
	
	@Column(name = "updatePerson", nullable = true, length = 32)
	private String updatePerson;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", nullable = true)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", nullable = true)
	private Date updateDate;
	
	@Column(name = "version", nullable = true)
	private Integer version;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
	private Set<DepartmentUser> departUsers = new HashSet<DepartmentUser>(0);
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "department")
	private Set<Department> departments = new HashSet<Department>(0);

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Set<DepartmentUser> getDepartUsers() {
		return departUsers;
	}

	public void setDepartUsers(Set<DepartmentUser> departUsers) {
		this.departUsers = departUsers;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	

}