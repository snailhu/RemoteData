package DataAn.linegraph.dao.impl;

import org.springframework.stereotype.Repository;

import DataAn.common.dao.BaseDaoImpl;
import DataAn.linegraph.dao.ITmplParamDao;
import DataAn.linegraph.domain.TemplateParameter;

@Repository
public class TmplParamDaoImpl extends BaseDaoImpl<TemplateParameter>
implements ITmplParamDao{
	@Override
	public void deleteByTemplateId(long tempalteId) {
		String hql = "delete from TemplateParameter param where param.linegraphtemplale.id=?";
		this.getSession().createQuery(hql).setParameter(0, tempalteId).executeUpdate();
	}

}
