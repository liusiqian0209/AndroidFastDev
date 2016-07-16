package cn.liusiqian.fastdevfw.http;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import cn.liusiqian.fastdevfw.FastDevApp;
import cn.liusiqian.fastdevfw.event.ApiEvent;
import cn.liusiqian.fastdevfw.model.base.BaseModel;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Créé par liusiqian 16/7/15.
 */

public class HttpService extends Service
{
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onApiEvent(final ApiEvent event)
    {
        BaseModel model = event.getModel();
        if (event.status == ApiEvent.Status.FAILED)
        {
            processNetworkError();
        }
        else if (model != null && !String.class.equals(event.api.modelClass)
                && model.errorCode != BaseModel.ERROR_CODE_SUCCESS)
        {
            processApiError(model.errorCode, model.reason);
        }
    }

    protected void processNetworkError()
    {
        //TODO 处理网络错误
        //TODO process network error
    }

    protected void processApiError(int errorCode, String errorMsg)
    {
        //TODO Api请求失败
        //TODO api request error
    }

    public static void startApiService()
    {
        Intent intent = new Intent();
        intent.setClass(FastDevApp.getInstance(),HttpService.class);
        FastDevApp.getInstance().startService(intent);
    }

    public static void stopApiService()
    {
        Intent intent = new Intent();
        intent.setClass(FastDevApp.getInstance(),HttpService.class);
        FastDevApp.getInstance().stopService(intent);
    }
}
