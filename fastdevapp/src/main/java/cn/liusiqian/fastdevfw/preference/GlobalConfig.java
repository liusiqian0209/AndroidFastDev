package cn.liusiqian.fastdevfw.preference;

import android.text.TextUtils;

import cn.liusiqian.fastdevfw.ExampleTag;
import cn.liusiqian.fastdevfw.FastDevApp;


/**
 * Créé par liusiqian 16/7/12.
 * <p/>
 * 这个类中的配置信息是全局信息，不取决于用户，也就是说，整个App中，这些信息只存在一份
 * Config info in this class is global, do not depend on login user, that is to say,
 * All the info below has only one copy in this whole App.
 */
public class GlobalConfig extends BaseConfig
{

    private static GlobalConfig instance;

    protected GlobalConfig()
    {
        super(PREFERENCES_NAME, FastDevApp.getInstance());
    }

    public static GlobalConfig getInstance()
    {
        if (instance == null)
        {
            synchronized (GlobalConfig.class)
            {
                if (instance == null)
                {
                    instance = new GlobalConfig();
                }
            }
        }
        return instance;
    }

    //SharedPreference File name
    private static final String PREFERENCES_NAME = "global_pref";
    // Param name
    @ExampleTag
    private static final String ACCESS_TOKEN = "access_token";
    private static final String CUR_USERID = "cur_userid";
    private static final String VERSION_CODE = "version_code";
    private static final String VERSION_NAME = "version_name";
    private static final String LOGIN_NAME = "cur_userLoginName";
    private static final String LOGIN_PASSWORD = "cur_userLoginPassword";
    private static final String CUR_USERNAME = "cur_userName";
    private static final String CUR_USERAVATARURL = "cur_userAvatarUrl";


    public String getCurUserAvatarUrl()
    {
        return getString(CUR_USERAVATARURL, "");
    }

    public void setCurUserAvatarUrl(String url)
    {
        commitString(CUR_USERAVATARURL, url);
    }


    public String getCurUsername()
    {
        return getString(CUR_USERNAME, "");
    }

    public void setCurUsername(String name)
    {
        commitString(CUR_USERNAME, name);
    }


    public int getVersionCode()
    {
        return getInt(VERSION_CODE, 0);
    }

    public void setVersionCode(int code)
    {
        commitInt(VERSION_CODE, code);
    }

    public String getVersionName()
    {
        return getString(VERSION_NAME, "");
    }

    public void setVersionName(String name)
    {
        commitString(VERSION_NAME, name);
    }

    public String getLoginName()
    {
        return getString(LOGIN_NAME, "");
    }

    public void setLoginName(String name)
    {
        commitString(LOGIN_NAME, name);
    }

    public String getLoginPassword()
    {
        return getString(LOGIN_PASSWORD, "");
    }

    public void setLoginPassword(String password)
    {
        commitString(LOGIN_PASSWORD, password);
    }

    public String getAccessToken()
    {
        return getString(ACCESS_TOKEN, null);
    }

    public void setAccessToken(String token)
    {
        commitString(ACCESS_TOKEN, token);
    }

    public String getCurUserid()
    {
        return getString(CUR_USERID, "");
    }

    public boolean isLogin()
    {
        return !TextUtils.isEmpty(getString(CUR_USERID, ""));
    }

    public void setCurUserid(String userid)
    {
        commitString(CUR_USERID, userid);
    }

}
