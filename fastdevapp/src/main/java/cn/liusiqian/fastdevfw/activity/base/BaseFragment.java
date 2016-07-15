package cn.liusiqian.fastdevfw.activity.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import de.greenrobot.event.EventBus;

/**
 * Créé par liusiqian 16/7/14.
 */
public abstract class BaseFragment extends Fragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy()
    {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
