package DataAn.routing;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import DataAn.Analysis.dto.YearAndParamDataDto;
import DataAn.common.utils.DateUtil;
import DataAn.common.utils.Log4jUtil;

public class DataSearchProcess {
	
	private String paramname ;
	private String paramcode ;
	private Double maxtemp ;
	private Double mintemp ;
	
	
	private YearAndParamDataDto yearAndParam = new YearAndParamDataDto();						
	private List<String> yearValue = new ArrayList<String>();
	private List<String> paramValue =  new ArrayList<String>();
	
	public DataSearchProcess(DataSearchTaskConfig searchconfig)
	{
		//paramname = searchconfig.
		paramcode = searchconfig.getProperty();
		maxtemp = Double.valueOf(searchconfig.getMaxvalue());
		mintemp = Double.valueOf(searchconfig.getMinvalue());
	}
		
	public void Process(Document doc)
	{	
		
		String paraVal = doc.getString(paramcode);			
		if(paraVal==null){
			paraVal = "\'-\'";
		}else{			
			Double paraValtemp=null;
			try{paraValtemp=Double.valueOf(paraVal);}
			catch(Exception e)
			{
				String error = "将字符串转换为Double类型时出错";
				Log4jUtil.getInstance().getLogger(DataSearchTask.class).error(error);
			}
			if((paraValtemp==null)|(paraValtemp>maxtemp) | (paraValtemp<mintemp))
			{paraVal = "\'-\'";}
		}
		
		yearValue.add(DateUtil.format(doc.getDate("datetime")));
		paramValue.add(paraVal);
	}
	
	public void merge()
	{
		yearAndParam.setParamCount(yearValue.size());
		yearAndParam.setParamValue(paramValue);
	    yearAndParam.setYearValue(yearValue);
	}

	public String getParamname() {
		return paramname;
	}

	public void setParamname(String paramname) {
		this.paramname = paramname;
	}

	public String getParamcode() {
		return paramcode;
	}

	public void setParamcode(String paramcode) {
		this.paramcode = paramcode;
	}

	public YearAndParamDataDto getYearAndParam() {
		return yearAndParam;
	}

	public void setYearAndParam(YearAndParamDataDto yearAndParam) {
		this.yearAndParam = yearAndParam;
	}

	public List<String> getYearValue() {
		return yearValue;
	}

	public void setYearValue(List<String> yearValue) {
		this.yearValue = yearValue;
	}

	public List<String> getParamValue() {
		return paramValue;
	}

	public void setParamValue(List<String> paramValue) {
		this.paramValue = paramValue;
	}
	

}
