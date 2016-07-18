package DataAn.Util;

public class JsonStringToObj {	
	@SuppressWarnings("unchecked")
	public static <T> T jsonToObject(String jsonString, Class<T> pojoCalss) {
		try{
			Object pojo;
			net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(jsonString);
			pojo = net.sf.json.JSONObject.toBean(jsonObject, pojoCalss);
			return (T)pojo;
		}catch(Exception ex){
			ex.printStackTrace();			
			return null;
		}
	}
}
