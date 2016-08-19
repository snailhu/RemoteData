package DataAn.fileSystem.dto;

import java.util.List;

public class SaveCSVFileResult<T> {

	private String title;
	private String cacheFilePath;
	private List<T> datas;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCacheFilePath() {
		return cacheFilePath;
	}
	public void setCacheFilePath(String cacheFilePath) {
		this.cacheFilePath = cacheFilePath;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	
}
