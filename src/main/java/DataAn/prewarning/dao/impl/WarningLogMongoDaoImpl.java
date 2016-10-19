package DataAn.prewarning.dao.impl;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.Pager;
import DataAn.prewarning.dao.IWarningLogMongoDao;
import DataAn.prewarning.domain.WarningLog;

@Repository
public class WarningLogMongoDaoImpl implements IWarningLogMongoDao {

	@Override
	public void deleteWainingById(String logId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pager<WarningLog> selectByOption(int pageIndex, int pageSize, String series, String star,
			String parameterType, String createdatetimeStart, String createdatetimeEnd, String warningType,
			String hadRead) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNotReadCount(String series, String star, String parameterType, String parameter,
			String warningType) {
		// TODO Auto-generated method stub
		return null;
	}

}
