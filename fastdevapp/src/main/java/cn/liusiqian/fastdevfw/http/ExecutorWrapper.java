package cn.liusiqian.fastdevfw.http;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import cn.liusiqian.fastdevfw.event.ApiEvent;
import cn.liusiqian.fastdevfw.http.base.HttpExecutor;
import cn.liusiqian.fastdevfw.http.base.HttpTask;
import cn.liusiqian.fastdevfw.model.AccessTokenModel;
import cn.liusiqian.fastdevfw.preference.GlobalConfig;
import de.greenrobot.event.EventBus;

/**
 * Créé par liusiqian 16/7/14.
 */
public class ExecutorWrapper extends HttpExecutor implements Callback
{
    private EventBus bus = EventBus.getDefault();
    private Set<Api> runningTask = new CopyOnWriteArraySet<>();
    private Set<HttpTask> pendingTasks = new CopyOnWriteArraySet<>();

    protected ExecutorWrapper(){}

    @Override
    public void onFailure(Request request, IOException e)
    {
        HttpTask task = (HttpTask) request.tag();
        runningTask.remove(task.api);
        if (task.api == Api.ACCESS_TOKEN)
        {
            //re-request access_token
            mHandler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    getAccessToken();
                }
            }, 3000);
        }
        else
        {
            bus.post(new ApiEvent(task.api, task.tag, task.params, null, ApiEvent.Status.FAILED));
        }
    }

    @Override
    public void onResponse(Response response) throws IOException
    {
        HttpTask task = (HttpTask) response.request().tag();
        try
        {
            runningTask.remove(task.api);
            if (task.api == Api.ACCESS_TOKEN)
            {
                // TODO: 依赖访问接口处理
                // TODO: handle response on which other Api depends
                AccessTokenModel token = (AccessTokenModel) mGson.fromJson(response.body().string(), task.api.modelClass);
                if (token != null && token.result != null && !TextUtils.isEmpty(token.result.accessToken))
                {
                    GlobalConfig.getInstance().setAccessToken(token.result.accessToken);
                }
                for (HttpTask pTask : pendingTasks)
                {
                    executeTask(pTask);
                }
                pendingTasks.clear();
            }
            else
            {
                // 其他通用接口
                // common Apis
                if (response.code() == 200)
                {
                    String json = response.body().string();
                    Logger.d("response-" + task.api.method.toString() + "::" + response.request().urlString());
                    Logger.d(json);
                    Logger.json(json);
                    Object result = mGson.fromJson(json, task.api.modelClass);
                    bus.post(new ApiEvent(task.api, task.tag, task.params, result, ApiEvent.Status.SUCCESS));
                }
                else
                {
                    bus.post(new ApiEvent(task.api, task.tag, task.params, null, ApiEvent.Status.FAILED));
                }
            }
        }
        catch (Exception e)
        {
            bus.post(new ApiEvent(task.api, task.tag, task.params, null, ApiEvent.Status.FAILED));
            e.printStackTrace();
        }
    }


    protected void executeTask(HttpTask task)
    {
        if (!runningTask.contains(task.api))
        {
            String token = GlobalConfig.getInstance().getAccessToken();
            if (Api.ACCESS_TOKEN != task.api && TextUtils.isEmpty(token))
            {
                // 这里是依赖请求；假定如果当前不存在access_token，先请求access_token
                // one request need depending the result of another request;
                // assume that if access_token do not exists, ask it first
                pendingTasks.add(task);
                getAccessToken();
            }
            else
            {
                runningTask.add(task.api);
                addDefaultParams(task.params);
                doRequestAsync(task.url, task.params, task.api.method, task, this);
            }
        }
    }

    @Override
    protected void addDefaultParams(Map<String, String> params)
    {
        //TODO: 在这里添加每个Api请求中都要携带的参数
        //TODO: add params that each http request will take
        params.put("version", GlobalConfig.getInstance().getVersionName());
        params.put("access_token", GlobalConfig.getInstance().getAccessToken());
    }

    private void getAccessToken()
    {
        HttpTask task = new HttpTask(Api.ACCESS_TOKEN);
        executeTask(task);
    }
}
