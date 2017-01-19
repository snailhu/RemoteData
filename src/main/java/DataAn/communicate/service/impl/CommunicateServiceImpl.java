package DataAn.communicate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoCursor;

import DataAn.common.utils.DateUtil;
import DataAn.communicate.service.ICommunicateService;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.fileSystem.service.IVirtualFileSystemService;
import DataAn.galaxy.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;
import DataAn.prewarning.domain.WarningValue;
import DataAn.prewarning.service.IPrewarningService;
import DataAn.status.option.StatusTrackingType;
import DataAn.status.service.IStatusTrackingService;
import DataAn.storm.exceptioncheck.model.ExceptionJobConfig;
import DataAn.storm.exceptioncheck.model.ExceptionPointConfig;
import DataAn.sys.service.IStormServerService;

@Service
public class CommunicateServiceImpl implements ICommunicateService{

	@Resource
	private IPrewarningService prewarningService;
	@Resource
	private IStatusTrackingService statusTrackingService;
	@Resource
	private IVirtualFileSystemDao fileDao;
	@Resource
	private IParameterService parameterService;
	@Resource
	private IVirtualFileSystemService fileService;
	@Resource
	private IStormServerService stormServerService;
	
	@Override
	public String getExceptionJobConfigList(String series, String star,
			String parameterType) {		
		if (StringUtils.isBlank(series) || StringUtils.isBlank(star) || StringUtils.isBlank(parameterType)) {
			System.out.println("请求参数：星系，星，设备类型 不能为空！！！");
			return null;
		}
		System.out.println("查询的参数的是："+parameterType);
		if(J9Series_Star_ParameterType.TOP.getValue().equals(parameterType))
		{
			return this.getTopExceptionJobConfigList(series, star, parameterType);
		}
		if(J9Series_Star_ParameterType.FLYWHEEL.getValue().equals(parameterType))
		{
			return this.getFlywheelExceptionJobConfigList(series, star, parameterType);
		}
		return null;
	}

	private String getTopExceptionJobConfigList(String series, String star,
			String parameterType){
		List<WarningValue> topjobWarningValues = prewarningService.getWarningValueByParams(series, star, null, parameterType, "0");
		if(topjobWarningValues != null && topjobWarningValues.size() > 0){
			Map<String,String> map = new HashMap<String,String>();
			map.put("top", "sss");
			List<ExceptionJobConfig> jobConfigList = new ArrayList<ExceptionJobConfig>();
			ExceptionJobConfig jobConfig = null;
			for (WarningValue wv : topjobWarningValues) {
				//TODO 根据参数的qequence值获取参数所属的设备	当前设计中陀螺的设置在配置文件JSON中，所以没有获取			
				/*//String deviceName = parameterService.getParameter_deviceName_by_en(series, star, wv.getParameterType(), wv.getParameter());
				if(StringUtils.isNotBlank(deviceName)){
					jobConfig = new ExceptionJobConfig();
					jobConfig.setDeviceName(deviceName);
					jobConfig.setDeviceType(wv.getParameterType());
					jobConfig.setParamCode(wv.getParameter());
					jobConfig.setMax(wv.getMaxVal());
					jobConfig.setMin(wv.getMinVal());
					jobConfig.setDelayTime(wv.getLimitTimes());//时间单位
					jobConfig.setCount(wv.getTimeZone());
					jobConfigList.add(jobConfig);
				}else{
					throw new RuntimeException(series+"-"+star+"-"+wv.getParameterType()+"-"+wv.getParameter()+" : 找不到设备1");
				}*/
				jobConfig = new ExceptionJobConfig();
				jobConfig.setParamCode(wv.getParameter());
				jobConfig.setDelayTime(wv.getTimeZone());//TODO 设置持续时间 min，注意时间单位
				jobConfig.setMax(wv.getMaxVal());
				jobConfig.setMin(wv.getMinVal());
				jobConfigList.add(jobConfig);
				
			}
			map.put("exceptionJobConfig", JSON.toJSONString(jobConfigList));
			//异常参数配置
			List<WarningValue> exceWarningValues = prewarningService.getWarningValueByParams(series, star, null, parameterType, "1");
			if(exceWarningValues != null && exceWarningValues.size() > 0){
				List<ExceptionPointConfig> exceConfigList = new ArrayList<ExceptionPointConfig>();
				ExceptionPointConfig exceConfig = null;
				String deviceName = "AA";
				for (WarningValue ew : exceWarningValues) {
					//TODO 这里应该根据陀螺CSV文件参数的命名规则，用sequence值获取到所属陀螺的名字
					//String deviceName = parameterService.getParameter_deviceName_by_en(series, star, ew.getParameterType(), ew.getParameter());
					if(StringUtils.isNotBlank(deviceName)){
						exceConfig = new ExceptionPointConfig();
						exceConfig.setDeviceType(ew.getParameterType());
						exceConfig.setDeviceName(deviceName);
						exceConfig.setParamCode(ew.getParameter());
						exceConfig.setMax(ew.getMaxVal());
						exceConfig.setMin(ew.getMinVal());
						//exceConfig.setDelayTime(ew.getTimeZone());//时间单位
						exceConfigList.add(exceConfig);						
					}else{
						throw new RuntimeException(series+"-"+star+"-"+ew.getParameterType()+"-"+ew.getParameter()+" : 找不到设备2");
					}
				}
				map.put("exceptionPointConfig", JSON.toJSONString(exceConfigList));
			}
			return JSON.toJSONString(map);
		}
		return null;
	}
	//获取飞轮特殊工况和异常参数配置
	private String getFlywheelExceptionJobConfigList(String series, String star,
			String parameterType){
		Map<String,String> map = new HashMap<String,String>();
		//特殊工况参数配置
		List<WarningValue> jobWarningValues = prewarningService.getWarningValueByParams(series, star, null, parameterType, "0");
		if(jobWarningValues != null && jobWarningValues.size() > 0){
			List<ExceptionJobConfig> jobConfigList = new ArrayList<ExceptionJobConfig>();
			ExceptionJobConfig jobConfig = null;
			Set<String> devicdNameSet = new HashSet<String>();
			for (WarningValue wv : jobWarningValues) {
				String deviceName = parameterService.getParameter_deviceName_by_en(series, star, wv.getParameterType(), wv.getParameter());
				if(StringUtils.isNotBlank(deviceName) && !deviceName.equals("null")){
					//保证一个设备只有一个参数配置
					if (!devicdNameSet.contains(deviceName)) {
						jobConfig = new ExceptionJobConfig();
						jobConfig.setDeviceName(deviceName);
						jobConfig.setDeviceType(wv.getParameterType());
						jobConfig.setParamCode(wv.getParameter());
						jobConfig.setMax(wv.getMaxVal());
						jobConfig.setMin(wv.getMinVal());
						jobConfig.setDelayTime(wv.getTimeZone() * 60 * 1000);//TODO 设置持续时间 mm，注意时间单位
						jobConfig.setCount(wv.getLimitTimes());// 设置 限定值出现的频次计为一次特殊工况
						jobConfigList.add(jobConfig);
						devicdNameSet.add(deviceName);
					}
				}
//				else{
//					throw new RuntimeException(series+"-"+star+"-"+wv.getParameterType()+"-"+wv.getParameter()+" : 找不到设备1");
//				}
			}
			map.put("exceptionJobConfig", JSON.toJSONString(jobConfigList));
		}
		//异常参数配置
		List<WarningValue> exceWarningValues = prewarningService.getWarningValueByParams(series, star, null, parameterType, "1");
		if(exceWarningValues != null && exceWarningValues.size() > 0){
			List<ExceptionPointConfig> exceConfigList = new ArrayList<ExceptionPointConfig>();
			ExceptionPointConfig exceConfig = null;
			for (WarningValue ew : exceWarningValues) {
				String deviceName = parameterService.getParameter_deviceName_by_en(series, star, ew.getParameterType(), ew.getParameter());
				exceConfig = new ExceptionPointConfig();
				exceConfig.setDeviceType(ew.getParameterType());
				if(StringUtils.isNotBlank(deviceName) && !deviceName.equals("null"))
					exceConfig.setDeviceName(deviceName);
				exceConfig.setParamCode(ew.getParameter());
				exceConfig.setMax(ew.getMaxVal());
				exceConfig.setMin(ew.getMinVal());
				exceConfig.setDelayTime(ew.getTimeZone() * 60 * 1000);//时间单位
				exceConfigList.add(exceConfig);						
//				else{
//					throw new RuntimeException(series+"-"+star+"-"+ew.getParameterType()+"-"+ew.getParameter()+" : 找不到设备2");
//				}
			}
			map.put("exceptionPointConfig", JSON.toJSONString(exceConfigList));
		}
		if(map.size() > 0)
			return JSON.toJSONString(map);
		
		return null;
	}
	@Override
	public String getExceptionPointConfigList(String series, String star,
			String parameterType) {
		if (StringUtils.isBlank(series) || StringUtils.isBlank(star) || StringUtils.isBlank(parameterType)) {
			System.out.println("请求参数：星系，星，设备类型 不能为空！！！");
			return null;
		}
		//异常参数配置
		List<WarningValue> warningValues = prewarningService.getWarningValueByParams(series, star, null, parameterType, "1");
		if(warningValues != null && warningValues.size() > 0){
			List<ExceptionPointConfig> exceConfigList = new ArrayList<ExceptionPointConfig>();
			ExceptionPointConfig exceConfig = null;
			for (WarningValue wv : warningValues) {
				exceConfig = new ExceptionPointConfig();
				exceConfig.setDeviceType(wv.getParameterType());
				exceConfig.setDeviceName(parameterService.getParameter_deviceName_by_en(series, star, wv.getParameterType(), wv.getParameter()));
				exceConfig.setParamCode(wv.getParameter());
				exceConfig.setMax(wv.getMaxVal());
				exceConfig.setMin(wv.getMinVal());
				exceConfig.setDelayTime(wv.getLimitTimes());//时间单位
				exceConfigList.add(exceConfig);
			}
			return JSON.toJSONString(exceConfigList);
		}
		return null;
	}

	
	@Override
	public String getAllWarnValue() {
		try {
			JSONArray jsonArray = new JSONArray();
			List<WarningValue> warningValues = prewarningService.getWarningValueByParams("", "", "", "", "0");
			for (WarningValue value : warningValues) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("series", value.getSeries());
				jsonObject.put("star", value.getStar());
				jsonObject.put("parameter", value.getParameter());
				jsonObject.put("parameterType", value.getParameterType());
				jsonObject.put("maxVal", value.getMaxVal());
				jsonObject.put("minVal", value.getMinVal());
				jsonObject.put("timeZone", value.getTimeZone());
				jsonObject.put("limitTimes", value.getLimitTimes());
				jsonArray.add(jsonObject);
			}
			return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAllErrorValue() {
		try {
			JSONArray jsonArray = new JSONArray();
			List<WarningValue> warningValues = prewarningService.getWarningValueByParams("", "", "", "", "1");
			for (WarningValue value : warningValues) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("series", value.getSeries());
				jsonObject.put("star", value.getStar());
				jsonObject.put("parameter", value.getParameter());
				jsonObject.put("parameterType", value.getParameterType());
				jsonObject.put("timeZone", value.getTimeZone());
				jsonObject.put("maxVal", value.getMaxVal());
				jsonObject.put("minVal", value.getMinVal());
				jsonArray.add(jsonObject);
			}
			return jsonArray.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getWarnValueByParam(String series, String star,
			String parameterType, String parameter) {
		if (StringUtils.isBlank(series) || StringUtils.isBlank(star) || StringUtils.isBlank(parameterType)) {
			System.out.println("请求参数：星系，星，设备类型 不能为空！！！");
			return null;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("series", series);
		jsonObject.put("star", star);
		jsonObject.put("device", parameterType);
		try {
			JSONArray jsonArray = new JSONArray();
			List<WarningValue> warningValues = prewarningService.getWarningValueByParams(series, star, parameter, parameterType, "0");
			for (WarningValue value : warningValues) {
				JSONObject jO = new JSONObject();
				jO.put("paramName", value.getParameter());
				jO.put("jobMax", value.getMaxVal());
				jO.put("jobMin", value.getMinVal());
				jO.put("delayTime", value.getTimeZone());
				jO.put("count", value.getLimitTimes());
				jsonArray.add(jO);
			}
			jsonObject.put("parameterInfos", jsonArray);
			return jsonObject.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getErrorValueByParam(String series, String star,
			String parameterType, String parameter) {
		if (StringUtils.isBlank("series") || StringUtils.isBlank("star") || StringUtils.isBlank("parameterType")) {
			return "请求参数：星系，星，设备类型 不能为空！！！";
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("series", series);
		jsonObject.put("star", star);
		jsonObject.put("device", parameterType);
		try {
			JSONArray jsonArray = new JSONArray();
			List<WarningValue> warningValues = prewarningService.getWarningValueByParams(series, star, parameter, parameterType, "1");
			for (WarningValue value : warningValues) {
				JSONObject jO = new JSONObject();
				jO.put("paramName", value.getParameter());
				jO.put("delayTime", value.getTimeZone());
				jO.put("exceptionMax", value.getMaxVal());
				jO.put("exceptionMin", value.getMinVal());
				jsonArray.add(jO);
			}
			jsonObject.put("parameterInfos", jsonArray);
			return jsonObject.toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public String updateStatus(String version, String statusType,
			String exceptionInfo) {
		JSONObject jsonObject = new JSONObject();
		try {
			VirtualFileSystem file = fileDao.selectByFileTypeIsFileAndMongoFSUUId(version);
			if (file != null) {
				System.out.println("updateStatus by file ...");
				System.out.println("file.getFileName(): " + file.getFileName());
				System.out.println("statusType: " + statusType);
				System.out.println("statusType: " + StatusTrackingType.getStatusTrackingType(statusType).getName());

				statusTrackingService.updateStatusTracking(file.getFileName(), statusType, 
						file.getParameterType(),exceptionInfo);
				
				//storm 数据处理失败删除文件并更新状态
				//如果出错则删除文件
				if(statusType.equals(StatusTrackingType.PREHANDLEFAIL.getValue())){
					System.out.println("begin delete file version: " + version);
					fileService.deleteFileByUUId(version);
				}
				
				MongodbUtil mg = MongodbUtil.getInstance();
				String databaseName = InitMongo.getDataDBBySeriesAndStar(file.getSeries(), file.getStar());
				Set<String> isexistCols = mg.getExistCollections(databaseName);
				if(isexistCols != null && isexistCols.size() > 0){
					for (String collectionName : isexistCols) {
						if(collectionName.indexOf(file.getParameterType()) > -1){
							//设置某一个版本的数据的状态为1,设置临时状态为正常状态
							//TODO 待测？
							//文件上传失败，更新为原来正常状态
							if(statusType.equals(StatusTrackingType.PREHANDLEFAIL.getValue()))
								mg.update(databaseName, collectionName, "status", 2, "status", 1);
							//文件上传成功，更新状态
							if(statusType.equals(StatusTrackingType.END.getValue()))
								mg.update(databaseName, collectionName, "status", 2, "status", 0);
						}
					}
				}
				//更新特殊工况和异常状态
				if(statusType.equals(StatusTrackingType.END.getValue())){
					String job_collectionName = file.getParameterType() + "_job";
					String exception_collectionName = file.getParameterType() + "_exception";
					mg.update(databaseName, job_collectionName, "status", 3, "status", 1);
					MongoCursor<Document> cursor = mg.find(databaseName, job_collectionName, "versions", file.getMongoFSUUId());
					Document doc = null;
					String strBeginDate = null;
					String strEndDate = null;
					String deviceName = null;
					Date beginDate = null;
					Date endDate = null;
				    while (cursor.hasNext()) {
				    	doc = cursor.next();
				    	strBeginDate = doc.getString("beginDate");
				    	strEndDate = doc.getString("endDate");
				    	deviceName = doc.getString("deviceName");
				    	if(StringUtils.isNotBlank(strBeginDate) && StringUtils.isNotBlank(strEndDate) && StringUtils.isNotBlank(deviceName)){
				    		beginDate = DateUtil.format(strBeginDate);
				    		endDate = DateUtil.format(strEndDate);
				    		mg.updateByDate(databaseName, exception_collectionName, beginDate, endDate, deviceName, 1);
				    	}
				    }
				}
				jsonObject.put("sucFlag", true);
				return jsonObject.toJSONString();
			} else {
				jsonObject.put("sucFlag", false);
				return jsonObject.toJSONString();
			}
		} catch (Exception e) {
			jsonObject.put("sucFlag", false);
			e.printStackTrace();
		}		
		return jsonObject.toJSONString();
	}

	@Override
	public boolean updateServerStatus(String serverIP) {
		try {
			stormServerService.updateByServerIP(serverIP);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
