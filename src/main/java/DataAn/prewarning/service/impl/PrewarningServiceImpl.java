package DataAn.prewarning.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import DataAn.common.dao.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.galaxy.option.J9Series_Star_ParameterType;
import DataAn.galaxyManager.dao.IParameterDao;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Parameter;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.service.IParameterService;
import DataAn.prewarning.dao.IWarningLogDao;
import DataAn.prewarning.dao.IWarningLogMongoDao;
import DataAn.prewarning.dao.IWarningValueDao;
import DataAn.prewarning.domain.WarningLog;
import DataAn.prewarning.domain.WarningValue;
import DataAn.prewarning.dto.ErrorValueDTO;
import DataAn.prewarning.dto.QueryLogDTO;
import DataAn.prewarning.dto.QueryValueDTO;
import DataAn.prewarning.dto.SelectOptionDTO;
import DataAn.prewarning.dto.WarnValueDTO;
import DataAn.prewarning.dto.WarningLogDTO;
import DataAn.prewarning.service.IPrewarningService;

@Service
public class PrewarningServiceImpl implements IPrewarningService {
	@Resource
	private IWarningValueDao warningValueDao;
	@Resource
	private IWarningLogDao warningLogDao;
	@Resource
	private IWarningLogMongoDao warningLogMongoDao;
	@Resource
	private ISeriesDao seriersDao;
	@Resource
	private IStarDao starDao;
	@Resource
	private IParameterDao parameterDao;
	@Resource
	private IParameterService parameterService;

	@Override
	public void addErrorValue(ErrorValueDTO errorValueDTO) throws Exception {
		WarningValue warningValue = new WarningValue();
		warningValue.setCreateDate(new Date());
		warningValue.setMaxVal(errorValueDTO.getMaxVal());
		warningValue.setMinVal(errorValueDTO.getMinVal());
		warningValue.setParameter(errorValueDTO.getParameter());
		warningValue.setParameterType(errorValueDTO.getParameterType());
		warningValue.setSeries(errorValueDTO.getSeries());
		warningValue.setStar(errorValueDTO.getStar());
		warningValue.setTimeZone(errorValueDTO.getTimeZone());
		warningValue.setLimitTimes(0);
		warningValue.setWarningType(1);
		warningValueDao.add(warningValue);
	}

	@Override
	public void updateErrorValue(ErrorValueDTO errorValueDTO) throws Exception {
		WarningValue warningValue = warningValueDao.get(errorValueDTO.getValueId());
		warningValue.setMaxVal(errorValueDTO.getMaxVal());
		warningValue.setMinVal(errorValueDTO.getMinVal());
		warningValue.setParameter(errorValueDTO.getParameter());
		warningValue.setParameterType(errorValueDTO.getParameterType());
		warningValue.setSeries(errorValueDTO.getSeries());
		warningValue.setStar(errorValueDTO.getStar());
		warningValue.setTimeZone(errorValueDTO.getTimeZone());
		warningValueDao.update(warningValue);
	}

	@Override
	public void deleteWarningValue(long valueId) throws Exception {
		warningValueDao.delete(valueId);
	}

	@Override
	public Pager<QueryValueDTO> pageQueryWarningValue(int pageIndex, int pageSize, String sort, String order,
			String series, String star, String parameter, String parameterType, String warningType) throws Exception {
		List<QueryValueDTO> valueDTOs = new ArrayList<QueryValueDTO>();
		Pager<WarningValue> warningPger = warningValueDao.selectByOption(pageIndex, pageSize, sort, order, series, star,
				parameter, parameterType, warningType);
		List<WarningValue> warningValues = warningPger.getDatas();
		if (warningValues != null && warningValues.size() > 0) {
			for (WarningValue value : warningValues) {
				QueryValueDTO valueDTO = new QueryValueDTO();
				valueDTO.setLimitTimes(value.getLimitTimes());
				valueDTO.setMaxVal(value.getMaxVal());
				valueDTO.setMinVal(value.getMinVal());
				valueDTO.setParameterType(
						J9Series_Star_ParameterType.getJ9SeriesStarParameterType(value.getParameterType()).getName());
				Series seriesDomain = seriersDao.get(value.getSeries());
				if (seriesDomain != null) {
					valueDTO.setSeries(seriesDomain.getName());
					valueDTO.setParameter(getParamCNname(seriesDomain.getCode(), value.getParameter()));
				} else {
					valueDTO.setSeries(value.getSeries().toString());
				}
				Star starDomain = starDao.get(value.getStar());
				if (starDomain != null) {
					valueDTO.setStar(starDomain.getName());
				} else {
					valueDTO.setStar(value.getStar().toString());
				}
				valueDTO.setTimeZone(value.getTimeZone());
				valueDTO.setValueId(value.getValueId());
				if (value.getWarningType() == 0) {
					valueDTO.setWarningType("特殊工况");
				} else if (value.getWarningType() == 1) {
					valueDTO.setWarningType("异常");
				}
				valueDTOs.add(valueDTO);
			}
		}
		Pager<QueryValueDTO> pager = new Pager<QueryValueDTO>(pageSize, pageIndex, warningPger.getTotalCount(),
				valueDTOs);
		return pager;
	}

	@Override
	public WarningValue getWarningValueById(long valueId) throws Exception {
		return warningValueDao.get(valueId);
	}

	@Override
	public void addWarningLog(WarningLogDTO warningLogDTO) throws Exception {
		WarningLog warningLog = new WarningLog();
		warningLog.setCreateDate(new Date());
		warningLog.setHadRead(warningLogDTO.getHadRead());
		warningLog.setParameter(warningLogDTO.getParameter());
		warningLog.setParameterType(warningLogDTO.getParameterType());
		warningLog.setParamValue(warningLogDTO.getParamValue());
		warningLog.setSeries(warningLogDTO.getSeries());
		warningLog.setStar(warningLogDTO.getStar());
		warningLog.setWarningType(warningLogDTO.getWarningType());
		warningLog.setTimeValue(warningLogDTO.getTimeValue());
		warningLogDao.add(warningLog);
	}

	@Override
	public void updateWarningLog(WarningLogDTO warningLogDTO) throws Exception {
		WarningLog warningLog = warningLogDao.get(warningLogDTO.getLogId());
		warningLog.setHadRead(warningLogDTO.getHadRead());
		warningLog.setParameter(warningLogDTO.getParameter());
		warningLog.setParameterType(warningLogDTO.getParameterType());
		warningLog.setParamValue(warningLogDTO.getParamValue());
		warningLog.setSeries(warningLogDTO.getSeries());
		warningLog.setStar(warningLogDTO.getStar());
		warningLog.setWarningType(warningLogDTO.getWarningType());
		warningLog.setTimeValue(warningLogDTO.getTimeValue());
		warningLogDao.update(warningLog);
	}

	@Override
	public void deleteWarningLog(String logId, String series, String star, String parameterType, String warningType)
			throws Exception {
		warningLogMongoDao.deleteWainingById(logId, series, star, parameterType, warningType);
	}

	@Override
	public Pager<QueryLogDTO> pageQueryWarningLog(int pageIndex, int pageSize, String series, String star,
			String parameterType, String parameter, String createdatetimeStart, String createdatetimeEnd,
			String warningType, String hadRead) throws Exception {
		String seriesName = "";
		String starName = "";
		Series seriesDomain = null;
		Star starDomain = null;
		if (StringUtils.isNotBlank(series)) {
			seriesDomain = seriersDao.get(Long.parseLong(series));
			if (seriesDomain != null) {
				seriesName = seriesDomain.getCode();
			}
		}
		if (StringUtils.isNotBlank(star)) {
			starDomain = starDao.get(Long.parseLong(star));
			if (starDomain != null) {
				starName = starDomain.getCode();
				// starName = starDomain.getName().substring(1);
			}
		}
		Pager<QueryLogDTO> logPager = warningLogMongoDao.selectByOption(pageIndex, pageSize, seriesName, starName,
				parameterType, parameter, createdatetimeStart, createdatetimeEnd, warningType, hadRead);
		for (QueryLogDTO warnLog : logPager.getDatas()) {
			warnLog.setParameterType(
					J9Series_Star_ParameterType.getJ9SeriesStarParameterType(warnLog.getParameterType()).getName());
			if (seriesDomain != null) {
				warnLog.setSeries(seriesDomain.getName());
				warnLog.setParameter(getParamCNname(seriesDomain.getCode(), warnLog.getParameter()));
			}
			if (starDomain != null) {
				warnLog.setStar(starDomain.getName());
			}
			if (warnLog.getWarningType().equals("0")) {
				warnLog.setWarningType("特殊工况");
			} else if (warnLog.getWarningType().equals("1")) {
				warnLog.setWarningType("异常");
			}
		}
		return logPager;
	}

	@Override
	public WarningLog getWarningLogById(long logId) throws Exception {
		return warningLogDao.get(logId);
	}

	@Override
	public Long getNotReadCount(String series, String star, String parameterType, String parameter, String warningType)
			throws Exception {
		return warningLogMongoDao.getNotReadCount(series, star, parameterType, parameter, warningType);
	}

	@Override
	public void addWarnValue(WarnValueDTO warnValueDTO) throws Exception {
		WarningValue warningValue = new WarningValue();
		warningValue.setCreateDate(new Date());
		warningValue.setMaxVal(warnValueDTO.getMaxVal().doubleValue());
		warningValue.setMinVal(warnValueDTO.getMinVal().doubleValue());
		warningValue.setParameter(warnValueDTO.getParameter());
		warningValue.setParameterType(warnValueDTO.getParameterType());
		warningValue.setSeries(warnValueDTO.getSeries());
		warningValue.setStar(warnValueDTO.getStar());
		warningValue.setTimeZone(warnValueDTO.getTimeZone().intValue());
		warningValue.setLimitTimes(warnValueDTO.getLimitTimes().intValue());
		warningValue.setWarningType(0);
		warningValueDao.add(warningValue);
	}

	@Override
	public void updateWarnValue(WarnValueDTO warnValueDTO) throws Exception {
		WarningValue warningValue = warningValueDao.get(warnValueDTO.getValueId());
		warningValue.setMaxVal(warnValueDTO.getMaxVal());
		warningValue.setMinVal(warnValueDTO.getMinVal());
		warningValue.setParameter(warnValueDTO.getParameter());
		warningValue.setParameterType(warnValueDTO.getParameterType());
		warningValue.setSeries(warnValueDTO.getSeries());
		warningValue.setStar(warnValueDTO.getStar());
		warningValue.setTimeZone(warnValueDTO.getTimeZone());
		warningValue.setLimitTimes(warnValueDTO.getLimitTimes());
		warningValueDao.update(warningValue);
	}

	@Override
	public SelectOptionDTO getSelectOption(String series, String paramaterType, String star) throws Exception {
		SelectOptionDTO selectOptionDTO = new SelectOptionDTO();
		if (StringUtils.isBlank(series) || StringUtils.isBlank(star)) {
			return selectOptionDTO;
		}
		Series seriesDomain = seriersDao.get(Long.parseLong(series));
		Star starDomain = starDao.get(Long.parseLong(star));
		if (seriesDomain != null && starDomain != null) {
			selectOptionDTO.setParamaters(
					parameterService.getParameterList(seriesDomain.getCode(), starDomain.getCode(), paramaterType));
		}
		List<StarDto> starDtoList = new ArrayList<StarDto>();
		List<Star> list = starDao.findByParam("series.id", Long.parseLong(series));
		if (list != null && list.size() > 0) {
			StarDto dto = null;
			for (Star starBean : list) {
				dto = new StarDto();
				dto.setId(starBean.getId());
				dto.setName(starBean.getName());
				dto.setBeginDate(DateUtil.format(starBean.getStartRunDate()));
				dto.setDescription(starBean.getDescription());
				starDtoList.add(dto);
			}
		}
		selectOptionDTO.setStars(starDtoList);
		return selectOptionDTO;
	}

	private String getParamCNname(String series, String EnName) throws Exception {
		String cnName = "";
		Parameter constraintDto = parameterDao.selectBySeriesAndCode(series, EnName);
		if (constraintDto != null) {
			cnName = constraintDto.getSimplyName();
		}
		return cnName;
	}

	@Override
	public boolean cherkWarningValue(String series, String star, String parameter, String parameterType,
			String warningType) throws Exception {
		return warningValueDao.cherkWarningValue(series, star, parameter, parameterType, warningType);
	}

	@Override
	public List<WarningValue> getWarningValueByParams(String series, String star, String parameter,
			String parameterType, String warningType) {
		String seriesId = seriersDao.getSeriesIdByName(series);
		if (StringUtils.isNotBlank(seriesId)) {
			List<Star> list = starDao.getStarBySeriesIdAndCode(Long.parseLong(seriesId), star);
			if (list != null && list.size() > 0) {
				return warningValueDao.getWarningValueByParams(seriesId, String.valueOf(list.get(0).getId()), parameter, parameterType, warningType);
			}
		}
		return null;
	}

	@Override
	public void addWarningLogFromMongo() throws Exception {
		List<QueryLogDTO> queryLogDTOs = warningLogMongoDao.getQueryLogDTOs();
		for (QueryLogDTO logDTO : queryLogDTOs) {
			WarningLog warningLog = new WarningLog();
			WarningLog hadwarn = warningLogDao.get(logDTO.getLogId());
			if (hadwarn != null) {
				continue;
			}
			warningLog.setLogId(logDTO.getLogId());
			warningLog.setCreateDate(new Date());
			warningLog.setHadRead(0);
			warningLog.setParameter(logDTO.getParameter());
			warningLog.setParameterType(logDTO.getParameterType());
			warningLog.setParamValue(logDTO.getParamValue());
			String seriersId = seriersDao.getSeriesIdByName(logDTO.getSeries());
			if (StringUtils.isNoneBlank(seriersId)) {
				warningLog.setSeries(Long.parseLong(seriersId));
			} else {
				continue;
			}
			String starId = starDao.getStarIdByName(logDTO.getStar());
			if (StringUtils.isNoneBlank(starId)) {
				warningLog.setStar(Long.parseLong(starId));
			} else {
				continue;
			}
			warningLog.setWarningType(Integer.parseInt(logDTO.getWarningType()));
			warningLog.setTimeValue(DateUtil.format(logDTO.getTimeValue(), "yyyy-MM-dd HH:mm:ss.SSS"));
			warningLogDao.add(warningLog);
		}
	}

	@Override
	public List<Star> getStarList(String seriesId) {
		return starDao.getStarListBySeriesId(seriesId);
	}

}
