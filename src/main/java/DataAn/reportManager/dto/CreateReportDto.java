package DataAn.reportManager.dto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateReportDto {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private String seriesId;
	private String starId;
	private String partsType;
	private String beginTime;
	private String endTime;
	
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public String getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}
	public String getStarId() {
		return starId;
	}
	public void setStarId(String starId) {
		this.starId = starId;
	}
	public String getPartsType() {
		return partsType;
	}
	public void setPartsType(String partsType) {
		this.partsType = partsType;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
