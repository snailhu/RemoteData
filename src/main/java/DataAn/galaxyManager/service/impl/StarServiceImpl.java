package DataAn.galaxyManager.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import DataAn.common.utils.DateUtil;
import DataAn.galaxyManager.dao.IStarDao;
import DataAn.galaxyManager.domain.Star;
import DataAn.galaxyManager.dto.StarDto;
import DataAn.galaxyManager.service.IStarService;

@Service
public class StarServiceImpl implements IStarService{

	@Resource
	private IStarDao starDao;
	
	@Override
	public List<StarDto> getStarsBySeriesId(long seriesId) {
		List<StarDto> starDtoList = new ArrayList<StarDto>();
		List<Star> list = starDao.findByParam("series.id", seriesId);
		if(list != null && list.size() > 0){
			StarDto dto = null;
			for (Star star : list) {
				dto = new StarDto();
				dto.setId(star.getId());
				dto.setName(star.getName());
				dto.setStartRunDate(DateUtil.format(star.getStartRunDate()));
				dto.setDescription(star.getDescription());
				starDtoList.add(dto);
			}
		}
		return starDtoList;
	}

}
