  package DataAn.reportManager.util;  

import java.util.Map;


public class ResultJSON {
	
	private String result;	// true:成功执行  false：失败
	private String msg;		// 消息：后台返回消息
	private Map<String, Object> data;
	
	public ResultJSON() {
		
	}
	
	private ResultJSON(String result) {
		this.result = result;
	}
	
	public ResultJSON(String result, String msg) {
		this.result = result;
		this.msg = msg;
	}
	
	private ResultJSON(String result, String msg, Map<String, Object> data) {
		this.result = result;
		this.msg = msg;
		this.data = data;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	public static ResultJSON getResultJSON(String res, String msg) {
		return new ResultJSON(res, msg);
	}
	
	public static ResultJSON getResultJSON(String res, String msg, Map<String, Object> data ) {
		return new ResultJSON(res, msg, data);
	}
	
	public static ResultJSON getSuccessResultJSON() {
		return new ResultJSON(CommonsConstant.RESULT_TRUE);
	}
	
	public static ResultJSON getFailedResultJSON() {
		return new ResultJSON(CommonsConstant.RESULT_FALSE);
	}
	
}
  
