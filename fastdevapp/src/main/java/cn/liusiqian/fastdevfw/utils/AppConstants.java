package cn.liusiqian.fastdevfw.utils;

import android.content.Context;
import android.view.WindowManager;

import cn.liusiqian.fastdevfw.FastDevApp;

/**
 * Créé par liusiqian 16/7/12.
 */

public class AppConstants
{
    static
    {
        WindowManager wm = (WindowManager) FastDevApp.getInstance().getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();
    }

    //全局控制
    public static final int DATABASE_VERSION = 1;          //数据库版本
    public static boolean DEBUG_MODE = true;              //测试模式

    // 屏幕宽高
    public static int screenWidth;
    public static int screenHeight;
}
