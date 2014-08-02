package com.dingfan.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.dingfan.dao.support.HibernateEntityDao;


public class BaseDao extends HibernateEntityDao{
    
    
    /** 分页原生sql查询
     * @param sql
     * @param values
     * @return
     */
    protected Object doSQLExecuteFind(final String sql, final int startIndex, final int MaxRes, final Object... values) {
        Object result = getHibernateTemplate().execute(new HibernateCallback() {        
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                int i=0;
                for (Object object : values) {
                	query.setParameter(i, object);
                	i++;
				}          
                query.setFirstResult(startIndex);
                query.setMaxResults(MaxRes);
                return query.list();
            }
        
        });
        getHibernateTemplate().flush();
        return result;
    }    
    /** 分页原生sql查询count
     * @param sql
     * @param values
     * @return
     */
    protected Integer doSQLExecuteFindCount(final String sql, final Object... values) {
        Object result = getHibernateTemplate().execute(new HibernateCallback() {        
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                int i=0;
                for (Object object : values) {
                	query.setParameter(i, object);
                	i++;
				}  
                return query.uniqueResult();
            }
        
        });
        return (Integer)result;
    }
    protected List<?> doSQLExecuteFind(final String sql, final Object... values) {
        Object result = getHibernateTemplate().executeFind(new HibernateCallback() {        
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(sql);
                int i=0;
                for (Object object : values) {
                	query.setParameter(i, object);
                	i++;
				}
                return query.list();
            }
        
        });
        return (List<?>) result;
    }    
    
}

