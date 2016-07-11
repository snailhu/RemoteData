package DataAn.fileSystem.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;


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
		String hql = "from VirtualFileSystem fs where fs.series=? and fs.star=? and fs.parentId=? order by " + order;
		return this.getSession().createQuery(hql).setParameter(0, series).setParameter(1, star).setParameter(2, parentId).list();
	}

	

}
