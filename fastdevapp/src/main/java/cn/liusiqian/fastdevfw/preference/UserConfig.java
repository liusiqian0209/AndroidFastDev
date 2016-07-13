package cn.liusiqian.fastdevfw.preference;

import cn.liusiqian.fastdevfwlib.preference.BaseUserConfig;

/**
 * Créé par liusiqian 16/7/12.
 */
public class UserConfig extends BaseUserConfig
{
    protected UserConfig(String userid)
    {
        super(userid);
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
