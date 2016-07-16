package cn.liusiqian.fastdevfw.database.dao;

import java.util.List;

import cn.liusiqian.fastdevfw.ExampleTag;
import cn.liusiqian.fastdevfw.database.bean.UserBean;

/**
 * Créé par liusiqian 16/7/15.
 */

@ExampleTag
public class UserDAO extends BaseDAO<UserBean>
{
    public boolean addSingleUser(String userid, String name, int age)
    {
        UserBean bean = querySingleUser(userid);
        if (null == bean)
        {
            add(new UserBean(name, age, userid));
            return true;
        }
        else
        {
            bean.setName(name);
            bean.setAge(age);
            update(bean);
            return false;
        }
    }

    public boolean deleteSingleUser(String userid)
    {
        UserBean bean = querySingleUser(userid);
        if (null != bean)
        {
            delete(bean);
            return true;
        }
        return false;
    }

    public UserBean querySingleUser(String userid)
    {
        List<UserBean> Users = queryWithConditon("id", userid);
        if (Users == null || Users.size() == 0)
        {
            return null;        //not found
        }
        else if (Users.size() == 1)
        {
            return Users.get(0);
        }
        else
        {
            throw new IllegalStateException("存在相同id的记录");
        }
    }

    public void updateUserAge(String userid, int age)
    {
        UserBean bean = querySingleUser(userid);
        if (null != bean)
        {
            bean.setAge(age);
            update(bean);
        }
    }
}
