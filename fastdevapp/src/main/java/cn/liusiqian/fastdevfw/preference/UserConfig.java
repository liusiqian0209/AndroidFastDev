package cn.liusiqian.fastdevfw.preference;


import cn.liusiqian.fastdevfw.FastDevApp;

/**
 * Créé par liusiqian 16/7/12.
 * <p/>
 * 这个类中存放用户配置信息，每一个曾经登录过的用户，都会存在一份对应的配置
 * User config info will be stored in this class, it will generate a corresponding config info
 * for each login user
 */
public class UserConfig extends BaseConfig
{
    protected UserConfig(String userid)
    {
        super("user_" + userid + "_pref", FastDevApp.getInstance());
    }

    private static UserConfig instance;
    private static String mCurUserId = "";

    public static UserConfig getInstance()
    {
        String userId = GlobalConfig.getInstance().getCurUserid();
        if (instance == null || !userId.equals(mCurUserId))
        {
            synchronized (UserConfig.class)
            {
                if (instance == null || !userId.equals(mCurUserId))
                {
                    instance = new UserConfig(userId);

                }
            }
        }
        return instance;
    }

    // Param name
    private static final String SOUND_ON = "sound_on";
    private static final String VIBRATE_ON = "vibrate_on";

    public boolean isSound_on()
    {
        return getBoolean(SOUND_ON, false);
    }

    public void setSound_on(boolean sound_on)
    {
        commitBoolean(SOUND_ON, sound_on);
    }

    public boolean isVibrate_on()
    {
        return getBoolean(VIBRATE_ON, false);
    }

    public void setVibrate_on(boolean vibrate_on)
    {
        commitBoolean(VIBRATE_ON, vibrate_on);
    }

}
