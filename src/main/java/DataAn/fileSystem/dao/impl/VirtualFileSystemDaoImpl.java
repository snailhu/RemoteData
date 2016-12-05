package DataAn.fileSystem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.common.pageModel.Pager;
import DataAn.fileSystem.dao.IVirtualFileSystemDao;
import DataAn.fileSystem.domain.VirtualFileSystem;
import DataAn.fileSystem.option.FileDataType;
import DataAn.fileSystem.option.FileType;

@Repository
public class VirtualFileSystemDaoImpl extends BaseDaoImpl<VirtualFileSystem>
		implements IVirtualFileSystemDao {

	@SuppressWarnings("unchecked")
	@Override
	public VirtualFileSystem selectByFileTypeIsFileAndMongoFSUUId(String uuId) {
		String hql = "from VirtualFileSystem fs where fs.fileType=:fileType and fs.mongoFSUUId=:uuId";
		List<VirtualFileSystem> list = this.getSession().createQuery(hql)
				.setParameter("fileType", FileType.FILE)
				.setParameter("uuId", uuId).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VirtualFileSystem selectByFileName(String fileName) {
		String hql = "from VirtualFileSystem fs where fs.fileName=?";
		List<VirtualFileSystem> list = this.getSession().createQuery(hql)
				.setParameter(0, fileName).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public VirtualFileSystem selectByParentIdisNullAndFileName(String fileName) {
		String hql = "from VirtualFileSystem fs where fs.parentId is null and fs.fileName=?";
		Object obj = this.getSession().createQuery(hql)
				.setParameter(0, fileName).uniqueResult();
		if (obj == null) {
			return null;
		}
		return (VirtualFileSystem) obj;
	}

	@Override
	public VirtualFileSystem selectByParentIdAndFileName(long parentId,
			String fileName) {
		String hql = "from VirtualFileSystem fs where fs.parentId=? and fs.fileName=?";
		Object obj = this.getSession().createQuery(hql)
				.setParameter(0, parentId).setParameter(1, fileName)
				.uniqueResult();
		if (obj == null) {
			return null;
		}
		return (VirtualFileSystem) obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VirtualFileSystem selectByParentIdAndFileNameAndParameterType(
			long parentId, String fileName, String parameterType) {
		if (parentId == 0) {
			String hql = "from VirtualFileSystem fs where fs.parentId is null and fs.fileName=? and fs.parameterType=?";
			List<VirtualFileSystem> list = this.getSession().createQuery(hql)
					.setParameter(0, fileName).setParameter(1, parameterType)
					.list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} else {
			String hql = "from VirtualFileSystem fs where fs.parentId=? and fs.fileName=? and fs.parameterType=?";
			List<VirtualFileSystem> list = this.getSession().createQuery(hql)
					.setParameter(0, parentId).setParameter(1, fileName)
					.setParameter(2, parameterType).list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public VirtualFileSystem selectBySeriesAndStarAndParameterTypeAndParentIdAndFileName(
			String series, String star, String parameterType, long parentId,
			String fileName) {
		if (parentId == 0) {
			String hql = "from VirtualFileSystem fs where fs.series=? and fs.star=? "
					+ "and fs.parameterType=? and fs.fileName=? and fs.parentId is null";
			List<VirtualFileSystem> list = this.getSession().createQuery(hql)
					.setParameter(0, series).setParameter(1, star)
					.setParameter(2, parameterType).setParameter(3, fileName)
					.list();
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		} else {
			String hql = "from VirtualFileSystem fs where fs.series=? and fs.star=? "
					+ "and fs.parameterType=? and fs.fileName=? and fs.parentId=?";
			List<VirtualFileSystem> list = this.getSession().createQuery(hql)
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
	public Pager<VirtualFileSystem> selectBySeriesAndStarAndParameterTypeAndParentIdAndOrder(
			String series, String star, String parameterType, long parentId,String dataTypes,
			String order, int pageIndex, int pageSize) {
		String hql = "from VirtualFileSystem fs where fs.series=:series and fs.star=:star and fs.parameterType=:parameterType";
		String countHql = "select count(*) from VirtualFileSystem fs where fs.series=:series and fs.star=:star and fs.parameterType=:parameterType";

		if (parentId != 0) {
			hql += " and fs.parentId=:parentId";
			countHql += " and fs.parentId=:parentId";
		} else {
			hql += " and fs.parentId is null";
			countHql += " and fs.parentId is null";
		}
		if (StringUtils.isNotBlank(dataTypes)) {
			hql += " and fs.dataType in (:datatype)";
			countHql += " and fs.dataType in (:datatype)";
		}
		
		if (StringUtils.isNotBlank(order)) {
			hql += " order by fs." + order;
		} else {
			hql += " order by fs.updateDate desc";
		}
		Query query = this.getSession().createQuery(hql)
				.setParameter("series", series).setParameter("star", star)
				.setParameter("parameterType", parameterType);
		Query countQuery = this.getSession().createQuery(countHql)
				.setParameter("series", series).setParameter("star", star)
				.setParameter("parameterType", parameterType);
		if (parentId != 0) {
			query.setParameter("parentId", parentId);
			countQuery.setParameter("parentId", parentId);
		}
		if (StringUtils.isNotBlank(dataTypes)) {
			String[] types = dataTypes.split(",");
			List<FileDataType> list = new ArrayList<FileDataType>();
			for (String type : types) {
				list.add(FileDataType.getType(type));
			}
			query.setParameterList("datatype", list);
			countQuery.setParameterList("datatype", list);
		}
		Long totalCount = 0l;
		Object obj = countQuery.uniqueResult();
		if (obj != null) {
			totalCount = (Long) obj;
		}
		// 设置每页显示多少个，设置多大结果。
		query.setMaxResults(pageSize);
		// 设置起点
		query.setFirstResult(pageSize * (pageIndex - 1));
		Pager<VirtualFileSystem> pager = new Pager<VirtualFileSystem>(
				pageIndex, pageSize, totalCount, query.list());
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<VirtualFileSystem> selectByOption(String series, String star,
			String parameterType, Long parentId, String beginTime,
			String endTime, String dataTypes, String order, int pageIndex,
			int pageSize) {
		String hql = "from VirtualFileSystem fs where fs.fileType=:fileType and fs.series=:series and fs.star=:star and fs.parameterType=:parameterType";
		String countHql = "select count (*) from VirtualFileSystem fs where fs.fileType=:fileType and fs.series=:series and fs.star=:star and fs.parameterType=:parameterType";

		if (parentId != null) {
			if (parentId != 0) {
				hql += " and fs.parentId=:parentId";
				countHql += " and fs.parentId=:parentId";
			} else {
				hql += " and fs.parentId is null";
				countHql += " and fs.parentId is null";
			}
		}

		if (StringUtils.isNotBlank(beginTime)) {
			hql += " and fs.year_month_day>=:beginTime";
			countHql += " and fs.year_month_day>=:beginTime";
		}
		if (StringUtils.isNotBlank(endTime)) {
			hql += " and fs.year_month_day<=:endTime";
			countHql += " and fs.year_month_day<=:endTime";
		}
		if (StringUtils.isNotBlank(dataTypes)) {
			hql += " and fs.dataType in (:datatype)";
			countHql += " and fs.dataType in (:datatype)";
		}
		if (StringUtils.isNotBlank(order)) {
			hql += " order by fs." + order;
		} else {
			hql += " order by fs.updateDate desc";
		}
		Query query = this.getSession().createQuery(hql)
				.setParameter("fileType", FileType.FILE)
				.setParameter("series", series).setParameter("star", star)
				.setParameter("parameterType", parameterType);
		Query countQuery = this.getSession().createQuery(countHql)
				.setParameter("fileType", FileType.FILE)
				.setParameter("series", series).setParameter("star", star)
				.setParameter("parameterType", parameterType);

		if (parentId != null && parentId != 0) {
			query.setParameter("parentId", parentId);
			countQuery.setParameter("parentId", parentId);
		}
		if (StringUtils.isNotBlank(beginTime)) {
			query.setParameter("beginTime", beginTime);
			countQuery.setParameter("beginTime", beginTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			query.setParameter("endTime", endTime);
			countQuery.setParameter("endTime", endTime);
		}
		if (StringUtils.isNotBlank(dataTypes)) {
			String[] types = dataTypes.split(",");
			List<FileDataType> list = new ArrayList<FileDataType>();
			for (String type : types) {
				list.add(FileDataType.getType(type));
			}
			query.setParameterList("datatype", list);
			countQuery.setParameterList("datatype", list);
		}
		Long totalCount = 0l;
		Object obj = countQuery.uniqueResult();
		if (obj != null) {
			totalCount = (Long) obj;
		}
		// 设置每页显示多少个，设置多大结果。
		query.setMaxResults(pageSize);
		// 设置起点
		query.setFirstResult(pageSize * (pageIndex - 1));
		Pager<VirtualFileSystem> pager = new Pager<VirtualFileSystem>(
				pageIndex, pageSize, totalCount, query.list());
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VirtualFileSystem> selectByFileTypeIsFileAndCachePathISNotNull() {
		String hql = "from VirtualFileSystem fs where fs.fileType=:fileType and fs.cachePath is not null";
		Query query = this.getSession().createQuery(hql)
				.setParameter("fileType", FileType.FILE);
		return query.list();
	}

}
