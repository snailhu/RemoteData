package DataAn.prewarning.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import DataAn.common.utils.DateUtil;
import DataAn.mongo.client.MongodbUtil;
import DataAn.mongo.init.InitMongo;
import DataAn.prewarning.dao.IWarningLogMongoDao;
import DataAn.prewarning.dto.QueryLogDTO;

@Repository
public class WarningLogMongoDaoImpl implements IWarningLogMongoDao {

	@Override
	public void deleteWainingById(String logId, String series, String star, String parameterType, String warningType) {
		String databaseName = InitMongo.getDataBaseNameBySeriesAndStar(series, star);
		String collectionName = getCollectionName(parameterType, warningType);
		MongodbUtil.getInstance().deleteMany(databaseName, collectionName, "_id", logId);
	}

	@Override
	public Long getNotReadCount(String series, String star, String parameterType, String parameter,
			String warningType) {
		List<String> databaseNames = MongodbUtil.getInstance().getDBsBySeriersAndStar(series, star);
		MongodbUtil mongodbUtil = MongodbUtil.getInstance();
		Long sum = 0l;
		if (StringUtils.isNotBlank(parameterType) && StringUtils.isNotBlank(warningType)) {
			String collectionName = getCollectionName(parameterType, warningType);
			for (String databaseName : databaseNames) {
				MongoCollection<Document> collection = mongodbUtil.getCollectionNotShard(databaseName, collectionName);
				sum += collection.count(Filters.eq("hadRead", "0"));
			}
		} else if (StringUtils.isNotBlank(parameterType) && StringUtils.isBlank(warningType)) {
			for (String databaseName : databaseNames) {
				MongoCollection<Document> collection = mongodbUtil.getCollectionNotShard(databaseName,
						parameterType + "_SpecialCase");
				sum += collection.count(Filters.eq("hadRead", "0"));

				MongoCollection<Document> collection2 = mongodbUtil.getCollectionNotShard(databaseName,
						parameterType + "_Exception");
				sum += collection2.count(Filters.eq("hadRead", "0"));
			}
		} else if (StringUtils.isBlank(parameterType) && StringUtils.isNotBlank(warningType)) {
			String collectionName = "";
			String collectionName2 = "";
			if ("0".equals(warningType)) {
				collectionName = "flywheel_SpecialCase";
				collectionName2 = "top_SpecialCase";
			} else if ("1".equals(warningType)) {
				collectionName = "flywheel_Exception";
				collectionName2 = "top_Exception";
			}
			for (String databaseName : databaseNames) {
				MongoCollection<Document> collection = mongodbUtil.getCollectionNotShard(databaseName, collectionName);
				sum += collection.count(Filters.eq("hadRead", "0"));

				MongoCollection<Document> collection2 = mongodbUtil.getCollectionNotShard(databaseName,
						collectionName2);
				sum += collection2.count(Filters.eq("hadRead", "0"));
			}
		} else {
			for (String databaseName : databaseNames) {
				sum += mongodbUtil.getCollectionNotShard(databaseName, "flywheel_SpecialCase")
						.count(Filters.eq("hadRead", "0"));
				sum += mongodbUtil.getCollectionNotShard(databaseName, "top_SpecialCase")
						.count(Filters.eq("hadRead", "0"));
				sum += mongodbUtil.getCollectionNotShard(databaseName, "flywheel_Exception")
						.count(Filters.eq("hadRead", "0"));
				sum += mongodbUtil.getCollectionNotShard(databaseName, "top_Exception")
						.count(Filters.eq("hadRead", "0"));
			}
		}
		return sum;
	}

	private String getCollectionName(String parameterType, String warningType) {
		String collectionName = "";
		if ("0".equals(warningType)) {
			collectionName = parameterType + "_SpecialCase";
		} else if ("1".equals(warningType)) {
			collectionName = parameterType + "_Exception";
		}
		return collectionName;
	}

	private List<QueryLogDTO> getWarningLogListByCollection(MongoCollection<Document> collection, String warnType) {
		List<QueryLogDTO> queryLogDTOs = new ArrayList<QueryLogDTO>();
		if (collection == null) {
			return queryLogDTOs;
		}
		FindIterable<Document> document_It = collection.find(Filters.eq("hadRead", "0"));
		for (Document doc : document_It) {
			QueryLogDTO warningLog = new QueryLogDTO();
			warningLog.setLogId(doc.getObjectId("_id").toString());
			warningLog.setParameter(doc.getString("paramName"));
			warningLog.setParameterType(doc.getString("deviceName"));
			warningLog.setParamValue(Double.parseDouble(doc.getString("value")));
			warningLog.setSeries(doc.getString("series"));
			warningLog.setStar(doc.getString("star"));
			warningLog.setTimeValue(DateUtil.formatSSS(doc.getDate("datetime")));
			warningLog.setWarningType(warnType);
			queryLogDTOs.add(warningLog);
			collection.updateMany(Filters.eq("_id", doc.getObjectId("_id")), Updates.set("hadRead", "1"));
		}
		return queryLogDTOs;
	}

	@Override
	public List<QueryLogDTO> getQueryLogDTOs() {
		MongodbUtil mongodbUtil = MongodbUtil.getInstance();
		List<String> databaseNames = mongodbUtil.getDBsBySeriersAndStar("", "");
		List<QueryLogDTO> queryLogAllDTOs = new ArrayList<QueryLogDTO>();
		for (String databaseName : databaseNames) {
			queryLogAllDTOs.addAll(getWarningLogListByCollection(
					mongodbUtil.getCollectionNotShard(databaseName, "flywheel_SpecialCase"), "0"));
			queryLogAllDTOs.addAll(getWarningLogListByCollection(
					mongodbUtil.getCollectionNotShard(databaseName, "top_SpecialCase"), "0"));
			queryLogAllDTOs.addAll(getWarningLogListByCollection(
					mongodbUtil.getCollectionNotShard(databaseName, "flywheel_Exception"), "1"));
			queryLogAllDTOs.addAll(getWarningLogListByCollection(
					mongodbUtil.getCollectionNotShard(databaseName, "top_Exception"), "1"));
		}
		return queryLogAllDTOs;
	}

}
