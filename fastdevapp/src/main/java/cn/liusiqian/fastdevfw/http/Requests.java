package cn.liusiqian.fastdevfw.http;

import cn.liusiqian.fastdevfw.ExampleTag;
import cn.liusiqian.fastdevfw.http.base.HttpTask;

/**
 * Créé par liusiqian 16/7/15.
 */
public class Requests extends ExecutorWrapper
{
    private static Requests instance = null;
    private Requests(){}
    public static Requests getInstance()
    {
        if(instance == null)
        {
            synchronized (Requests.class)
            {
                if (instance == null)
                {
                    instance = new Requests();
                }
            }
        }
        return instance;
    }

    @ExampleTag
    public void getCityAir(String cityName)
    {
        HttpTask task = new HttpTask(Api.CITY_AIR);
        task.addParam("city",cityName);
        executeTask(task);
    }

    @ExampleTag
    public void getCities()
    {
        HttpTask task = new HttpTask(Api.GET_CITIES,"page_main");
        executeTask(task);
    }
}
