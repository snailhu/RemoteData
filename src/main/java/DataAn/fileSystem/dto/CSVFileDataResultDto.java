package DataAn.fileSystem.dto;

import java.util.List;
import java.util.Map;

public class CSVFileDataResultDto<T> {

	private String title; //csv文件头部
	private String cacheFilePath; //边读边存的时候使用
	private List<T> datas; //csv文件内容
	
	private Map<String,List<T>> map; //分等级存储
	
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
	
	public Map<String, List<T>> getMap() {
		return map;
	}
	public void setMap(Map<String, List<T>> map) {
		this.map = map;
	}
	
	
}
