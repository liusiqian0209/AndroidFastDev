package cn.liusiqian.fastdevfwlib.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import de.greenrobot.event.EventBus;

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
}
