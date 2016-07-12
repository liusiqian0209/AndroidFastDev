package cn.liusiqian.fastdevfwlib.preference;

import cn.liusiqian.fastdevfwlib.FastDevApp;

/**
 * Créé par liusiqian 16/7/12.
 * <p/>
 * 这个类中存放用户配置信息，每一个曾经登录过的用户，都会存在一份对应的配置
 * User config info will be stored in this class, it will generate a corresponding config info
 * for each login user
 */
public class BaseUserConfig extends BaseConfig
{
    protected BaseUserConfig(String userid)
    {
        super("user_" + userid + "_pref", FastDevApp.getInstance());
    }

    private static BaseUserConfig instance;
    private static String mCurUserId = "";

    public static BaseUserConfig getInstance()
    {
        String userId = BaseGlobalConfig.getInstance().getCurUserid();
        if (instance == null || !userId.equals(mCurUserId))
        {
            synchronized (BaseUserConfig.class)
            {
                if (instance == null || !userId.equals(mCurUserId))
                {
                    instance = new BaseUserConfig(userId);

                }
            }
        }
        return instance;
    }

}
