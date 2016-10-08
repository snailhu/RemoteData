package DataAn.Analysis.dto;

import java.util.List;

public class ParamGroup {
	private int id;
	
	private String Ycount;
	
	private String Y1name;
	
	private String Y2name;
	
	private String beginDate;
	
	private String endDate;
	
	private String nowSeries;
	
	private String nowStar;	
	private String component;
	
	private List<SingleParamDto> secectRow;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<SingleParamDto> getSecectRow() {
		return secectRow;
	}

	public void setSecectRow(List<SingleParamDto> secectRow) {
		this.secectRow = secectRow;
	}

	public String getY2name() {
		return Y2name;
	}

	public void setY2name(String y2name) {
		Y2name = y2name;
	}

	public String getYcount() {
		return Ycount;
	}

	public void setYcount(String ycount) {
		Ycount = ycount;
	}

	public String getY1name() {
		return Y1name;
	}

	public void setY1name(String y1name) {
		Y1name = y1name;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
		
	public String getNowSeries() {
		return nowSeries;
	}

	public void setNowSeries(String nowSeries) {
		this.nowSeries = nowSeries;
	}
	
	public String getNowStar() {
		return nowStar;
	}

	public void setNowStar(String nowStar) {
		this.nowStar = nowStar;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	@Override
	public String toString() {
		return "ParamGroup [id=" + id + ", Ycount=" + Ycount + ", Y1name="
				+ Y1name + ", Y2name=" + Y2name + ", beginDate=" + beginDate
				+ ", endDate=" + endDate + ", secectRow=" + secectRow + "]";
	}

	
	
	
}
