package DataAn.communicate.service;

public interface ICommunicateService {

	public String getExceptionJobConfigList(String series, String star, String parameterType);
	
	public String getExceptionPointConfigList(String series, String star, String parameterType);
	
	public String getAllWarnValue();
	
	public String getAllErrorValue();
	
	public String getWarnValueByParam(String series, String star, String parameterType,
			String parameter);
	
	public String getErrorValueByParam(String series, String star, String parameterType,
			String parameter);
	
	public String updateStatus(String version, String statusType, String exceptionInfo);
	
	public boolean updateServerStatus(String serverIP);
}
