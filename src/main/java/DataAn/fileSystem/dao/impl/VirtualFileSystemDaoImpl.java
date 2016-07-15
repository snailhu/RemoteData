package DataAn.fileSystem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.fileSystem.option.DataType;
import DataAn.fileSystem.option.FileType;


@Repository
public class VirtualFileSystemDaoImpl extends BaseDaoImpl<VirtualFileSystem>
implements IVirtualFileSystemDao{

	@Override
	public VirtualFileSystem selectByParentIdisNullAndFileName(String fileName) {
		String hql = "from VirtualFileSystem fs where fs.parentId is null and fs.fileName=?";
		Object obj = this.getSession().createQuery(hql).setParameter(0, fileName).uniqueResult();
		if(obj == null){
			return null;
		}
		return (VirtualFileSystem) obj;
	}
	@Override
	public VirtualFileSystem selectByParentIdAndFileName(long parentId,String fileName) {
		String hql = "from VirtualFileSystem fs where fs.parentId=? and fs.fileName=?";
		Object obj = this.getSession().createQuery(hql).setParameter(0, parentId).setParameter(1, fileName).uniqueResult();
		if(obj == null){
			return null;
		}
		return (VirtualFileSystem) obj;
	}

	@Override
	public VirtualFileSystem selectByyear_month_day(String year_month_day) {
		String hql = "from VirtualFileSystem fs where fs.year_month_day=?";
		Object obj = this.getSession().createQuery(hql).setParameter(0, year_month_day).uniqueResult();
		if(obj == null){
			return null;
		}
		return (VirtualFileSystem) obj;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualFileSystem> selectByParentIdisNullAndOrder(String order) {
		String hql = "from VirtualFileSystem fs where fs.parentId is null order by " + order;
		return this.getSession().createQuery(hql).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualFileSystem> selectBySeriesAndStarAndParentIdisNullAndOrder(
			String series, String star, String order) {
		String hql = "from VirtualFileSystem fs where fs.series=? and fs.star=? and fs.parentId is null order by " + order;
		return this.getSession().createQuery(hql).setParameter(0, series).setParameter(1, star).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualFileSystem> selectBySeriesAndStarAndParentIdAndOrder(
			String series, String star, long parentId, String order) {
		String hql = "from VirtualFileSystem fs where fs.series=? and fs.star=? and fs.parentId=?";
		if(order != null && "".equals(order)){
			hql = "from VirtualFileSystem fs where fs.series=? and fs.star=? and fs.parentId=? order by " + order;
		}
		return this.getSession().createQuery(hql).setParameter(0, series).setParameter(1, star).setParameter(2, parentId).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualFileSystem> selectByOption(String series, String star,long parentId, 
			String beginTime, String endTime, String dataTypes,String order) {
		String hql = "from VirtualFileSystem fs where fs.fileType=:fileType and fs.series=:series and fs.star=:star";
		if(parentId != 0){
			hql += " and fs.parentId=:parentId";
		}
		if(StringUtils.isNotBlank(beginTime)){
			hql += " and fs.year_month_day>=:beginTime";
		}
		if(StringUtils.isNotBlank(endTime)){
			hql += " and fs.year_month_day<=:endTime";
		}
		if(StringUtils.isNotBlank(dataTypes)){
			hql += " and fs.dataType in (:datatype)";				
		}
		hql += " order by " + order;
		Query query = this.getSession().createQuery(hql).setParameter("fileType", FileType.FILE)
														.setParameter("series", series)
														.setParameter("star", star);
		if(parentId != 0){
			query.setParameter("parentId", parentId);
		}
		if(StringUtils.isNotBlank(beginTime)){
			query.setParameter("beginTime", beginTime);
		}
		if(StringUtils.isNotBlank(endTime)){
			query.setParameter("endTime", endTime);
		}
		if(StringUtils.isNotBlank(dataTypes)){
			String[] types = dataTypes.split(",");
			List<DataType> list = new ArrayList<DataType>();
			for (String type : types) {
				list.add(DataType.getType(type));
			}
			query.setParameterList("datatype", list);
		}
		return query.list();
	}

	

}
