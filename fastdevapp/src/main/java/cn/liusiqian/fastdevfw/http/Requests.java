package cn.liusiqian.fastdevfw.http;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.liusiqian.fastdevfw.ExampleTag;
import cn.liusiqian.fastdevfw.http.base.FileUploadEntity;
import cn.liusiqian.fastdevfw.http.base.HttpTask;
import cn.liusiqian.fastdevfw.http.callback.ProgressCallback;
import cn.liusiqian.fastdevfw.model.UploadModel;

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

    @ExampleTag
    public void uploadFile(File file, ProgressCallback<UploadModel> callback)
    {
        uploadWithForm(DomainManager.getUrl(Api.UPLOAD_FILE), null, new FileUploadEntity("attach", "attach", null, file), callback);
    }
}
