package cn.liusiqian.fastdevfw.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import cn.liusiqian.fastdevfw.FastDevApp;
import cn.liusiqian.fastdevfw.database.DatabaseHelper;
import cn.liusiqian.fastdevfw.database.bean.BaseBean;

/**
 * Créé par liusiqian 16/7/15.
 */
public abstract class BaseDAO<T extends BaseBean>
{
    protected Dao<T,Integer> daoOpe;
    protected DatabaseHelper helper;

    /**
     * 注意，由于数据库是区分用户的，因此应当在登录之后再调用子类的构造方法
     * Attention, due to database file is diffenrent for individual login user,
     * thus we should call this Constructor after login
     *
     */
    public BaseDAO()
    {
        helper = DatabaseHelper.getHelper();
        ParameterizedType pt = (ParameterizedType)getClass().getGenericSuperclass();
        Class<T> cls = (Class<T>) pt.getActualTypeArguments()[0];
        try
        {
            daoOpe = helper.getDao(cls);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void add(T bean)
    {
        try
        {
            daoOpe.create(bean);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void addIfNotExists(T bean)
    {
        try
        {
            daoOpe.createIfNotExists(bean);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void delete(T bean)
    {
        try
        {
            daoOpe.delete(bean);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void delete(Collection<T> beans)
    {
        try
        {
            daoOpe.delete(beans);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void clearTable()
    {
        ParameterizedType pt = (ParameterizedType)getClass().getGenericSuperclass();
        Class<T> cls = (Class<T>) pt.getActualTypeArguments()[0];
        try
        {
            TableUtils.clearTable(helper.getConnectionSource(), cls);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<T> queryAll()
    {
        try
        {
            return daoOpe.queryForAll();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> queryAllOrder(String orderby, boolean asec)
    {
        try
        {
            QueryBuilder<T,Integer> qb = daoOpe.queryBuilder();
            qb.orderBy(orderby, asec);
            return daoOpe.query(qb.prepare());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通常用于查找特定的一条记录
     * @param key
     * @param value
     * @return
     */
    public List<T> queryWithConditon(String key, Object value)
    {
        try
        {
            return daoOpe.queryForEq(key, value);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> queryWithConditionOrder(String key, Object value, String orderby, boolean asec)
    {
        try
        {
            QueryBuilder<T,Integer> qb = daoOpe.queryBuilder();
            qb.where().eq(key,value);
            qb.orderBy(orderby,asec);
            return daoOpe.query(qb.prepare());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 常用复合条件查询 不能查询不等式
     * @param keys
     * @param values
     * @param orderby
     * @param limit
     * @param offset
     * @param asec
     * @return
     */
    public List<T> queryWithConditions(String[] keys,Object[] values,
                                       String orderby ,Long limit,Long offset, boolean asec)
    {
        if(keys.length != values.length)
        {
            throw new IllegalArgumentException("number of keys MUST BE SAME with number of values");
        }

        try
        {
            QueryBuilder<T,Integer> qb = daoOpe.queryBuilder();
            for(int i=0;i<keys.length;i++)
            {
                qb.where().eq(keys[i],values[i]);
            }
            if(limit != null && limit.longValue()!=0)
            {
                qb.limit(limit );
            }
            if(orderby!=null)
            {
                qb.orderBy(orderby,asec);
            }
            if(offset!=null && offset.longValue()!=0)
            {
                qb.offset( offset );
            }
            return daoOpe.query(qb.prepare());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void update(T bean)
    {
        try
        {
            daoOpe.update(bean);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
