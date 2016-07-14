package cn.liusiqian.fastdevfw.http;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import cn.liusiqian.fastdevfw.event.ApiEvent;
import cn.liusiqian.fastdevfwlib.model.BaseModel;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Créé par liusiqian 16/7/15.
 */

public class HttpService extends Service
{
    @Nullable
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
            //TODO 网络问题
        }
        else if (model != null && !String.class.equals(event.api.modelClass)
                && model.errorCode != BaseModel.ERROR_CODE_SUCCESS)
        {
            //TODO Api请求失败
        }
    }
}
