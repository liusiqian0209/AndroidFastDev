package cn.liusiqian.fastdevfw.activity.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import cn.liusiqian.fastdevfw.event.ApiEvent;
import cn.liusiqian.fastdevfw.http.HttpService;
import cn.liusiqian.fastdevfw.http.base.HttpTask;
import cn.liusiqian.fastdevfw.model.base.BaseModel;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Créé par liusiqian 16/7/14.
 */
public abstract class BaseActivity extends FragmentActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy()
    {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onApiEvent(ApiEvent event)
    {
        Object model = event.getModel();
        if (event.status == ApiEvent.Status.SUCCESS && model != null)
        {
            if (model instanceof String)
            {
                processStringResult((String)model);
            }
            else if( model instanceof BaseModel && HttpTask.TAG_GLOBAL.equals(event.tag))
            {
                processGlobalApiResult(event);
            }
            else if(model instanceof BaseModel)
            {
                processCustomApiResult(event);
            }
        }
    }

    protected void processCustomApiResult(ApiEvent event)
    {

    }

    protected void processGlobalApiResult(ApiEvent event)
    {

    }

    protected void processStringResult(String res)
    {

    }


}
