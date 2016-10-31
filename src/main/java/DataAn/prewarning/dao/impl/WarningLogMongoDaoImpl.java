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

import DataAn.common.dao.Pager;
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
				if (collection != null) {
					sum += collection.count(Filters.eq("hadRead", "0"));
				}
			}
		} else if (StringUtils.isNotBlank(parameterType) && StringUtils.isBlank(warningType)) {
			for (String databaseName : databaseNames) {
				MongoCollection<Document> collection = mongodbUtil.getCollectionNotShard(databaseName,
						parameterType + "_SpecialCase");
				if (collection != null) {
					sum += collection.count(Filters.eq("hadRead", "0"));
				}

				MongoCollection<Document> collection2 = mongodbUtil.getCollectionNotShard(databaseName,
						parameterType + "_Exception");
				if (collection2 != null) {
					sum += collection2.count(Filters.eq("hadRead", "0"));
				}
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
				if (collection != null) {
					sum += collection.count(Filters.eq("hadRead", "0"));
				}

				MongoCollection<Document> collection2 = mongodbUtil.getCollectionNotShard(databaseName,
						collectionName2);
				if (collection2 != null) {
					sum += collection2.count(Filters.eq("hadRead", "0"));
				}
			}
		} else {
			for (String databaseName : databaseNames) {
				MongoCollection<Document> collection = mongodbUtil.getCollectionNotShard(databaseName,
						"flywheel_SpecialCase");
				if (collection != null) {
					sum += collection.count(Filters.eq("hadRead", "0"));
				}
				MongoCollection<Document> collection2 = mongodbUtil.getCollectionNotShard(databaseName,
						"top_SpecialCase");
				if (collection2 != null) {
					sum += collection2.count(Filters.eq("hadRead", "0"));
				}
				MongoCollection<Document> collection3 = mongodbUtil.getCollectionNotShard(databaseName,
						"flywheel_Exception");
				if (collection3 != null) {
					sum += collection3.count(Filters.eq("hadRead", "0"));
				}
				MongoCollection<Document> collection4 = mongodbUtil.getCollectionNotShard(databaseName,
						"top_Exception");
				if (collection4 != null) {
					sum += collection4.count(Filters.eq("hadRead", "0"));
				}
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

	private void updateHadRead(QueryLogDTO queryLogDTO) {
		MongodbUtil mongodbUtil = MongodbUtil.getInstance();
		String databaseName = InitMongo.getDataBaseNameBySeriesAndStar(queryLogDTO.getSeries(), queryLogDTO.getStar());
		String collectionName = getCollectionName(queryLogDTO.getParameterType(), queryLogDTO.getWarningType());
		MongoCollection<Document> collection = mongodbUtil.getCollectionNotShard(databaseName, collectionName);
		if (collection != null) {
			collection.updateMany(Filters.eq("_id", queryLogDTO.getLogId()), Updates.set("hadRead", "1"));
		}
	}

	@Override
	public Pager<QueryLogDTO> selectByOption(int pageIndex, int pageSize, String series, String star,
			String parameterType, String parameter, String createdatetimeStart, String createdatetimeEnd,
			String warningType, String hadRead) {
		MongodbUtil mongodbUtil = MongodbUtil.getInstance();
		List<QueryLogDTO> queryLogAllDTOs = new ArrayList<QueryLogDTO>();
		List<QueryLogDTO> queryLogResultDTOs = new ArrayList<QueryLogDTO>();
		if ("0".equals(hadRead)) {
			List<String> databaseNames = MongodbUtil.getInstance().getDBsBySeriersAndStar(series, star);
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
			if (queryLogAllDTOs.size() < pageSize) {
				for (int i = 0; i < queryLogAllDTOs.size(); i++) {
					QueryLogDTO queryLogDTO = queryLogAllDTOs.get(i);
					updateHadRead(queryLogDTO);
					queryLogResultDTOs.add(queryLogDTO);
				}
			} else {
				for (int i = 0; i < pageSize; i++) {
					QueryLogDTO queryLogDTO = queryLogAllDTOs.get(i);
					updateHadRead(queryLogDTO);
					queryLogResultDTOs.add(queryLogDTO);
				}
			}
			Pager<QueryLogDTO> pager = new Pager<QueryLogDTO>(pageSize, pageIndex, queryLogAllDTOs.size(),
					queryLogResultDTOs);
			return pager;
		} else {
			String databaseName = InitMongo.getDataBaseNameBySeriesAndStar(series, star);
			String collectionName = getCollectionName(parameterType, warningType);
			MongoCollection<Document> collection = mongodbUtil.getCollectionNotShard(databaseName, collectionName);
			FindIterable<Document> document_It = null;
			Long totalCount = 0l;
			List<QueryLogDTO> queryLogDTOs = new ArrayList<QueryLogDTO>();
			if (collection == null) {
				Pager<QueryLogDTO> pager = new Pager<QueryLogDTO>(pageSize, pageIndex, totalCount, queryLogDTOs);
				return pager;
			} else {
				if (StringUtils.isNotBlank(createdatetimeStart) && StringUtils.isNotBlank(createdatetimeEnd)
						&& StringUtils.isNotBlank(parameter)) {
					document_It = collection
							.find(Filters.and(Filters.eq("paramName", parameter),
									Filters.gte("datetime", DateUtil.format(createdatetimeStart)),
									Filters.lte("datetime", DateUtil.format(createdatetimeEnd))))
							.sort(Filters.eq("datetime", -1)).skip((pageIndex - 1) * pageSize).limit(pageSize);
					totalCount = collection.count(Filters.and(Filters.eq("paramName", parameter),
							Filters.gte("datetime", DateUtil.format(createdatetimeStart)),
							Filters.lte("datetime", DateUtil.format(createdatetimeEnd))));

				}
				if (StringUtils.isNotBlank(createdatetimeStart) && StringUtils.isNotBlank(createdatetimeEnd)
						&& StringUtils.isBlank(parameter)) {
					document_It = collection
							.find(Filters.and(Filters.gte("datetime", DateUtil.format(createdatetimeStart)),
									Filters.lte("datetime", DateUtil.format(createdatetimeEnd))))
							.sort(Filters.eq("datetime", -1)).skip((pageIndex - 1) * pageSize).limit(pageSize);
					totalCount = collection
							.count(Filters.and(Filters.gte("datetime", DateUtil.format(createdatetimeStart)),
									Filters.lte("datetime", DateUtil.format(createdatetimeEnd))));
				}
				if (StringUtils.isNotBlank(createdatetimeStart) && StringUtils.isBlank(createdatetimeEnd)
						&& StringUtils.isNotBlank(parameter)) {
					document_It = collection
							.find(Filters.and(Filters.eq("paramName", parameter),
									Filters.gte("datetime", createdatetimeStart)))
							.sort(Filters.eq("datetime", -1)).skip((pageIndex - 1) * pageSize).limit(pageSize);
					totalCount = collection.count(Filters.and(Filters.eq("paramName", parameter),
							Filters.gte("datetime", DateUtil.format(createdatetimeStart))));
				}
				if (StringUtils.isBlank(createdatetimeStart) && StringUtils.isNotBlank(createdatetimeEnd)
						&& StringUtils.isNotBlank(parameter)) {
					document_It = collection
							.find(Filters.and(Filters.eq("paramName", parameter),
									Filters.lte("datetime", DateUtil.format(createdatetimeEnd))))
							.sort(Filters.eq("datetime", -1)).skip((pageIndex - 1) * pageSize).limit(pageSize);
					totalCount = collection.count(Filters.and(Filters.eq("paramName", parameter),
							Filters.lte("datetime", DateUtil.format(createdatetimeEnd))));
				}
				if (StringUtils.isBlank(createdatetimeStart) && StringUtils.isBlank(createdatetimeEnd)
						&& StringUtils.isNotBlank(parameter)) {
					document_It = collection.find(Filters.eq("paramName", parameter)).sort(Filters.eq("datetime", -1))
							.skip((pageIndex - 1) * pageSize).limit(pageSize);
					totalCount = collection.count(Filters.eq("paramName", parameter));
				}
				if (StringUtils.isBlank(createdatetimeStart) && StringUtils.isNotBlank(createdatetimeEnd)
						&& StringUtils.isBlank(parameter)) {
					document_It = collection.find(Filters.lte("datetime", DateUtil.format(createdatetimeEnd)))
							.sort(Filters.eq("datetime", -1)).skip((pageIndex - 1) * pageSize).limit(pageSize);
					totalCount = collection.count(Filters.lte("datetime", DateUtil.format(createdatetimeEnd)));
				}
				if (StringUtils.isNotBlank(createdatetimeStart) && StringUtils.isBlank(createdatetimeEnd)
						&& StringUtils.isBlank(parameter)) {
					document_It = collection.find(Filters.gte("datetime", DateUtil.format(createdatetimeStart)))
							.sort(Filters.eq("datetime", -1)).skip((pageIndex - 1) * pageSize).limit(pageSize);
					totalCount = collection.count(Filters.gte("datetime", DateUtil.format(createdatetimeStart)));
				}
				if (StringUtils.isBlank(createdatetimeStart) && StringUtils.isBlank(createdatetimeEnd)
						&& StringUtils.isBlank(parameter)) {
					document_It = collection.find().sort(Filters.eq("datetime", -1)).skip((pageIndex - 1) * pageSize)
							.limit(pageSize);
					totalCount = collection.count();
				}

				for (Document doc : document_It) {
					QueryLogDTO warningLog = new QueryLogDTO();
					warningLog.setLogId(doc.getObjectId("_id").toString());
					warningLog.setParameter(doc.getString("paramName"));
					warningLog.setParameterType(doc.getString("deviceName"));
					warningLog.setParamValue(Double.parseDouble(doc.getString("value")));
					warningLog.setSeries(doc.getString("series"));
					warningLog.setStar(doc.getString("star"));
					warningLog.setTimeValue(DateUtil.formatSSS(doc.getDate("datetime")));
					warningLog.setWarningType(warningType);
					warningLog.setWarnRemark(doc.getString("remark"));
					queryLogDTOs.add(warningLog);
					collection.updateMany(Filters.eq("_id", doc.getObjectId("_id")), Updates.set("hadRead", "1"));
				}
				Pager<QueryLogDTO> pager = new Pager<QueryLogDTO>(pageSize, pageIndex, totalCount, queryLogDTOs);
				return pager;
			}
		}
	}

}
