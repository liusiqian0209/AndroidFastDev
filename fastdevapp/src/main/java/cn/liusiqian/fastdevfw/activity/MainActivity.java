package cn.liusiqian.fastdevfw.activity;

import android.os.Bundle;

import cn.liusiqian.fastdevfw.ExampleTag;
import cn.liusiqian.fastdevfw.R;
import cn.liusiqian.fastdevfw.activity.base.BaseActivity;
import cn.liusiqian.fastdevfw.event.ApiEvent;
import cn.liusiqian.fastdevfw.http.Api;
import cn.liusiqian.fastdevfw.http.Requests;

public class MainActivity extends BaseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestData();
    }

    private void requestData()
    {
        Requests.getInstance().getCities();
        Requests.getInstance().getCityAir("Beijing");
//        Requests.getInstance().uploadFile();
    }

    @ExampleTag
    @Override
    protected void processGlobalApiResult(ApiEvent event)
    {
        switch (event.api)
        {
            case CITY_AIR:
                // do something you want here
                break;
        }
    }

    @ExampleTag
    @Override
    protected void processCustomApiResult(ApiEvent event)
    {
        if( "page_main".equals(event.tag) && event.api == Api.GET_CITIES)
        {
            //do something you want here
        }
    }
}
