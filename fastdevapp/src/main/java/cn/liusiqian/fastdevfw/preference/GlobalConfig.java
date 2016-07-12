package cn.liusiqian.fastdevfw.preference;

import cn.liusiqian.fastdevfwlib.preference.BaseGlobalConfig;

/**
 * Créé par liusiqian 16/7/12.
 */
public class GlobalConfig extends BaseGlobalConfig
{
    // Param name
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

}
