package DataAn.fileSystem.dao;

import java.util.List;

import DataAn.common.dao.IBaseDao;
import DataAn.fileSystem.domain.DateParameters;

public interface IDateParametersDao extends IBaseDao<DateParameters>{

	public List<DateParameters> selectByYear_month_day(String beginDate,String endDate);
}
