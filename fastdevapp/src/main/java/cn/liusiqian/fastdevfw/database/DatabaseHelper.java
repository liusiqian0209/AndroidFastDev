package cn.liusiqian.fastdevfw.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.liusiqian.fastdevfw.ExampleTag;
import cn.liusiqian.fastdevfw.FastDevApp;
import cn.liusiqian.fastdevfw.database.bean.UserBean;
import cn.liusiqian.fastdevfw.preference.GlobalConfig;
import cn.liusiqian.fastdevfw.utils.AppConstants;

/**
 * Créé par liusiqian 16/7/15.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper
{

    //TODO: Define your tables here
    @ExampleTag
    private Class[] tableCls = new Class[]{UserBean.class};

    //TODO: Define your database file prefix here
    private static final String DB_FILE_PREFIX = "AndroidFastDev_";

    public static DatabaseHelper instance;
    private static String mCurUserId = "";
    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private DatabaseHelper(Context context, String databaseName, int databaseVersion)
    {
        super(context, databaseName, null, databaseVersion);
    }

    public static DatabaseHelper getHelper()
    {
        String userid = GlobalConfig.getInstance().getCurUserid();
        if (instance == null || !userid.equals(mCurUserId))
        {
            synchronized (DatabaseHelper.class)
            {
                if (instance == null || !userid.equals(mCurUserId))
                {
                    instance = new DatabaseHelper(FastDevApp.getInstance(), DB_FILE_PREFIX + userid + ".db", AppConstants.DATABASE_VERSION);
                    mCurUserId = userid;
                }

            }
        }

        return instance;
    }

    public synchronized Dao getDao(Class cls) throws SQLException
    {
        Dao dao = null;
        String className = cls.getSimpleName();

        if (daos.containsKey(className))
        {
            dao = daos.get(className);
        }
        if (dao == null)
        {
            dao = super.getDao(cls);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close()
    {
        super.close();

        for (String key : daos.keySet())
        {
            Dao dao = daos.get(key);
            dao = null;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource)
    {
        try
        {
            for (Class cls : tableCls)
            {
                TableUtils.createTableIfNotExists(connectionSource, cls);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource,
                          int oldVersion, int newVersion)
    {
        try
        {
            for (Class cls : tableCls)
            {
                TableUtils.dropTable(connectionSource, cls, true);
            }
            onCreate(sqLiteDatabase, connectionSource);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
