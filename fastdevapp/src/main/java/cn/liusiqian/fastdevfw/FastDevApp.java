package cn.liusiqian.fastdevfw;

import android.app.Application;

import com.orhanobut.logger.Logger;

import cn.liusiqian.fastdevfw.http.HttpService;

/**
 * Créé par liusiqian 16/7/12.
 */
public class FastDevApp extends Application
{
    private static FastDevApp instance = null;
    public static FastDevApp getInstance()
    {
        return instance;
    }

    @Override
    public void onCreate()
    {
        instance = this;
        Logger.init("FastDevApp").hideThreadInfo().methodCount(0);
        super.onCreate();
        HttpService.startApiService();
    }

    @Override
    public void onTerminate()
    {
        HttpService.stopApiService();
        super.onTerminate();
    }
}
