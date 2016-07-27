//package DataAn.analysis;
//
//public class JsonToObject {
//	
//	String  jsonArray = '[{"id":0,"secectRow":[{"id":1,"name":"采集数据26:飞轮a电源+5V(16026)","max":0,"min":0},{"id":3,"name":"采集数据28:飞轮b电源+5V(16028)","max":0,"min":0}],"Ycount":"1","Y1name":"1"},{"id":1,"secectRow":[{"id":6,"name":"采集数据31:飞轮d电机电流(16031)","max":0,"min":0},{"id":7,"name":"采集数据32:飞轮d电源+5V(16032)","max":0,"min":0}],"Ycount":"1","Y1name":"2"}]'
//	
//	public static Object[] getDTOArray(String jsonString, Class clazz, Map map){
//		setDataFormat2JAVA();
//		JSONArray array = JSONArray.fromObject(jsonString);
//		Object[] obj = new Object[array.size()];
//		for(int i = 0; i < array.size(); i++){
//		JSONObject jsonObject = array.getJSONObject(i);
//		obj[i] = JSONObject.toBean(jsonObject, clazz, map);
//		}
//		return obj;
//		} 
//			
//}
