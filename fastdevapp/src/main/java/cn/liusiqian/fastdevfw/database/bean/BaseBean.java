package cn.liusiqian.fastdevfw.database.bean;

import com.j256.ormlite.field.DatabaseField;

/**
 * Créé par liusiqian 16/7/15.
 */
public abstract class BaseBean
{
    @DatabaseField(generatedId = true)
    private int _id;
}
