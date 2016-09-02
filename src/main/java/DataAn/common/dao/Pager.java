package DataAn.common.dao;

import java.util.List;

/**
 * 分页对象
 */
public class Pager<T> {
	/** 分页的大小 */
	private int pageSize;
	/** 分页的起始页*/
	private int pageIndex;
	/** 总记录数 */
	private long totalCount;
	//总页数
    private Long totalPages;
	/** 分页的数据 */
	private List<T> datas;
	
	
	public Pager() {
		super();
	}

	public Pager(int pageSize, int pageIndex, long totalCount, List<T> datas) {
		super();
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		this.totalCount = totalCount;
		this.totalPages = totalCount/pageSize;
    	if (totalCount%pageSize>0) {
			this.totalPages++;
		}
		this.datas = datas;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	
}
