package cn.liusiqian.fastdevfw.database.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import cn.liusiqian.fastdevfw.ExampleTag;

/**
 * Créé par liusiqian 16/7/15.
 */

@ExampleTag
@DatabaseTable(tableName = "person_info")
public class UserBean extends BaseBean
{
    @DatabaseField(canBeNull = false, defaultValue = "liusiqian")
    private String name;
    @DatabaseField()
    private int age;
    @DatabaseField()
    private String id;

    public UserBean()
    {
    }

    public UserBean(String name, int age, String id)
    {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getStuID()
    {
        return id;
    }

    public void setStuID(String stuID)
    {
        this.id = stuID;
    }
}
