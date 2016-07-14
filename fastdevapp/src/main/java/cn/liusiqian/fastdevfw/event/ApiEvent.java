package cn.liusiqian.fastdevfw.event;

import java.util.HashMap;
import java.util.Map;

import cn.liusiqian.fastdevfw.http.Api;

/**
 * Créé par liusiqian 16/7/13.
 */

public class ApiEvent
{
    public final Api api;
    public final Status status;
    private Map<String, String> params;
    public final String tag;
    private Object result;

    public ApiEvent(Api api, Map<String, String> params, Object data, Status status)
    {
        this.api = api;
        this.params = params;
        this.result = data;
        this.status = status;
        this.tag = "";
    }

    public ApiEvent(Api api, String tag, Map<String, String> params, Object data, Status status)
    {
        this.api = api;
        this.params = params;
        this.result = data;
        this.status = status;
        this.tag = tag;
    }


    public <M> M getModel()
    {
        return (M) result;
    }

    public Map<String, String> getParams()
    {
        return new HashMap<>(params);
    }

    public enum Status
    {
        FAILED,
        SUCCESS
    }
}
