package DataAn.common.pageModel;

import java.util.List;

public class Pager<T> {
	
	//起始页
    private int pageIndex; 
    //页面大小
    private int pageSize; 
    //总记录数
    private Long totalCount;
    //总页数
    private Long totalPages;
//    private boolean hasPreviousPage; 
//    private boolean hasNextPage; 
    private List<T>  rows;
    
 /**
  * 
  * @param pageIndex
  * @param pageSize
  * @param totalCount
  * @param rows
  */
    public Pager(int pageIndex,int pageSize,Long totalCount,List<T>  rows)
    {
    	this.pageIndex = pageIndex;
    	this.pageSize = pageSize;
    	this.totalCount = totalCount;
    	this.totalPages = totalCount/pageSize;
    	if (totalCount%pageSize>0) {
			this.totalPages++;
		}
    	this.setRows(rows);
    }
      
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isHasPreviousPage() {
		return pageIndex>1;
	}

	public boolean isHasNextPage() {
		return pageIndex<totalPages;
	}

	public List<T>  getRows() {
		return rows;
	}

	public void setRows(List<T>  rows) {
		this.rows = rows;
	}
	
}
