package DataAn.reportManager.dao.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.option.FileType;
import DataAn.reportManager.dao.IReportFileSystemDao;
import DataAn.reportManager.domain.ReportFileSystem;


@Repository
public class ReportFileSystemDaoImpl extends BaseDaoImpl<ReportFileSystem> implements IReportFileSystemDao{


	@SuppressWarnings("unchecked")
	@Override
	public ReportFileSystem selectBySeriesAndStarAndParameterTypeAndParentIdAndFileName(
			String series, String star, String parameterType, long parentId,
			String fileName) {
		if(parentId == 0){
			String hql = "from ReportFileSystem fs where fs.series=? and fs.star=? "
					+ "and fs.partsType=? and fs.fileName=? and fs.parentId is null";
			List<ReportFileSystem> list = this.getSession().createQuery(hql)
					.setParameter(0, series).setParameter(1, star)
					.setParameter(2, parameterType).setParameter(3, fileName)
					.list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}else{
			String hql = "from ReportFileSystem fs where fs.series=? and fs.star=? "
					+ "and fs.partsType=? and fs.fileName=? and fs.parentId=?";
			List<ReportFileSystem> list = this.getSession().createQuery(hql)
					.setParameter(0, series).setParameter(1, star)
					.setParameter(2, parameterType).setParameter(3, fileName)
					.setParameter(4, parentId).list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReportFileSystem selectByFileName(String fileName) {
		String hql = "from ReportFileSystem fs where fs.fileName=?";
		List<ReportFileSystem> list= this.getSession().createQuery(hql).setParameter(0, fileName).list();
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public ReportFileSystem selectByParentIdisNullAndFileName(String fileName) {
		String hql = "from ReportFileSystem fs where fs.parentId is null and fs.fileName=?";
		Object obj = this.getSession().createQuery(hql).setParameter(0, fileName).uniqueResult();
		if(obj == null){
			return null;
		}
		return (ReportFileSystem) obj;
	}
	@Override
	public ReportFileSystem selectByParentIdAndFileName(long parentId,String fileName) {
		String hql = "from ReportFileSystem fs where fs.parentId=? and fs.fileName=?";
		Object obj = this.getSession().createQuery(hql).setParameter(0, parentId).setParameter(1, fileName).uniqueResult();
		if(obj == null){
			return null;
		}
		return (ReportFileSystem) obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ReportFileSystem selectByParentIdAndFileNameAndParameterType(long parentId,
			String fileName, String partsType) {
		if(parentId == 0){
			String hql = "from ReportFileSystem fs where fs.parentId is null and fs.fileName=? and fs.partsType=?";
			List<ReportFileSystem> list = this.getSession().createQuery(hql).setParameter(0, fileName).setParameter(1, partsType).list();
			if(list != null && list.size() > 0){
				return list.get(0);
			}
		}else{
			String hql = "from ReportFileSystem fs where fs.parentId=? and fs.fileName=? and fs.partsType=?";
			List<ReportFileSystem> list = this.getSession().createQuery(hql)
															.setParameter(0, parentId)
															.setParameter(1, fileName)
															.setParameter(2, partsType).list();
			if(list != null && list.size() > 0){
				return list.get(0);
			}
		}
		return null;
	}
	
	@Override
	public ReportFileSystem selectByyear_month_day(String year_month_day) {
		String hql = "from ReportFileSystem fs where fs.year_month_day=?";
		Object obj = this.getSession().createQuery(hql).setParameter(0, year_month_day).uniqueResult();
		if(obj == null){
			return null;
		}
		return (ReportFileSystem) obj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportFileSystem> selectByParentIdisNullAndOrder(String order) {
		String hql = "from ReportFileSystem fs where fs.parentId is null order by " + order;
		return this.getSession().createQuery(hql).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pager<ReportFileSystem> selectBySeriesAndStarAndParameterTypeAndParentIdisNullAndOrder(
			String series, String star, String partsType, String order, int pageIndex, int pageSize) {
		String hql = "from ReportFileSystem fs where fs.parentId is null " ;
		String countHQl = "select count(*) from ReportFileSystem fs where fs.parentId is null ";
		if(StringUtils.isNotBlank(series)){
			hql += " and fs.series = :series";
			countHQl += " and fs.series = :series";
		}
		if(StringUtils.isNotBlank(star)){
			hql += " and fs.star = :star";
			countHQl += " and fs.star = :star";
		}
		if(StringUtils.isNotBlank(partsType)){
			hql += " and fs.partsType = :partsType";
			countHQl += " and fs.partsType = :partsType";
		}
		if(order != null && !"".equals(order)){
			hql += " order by " + order;
		}
		
		Query query = this.getSession().createQuery(hql);
		Query countQuery = this.getSession().createQuery(countHQl);
		
		if(StringUtils.isNotBlank(series)){
			query.setParameter("series", series);
			countQuery.setParameter("series", series);
		}
		if(StringUtils.isNotBlank(star)){
			query.setParameter("star", star);
			countQuery.setParameter("star", star);
		}
		if(StringUtils.isNotBlank(partsType)){
			query.setParameter("partsType", partsType);
			countQuery.setParameter("partsType", partsType);
		}
		Long totalCount = (Long) countQuery.uniqueResult();
		//设置每页显示多少个，设置多大结果
        query.setMaxResults(pageSize);
        //设置起点
        query.setFirstResult(pageSize*(pageIndex-1));  
        Pager<ReportFileSystem> pager = new Pager<ReportFileSystem>(pageIndex,pageSize,totalCount,query.list());
		return pager;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pager<ReportFileSystem> selectBySeriesAndStarAndParameterTypeAndParentIdAndOrder(
			String series, String star, String partsType, long parentId, String order, int pageIndex, int pageSize) {
		String hql = "from ReportFileSystem fs where 1 = 1 ";
		String countHQl = "select count(*) from ReportFileSystem fs where 1 = 1 ";
		if(StringUtils.isNotBlank(series)){
			hql += " and fs.series = :series";
			countHQl += " and fs.series = :series";
		}
		if(StringUtils.isNotBlank(star)){
			hql += " and fs.star = :star";
			countHQl += " and fs.star = :star";
		}
		if(StringUtils.isNotBlank(partsType)){
			hql += " and fs.partsType = :partsType";
			countHQl += " and fs.partsType = :partsType";
		}
		if(parentId != 0){
			hql += " and fs.parentId = :parentId";
			countHQl += " and fs.parentId = :parentId";
		}
		if(order != null && !"".equals(order)){
			hql += " order by " + order;
		}
		Query query = this.getSession().createQuery(hql);
		Query countQuery = this.getSession().createQuery(countHQl);
		if(StringUtils.isNotBlank(series)){
			query.setParameter("series", series);
			countQuery.setParameter("series", series);
		}
		if(StringUtils.isNotBlank(star)){
			query.setParameter("star", star);
			countQuery.setParameter("star", star);
		}
		if(StringUtils.isNotBlank(partsType)){
			query.setParameter("partsType", partsType);
			countQuery.setParameter("partsType", partsType);
		}
		if(parentId != 0){
			query.setParameter("parentId", parentId);
			countQuery.setParameter("parentId", parentId);
		}
		Long totalCount = (Long) countQuery.uniqueResult();
		//设置每页显示多少个，设置多大结果。  
        query.setMaxResults(pageSize);  
        //设置起点  
        query.setFirstResult(pageSize*(pageIndex-1));  
        Pager<ReportFileSystem> pager = new Pager<ReportFileSystem>(pageIndex,pageSize,totalCount,query.list());
		return pager;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pager<ReportFileSystem> selectByOption(String series, String star, String partsType, long parentId, 
			String beginTime, String endTime,String order, int pageIndex, int pageSize) {
		String hql = "from ReportFileSystem fs where fs.fileType = :fileType ";
		String countHQl = "select count (*) from ReportFileSystem fs where fs.fileType = :fileType ";
		if(StringUtils.isNotBlank(series)){
			hql += " and fs.series = :series";
			countHQl += " and fs.series = :series";
		}
		if(StringUtils.isNotBlank(star)){
			hql += " and fs.star = :star";
			countHQl += " and fs.star = :star";
		}
		if(StringUtils.isNotBlank(partsType)){
			hql += " and fs.partsType = :partsType";
			countHQl += " and fs.partsType = :partsType";
		}
		if(parentId != 0){
			hql += " and fs.parentId=:parentId";
			countHQl += " and fs.parentId=:parentId";
		}
		if(StringUtils.isNotBlank(beginTime)){
			hql += " and fs.year_month_day>=:beginTime";
			countHQl += " and fs.year_month_day>=:beginTime";
		}
		if(StringUtils.isNotBlank(endTime)){
			hql += " and fs.year_month_day<=:endTime";
			countHQl += " and fs.year_month_day<=:endTime";
		}
		hql += " order by " + order;
		Query query = this.getSession().createQuery(hql);
		Query countQuery = this.getSession().createQuery(countHQl);
		
		query.setParameter("fileType", FileType.FILE);
		countQuery.setParameter("fileType", FileType.FILE);
		if(StringUtils.isNotBlank(series)){
			query.setParameter("series", series);
			countQuery.setParameter("series", series);
		}
		if(StringUtils.isNotBlank(star)){
			query.setParameter("star", star);
			countQuery.setParameter("star", star);
		}
		if(StringUtils.isNotBlank(partsType)){
			query.setParameter("partsType", partsType);
			countQuery.setParameter("partsType", partsType);
		}
		if(parentId != 0){
			query.setParameter("parentId", parentId);
			countQuery.setParameter("parentId", parentId);
		}
		if(StringUtils.isNotBlank(beginTime)){
			query.setParameter("beginTime", beginTime);
			countQuery.setParameter("beginTime", beginTime);
		}
		if(StringUtils.isNotBlank(endTime)){
			query.setParameter("endTime", endTime);
			countQuery.setParameter("endTime", endTime);
		}
		Long totalCount = (Long) countQuery.uniqueResult();
		//设置每页显示多少个，设置多大结果。  
        query.setMaxResults(pageSize);  
        //设置起点  
        query.setFirstResult(pageSize*(pageIndex-1));  
        Pager<ReportFileSystem> pager = new Pager<ReportFileSystem>(pageIndex,pageSize,totalCount,query.list());
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReportFileSystem> selectByFileTypeIsFileAndCachePathISNotNull() {
		String hql = "from ReportFileSystem fs where fs.fileType=:fileType and fs.cachePath is not null";
		Query query = this.getSession().createQuery(hql).setParameter("fileType", FileType.FILE);
		return query.list();
	}

}
