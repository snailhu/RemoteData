package DataAn.prewarning.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import DataAn.Analysis.dto.ConstraintDto;
import DataAn.common.dao.Pager;
import DataAn.common.utils.DateUtil;
import DataAn.fileSystem.option.J9Series_Star_ParameterType;
import DataAn.fileSystem.service.IJ9Series_Star_Service;
import DataAn.galaxyManager.dao.ISeriesDao;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Series;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.prewarning.dao.IWarningLogDao;
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
	private ISeriesDao seriersDao;
	@Resource
	private IStarDao starDao;
	@Resource
	private IJ9Series_Star_Service j9Series_Star_Service;

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
		warningValue.setTimeZone(0);
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
		warningValueDao.update(warningValue);
	}

	@Override
	public void deleteWarningValue(long valueId) throws Exception {
		warningValueDao.delete(valueId);
	}

	@Override
	public Pager<QueryValueDTO> pageQueryWarningValue(int pageIndex, int pageSize, String series, String star,
			String parameter, String parameterType, String warningType) throws Exception {
		List<QueryValueDTO> valueDTOs = new ArrayList<QueryValueDTO>();
		Pager<WarningValue> warningPger = warningValueDao.selectByOption(pageIndex, pageSize, series, star, parameter,
				parameterType, warningType);
		List<WarningValue> warningValues = warningPger.getDatas();
		if (warningValues != null && warningValues.size() > 0) {
			for (WarningValue value : warningValues) {
				QueryValueDTO valueDTO = new QueryValueDTO();
				valueDTO.setLimitTimes(value.getLimitTimes());
				valueDTO.setMaxVal(value.getMaxVal());
				valueDTO.setMinVal(value.getMinVal());
				valueDTO.setParameter(getParamCNname(value.getParameter()));
				valueDTO.setParameterType(
						J9Series_Star_ParameterType.getJ9SeriesStarParameterType(value.getParameterType()).getName());
				Series seriesDomain = seriersDao.get(value.getSeries());
				if (seriesDomain != null) {
					valueDTO.setSeries(seriesDomain.getDescription());
				} else {
					valueDTO.setSeries(value.getSeries().toString());
				}
				Star starDomain = starDao.get(value.getStar());
				if (starDomain != null) {
					valueDTO.setStar(starDomain.getDescription());
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
	public void deleteWarningLog(long logId) throws Exception {
		warningLogDao.delete(logId);
	}

	@Override
	public Pager<QueryLogDTO> pageQueryWarningLog(int pageIndex, int pageSize, String series, String star,
			String parameterType, String createdatetimeStart, String createdatetimeEnd, String warningType,
			String hadRead) throws Exception {

		List<QueryLogDTO> logDTOs = new ArrayList<QueryLogDTO>();
		Pager<WarningLog> logPager = warningLogDao.selectByOption(pageIndex, pageSize, series, star, parameterType,
				createdatetimeStart, createdatetimeEnd, warningType, hadRead);
		List<WarningLog> warningLogs = logPager.getDatas();
		if (warningLogs != null && warningLogs.size() > 0) {
			for (WarningLog warnLog : warningLogs) {
				QueryLogDTO logDTO = new QueryLogDTO();
				logDTO.setHadRead("1");
				logDTO.setLogId(warnLog.getLogId());
				logDTO.setParameter(getParamCNname(warnLog.getParameter()));
				logDTO.setParameterType(
						J9Series_Star_ParameterType.getJ9SeriesStarParameterType(warnLog.getParameterType()).getName());
				logDTO.setParamValue(warnLog.getParamValue());
				Series seriesDomain = seriersDao.get(warnLog.getSeries());
				if (seriesDomain != null) {
					logDTO.setSeries(seriesDomain.getDescription());
				} else {
					logDTO.setSeries(warnLog.getSeries().toString());
				}
				Star starDomain = starDao.get(warnLog.getStar());
				if (starDomain != null) {
					logDTO.setStar(starDomain.getDescription());
				} else {
					logDTO.setStar(warnLog.getStar().toString());
				}
				logDTO.setTimeValue(DateUtil.formatSSS(warnLog.getTimeValue()));
				if (warnLog.getWarningType() == 0) {
					logDTO.setWarningType("特殊工况");
				} else if (warnLog.getWarningType() == 1) {
					logDTO.setWarningType("异常");
				}
				logDTOs.add(logDTO);
				warnLog.setHadRead(1);
				warningLogDao.update(warnLog);
			}
		}
		Pager<QueryLogDTO> pager = new Pager<QueryLogDTO>(pageSize, pageIndex, logPager.getTotalCount(), logDTOs);
		return pager;
	}

	@Override
	public WarningLog getWarningLogById(long logId) throws Exception {
		return warningLogDao.get(logId);
	}

	@Override
	public Long getNotReadCount(String series, String star, String parameterType, String parameter, String warningType)
			throws Exception {
		return warningLogDao.getNotReadCount(series, star, parameterType, parameter, warningType);
	}

	@Override
	public void addWarnValue(WarnValueDTO warnValueDTO) throws Exception {
		WarningValue warningValue = new WarningValue();
		System.out.println(warningValue.getMaxVal());
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
	public SelectOptionDTO getSelectOption(String series, String paramaterType) throws Exception {
		SelectOptionDTO selectOptionDTO = new SelectOptionDTO();
		if ("flywheel".equals(paramaterType)) {
			selectOptionDTO.setParamaters(j9Series_Star_Service.getFlyWheelParameterList());
		}
		if ("top".equals(paramaterType)) {
			selectOptionDTO.setParamaters(j9Series_Star_Service.getTopParameterList());
		}
		if (StringUtils.isNoneBlank(series)) {
			List<StarDto> starDtoList = new ArrayList<StarDto>();
			List<Star> list = starDao.findByParam("series.id", Long.parseLong(series));
			if (list != null && list.size() > 0) {
				StarDto dto = null;
				for (Star star : list) {
					dto = new StarDto();
					dto.setId(star.getId());
					dto.setName(star.getName());
					dto.setBeginDate(DateUtil.format(star.getStartRunDate()));
					dto.setDescription(star.getDescription());
					starDtoList.add(dto);
				}
			}
			selectOptionDTO.setStars(starDtoList);
		}
		return selectOptionDTO;
	}

	private String getParamCNname(String EnName) throws Exception {
		String cnName = "";
		for (ConstraintDto constraintDto : j9Series_Star_Service.getFlyWheelParameterList()) {
			if (EnName.equals(constraintDto.getValue())) {
				cnName = constraintDto.getName();
				break;
			}
		}
		for (ConstraintDto constraintDto : j9Series_Star_Service.getTopParameterList()) {
			if (EnName.equals(constraintDto.getValue())) {
				cnName = constraintDto.getName();
				break;
			}
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
		String starId = starDao.getStarIdByName(star);
		if (StringUtils.isBlank(seriesId) || StringUtils.isBlank(starId)) {
			return null;
		}
		return warningValueDao.getWarningValueByParams(seriesId, starId, parameter, parameterType, warningType);
	}

}
